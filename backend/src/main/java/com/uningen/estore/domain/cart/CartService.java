package com.uningen.estore.domain.cart;

import com.uningen.estore.domain.product.Product;
import com.uningen.estore.domain.product.ProductService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CartService {
    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public CartDTO getCartDTO(Optional<Cart> cart){
        if(!cart.isPresent()) return null;

        Map<Long, Integer> cartMap = cart.isPresent() ? cart.get().getCartProducts() : new HashMap<>();
        List<CartItem> cartItems = new ArrayList<>();
        if(!cartMap.isEmpty()){
            for(Long productId : cartMap.keySet()){
                Product product = productService.viewProductDetails(productId);
                Integer productQty = cartMap.get(productId);
                cartItems.add(new CartItem(product, productQty));
            }
        }
        CartDTO cartDTO = new CartDTO(cart.get().getId(), "some buyer", cartItems, "payment intent", "client secret");
        return cartDTO;
    }

    public Optional<CartDTO> getCartDTOById(Long id){
        Optional<Cart> cart = cartRepository.findById(id);
        return Optional.ofNullable(getCartDTO(cart));
    }

    // CHECK HOW TO ASSIGN ID AFTER AUTH
    public Optional<CartDTO> getCart(){
        return getCartDTOById(1L);
    }

    public Optional<Cart> getCartById(Long id){
        return cartRepository.findById(id);
    }


    public Cart saveCart(Cart cart){
        return cartRepository.save(cart);
    }

    public Iterable<Cart> getAllCarts(){
        return cartRepository.findAll();
    }
}
