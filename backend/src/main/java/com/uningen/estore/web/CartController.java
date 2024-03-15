package com.uningen.estore.web;

import com.uningen.estore.config.JwtService;
import com.uningen.estore.domain.cart.*;
import com.uningen.estore.domain.product.ProductNotFoundException;
import com.uningen.estore.domain.product.ProductService;
import com.uningen.estore.domain.user.AppUserRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("cart")
@RequiredArgsConstructor
public class CartController {
    private final ProductService productService;
    private final CartService cartService;
    private final JwtService jwtService;
    private final AppUserRepository appUserRepository;

    @PostMapping
    public ResponseEntity<CartDTO> addProductToCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @CookieValue(value = "userid", defaultValue = "unknown") String userIdFromCookie,
            HttpServletResponse response
    ){
        if(quantity <= 0) throw new NegativeQuantityException(quantity);
        Cart cart = cartService.transferCartUponLogin(userIdFromCookie, authHeader, response);
        Map<Long, Integer> cartItems = cart.getCartProducts();
        if(!productService.existsById(productId)) throw new ProductNotFoundException(productId);
        if(productService.checkAvailability(productId, quantity)){
            if(cartItems.containsKey(productId)){
                cartItems.put(productId, cartItems.get(productId) + quantity);
                productService.editProductQuantity(productId, -quantity);
            }
            else {
                cartItems.put(productId, quantity);
                productService.editProductQuantity(productId, -quantity);
            }
            return new ResponseEntity<>(cartService.saveCartAndGetDTO(cart), HttpStatus.OK);
        }
        throw new InsufficientQuantityAvailableException(productId, quantity);
    }

    @DeleteMapping
    public ResponseEntity<CartDTO> removeProductFromCart(
            @RequestParam Long productId,
            @RequestParam int quantity,
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @CookieValue(value = "userid", defaultValue = "unknown") String userIdFromCookie,
            HttpServletResponse response
    ){
        if(quantity <= 0) throw new NegativeQuantityException(quantity);
        Cart cart = cartService.transferCartUponLogin(userIdFromCookie, authHeader, response);

        if(productService.existsById(productId)){
            Map<Long, Integer> cartItems = cart.getCartProducts();
            if(cartItems.containsKey(productId)){
                int finalQty = cartItems.get(productId) - quantity >= 0 ? cartItems.get(productId) - quantity : 0;
                if(finalQty <= 0) {
                    productService.editProductQuantity(productId, cartItems.get(productId));
                    cartItems.remove(productId);
                }
                else {
                    cartItems.put(productId, finalQty);
                    productService.editProductQuantity(productId, quantity);
                }
                return new ResponseEntity<>(cartService.saveCartAndGetDTO(cart), HttpStatus.OK);
            }
            else {
                throw new ProductNotFoundException(productId);
            }
        }
        throw new ProductNotFoundException(productId);
    }
}
