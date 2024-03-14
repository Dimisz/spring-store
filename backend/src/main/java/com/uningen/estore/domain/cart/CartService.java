package com.uningen.estore.domain.cart;

import com.uningen.estore.config.JwtService;
import com.uningen.estore.domain.product.Product;
import com.uningen.estore.domain.product.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;
    private final JwtService jwtService;

    public CartDTO getCartDTO(Optional<Cart> cart){
//        if(!cart.isPresent()) return null;
        Cart existingCart = cart.isPresent() ? cart.get() : new Cart("Currently not set", new HashMap<Long, Integer>());
        Map<Long, Integer> cartMap = existingCart.getCartProducts();
        List<CartItem> cartItems = new ArrayList<>();
        if(cartMap != null && !cartMap.isEmpty()){
            for(Long productId : cartMap.keySet()){
                Product product = productService.viewProductDetails(productId);
                Integer productQty = cartMap.get(productId);
                cartItems.add(new CartItem(product, productQty));
            }
        }
        return new CartDTO(null, existingCart.getId(), cartItems, "payment intent", "client secret");
    }

    public Optional<CartDTO> getCartDTOByEmail(String email){
        Optional<Cart> cart = cartRepository.findById(email);
        return Optional.ofNullable(getCartDTO(cart));
    }

    // CHECK HOW TO ASSIGN ID AFTER AUTH
    public Optional<CartDTO> getCart(String email){
        return getCartDTOByEmail(email);
    }

//    public void changeCartUserEmail(Cart cart, String userEmail){
//        cart.setUserEmail(userEmail);
//        cartRepository.save(cart);
//    }

    public Optional<Cart> getCartById(String id){
        return cartRepository.findById(id);
    }

//    public Optional<Cart> getCartByEmail(String userEmail){
//        return cartRepository.findById(userEmail);
//    }


    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public CartDTO saveCartAndGetDTO(Cart cart){
        return getCartDTO(Optional.of(cartRepository.save(cart)));
    }

    public Iterable<Cart> getAllCarts(){
        return cartRepository.findAll();
    }

    public Cart transferCartUponLogin(String userIdFromCookie, String authHeader, HttpServletResponse response){
        String userId = userIdFromCookie;

        String extractedEmail = jwtService.extractEmailOrGetNull(authHeader);
        // user just logged in -> transfer anonymous cart
        Cart cart;
        if(extractedEmail != null){
            if(cartRepository.findById(extractedEmail).isPresent()){
                cart = cartRepository.findById(extractedEmail).get();
                if(!userIdFromCookie.equals("unknown")){
                    if(cartRepository.findById(userIdFromCookie).isPresent()){
                        Cart anonymousCart = cartRepository.findById(userIdFromCookie).get();
                        Map<Long, Integer> productFromAnonymousCart = anonymousCart.getCartProducts();
                        Map<Long, Integer> copiedProducts = new HashMap<>();
                        for(Long productId : productFromAnonymousCart.keySet()){
                            copiedProducts.put(productId, productFromAnonymousCart.get(productId));
                        }
                        cartRepository.removeById(userIdFromCookie);
                        cart.setCartProducts(copiedProducts);
//                        cartRepository.removeById(userIdFromCookie);
                    }
                }
                clearCookie(response);
                return cart;
            }
            else if(!userIdFromCookie.equals("unknown")){
                if(cartRepository.findById(userIdFromCookie).isPresent()){
                    Cart anonymousCart = cartRepository.findById(userIdFromCookie).get();
                    Map<Long, Integer> productFromAnonymousCart = anonymousCart.getCartProducts();
                    Map<Long, Integer> copiedProducts = new HashMap<>();
                    for(Long productId : productFromAnonymousCart.keySet()){
                        copiedProducts.put(productId, productFromAnonymousCart.get(productId));
                    }
                    cartRepository.removeById(userIdFromCookie);
                    cart = new Cart(extractedEmail, copiedProducts);
//                    cart.setUserEmail(extractedEmail);
                    clearCookie(response);
                    return cart;
                }
            }
            else {
                cart = new Cart(extractedEmail, new HashMap<Long, Integer>());
                clearCookie(response);
                return cart;
            }
        }
        else if(!userIdFromCookie.equals("unknown")){
            if(cartRepository.findById(userIdFromCookie).isPresent()){
                cart = cartRepository.findById(userIdFromCookie).get();
                return cart;
            }
            else {
                return new Cart(userIdFromCookie, new HashMap<Long, Integer>());
            }
        }
        throw new CartNotFoundException("non existent");
    }

    private void clearCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("userid", "");
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
