package com.uningen.estore.web;

import com.uningen.estore.domain.cart.*;
import com.uningen.estore.domain.product.ProductNotFoundException;
import com.uningen.estore.domain.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("cart")
public class CartController {
    private final ProductService productService;
    private final CartService cartService;

    public CartController(ProductService productService, CartService cartService) {
        this.productService = productService;
        this.cartService = cartService;
    }

//    @PostMapping
//    public ResponseEntity<Cart> createCart(){
//        Map<Long, Integer> cartItems = new HashMap<>();
//        cartItems.put(2L, 5);
//        cartItems.put(4L,2);
//        cartItems.put(6L, 1);
//        Cart cart = Cart.of(cartItems);
//        cartService.saveCart(cart);
//        return new ResponseEntity<>(cart, HttpStatus.CREATED);
//    }

    @PostMapping
//    public ResponseEntity<Cart> addProductToCart(
    public ResponseEntity<Optional<CartDTO>> addProductToCart(
            @RequestParam Long productId,
            @RequestParam int quantity
    ){
        if(quantity <= 0) throw new NegativeQuantityException(quantity);
        Cart cart = cartService.getCartById(1L).isPresent() ? cartService.getCartById(1L).get() : null;
        Map<Long, Integer> cartItems;
        if(!productService.existsById(productId)) throw new ProductNotFoundException(productId);
        if(productService.checkAvailability(productId, quantity)){
            if(cart == null) {
                cartItems = new HashMap<>();
                cartItems.put(productId, quantity);
                cart = Cart.of(cartItems);
//                return new ResponseEntity<>(cartService.saveCart(cart), HttpStatus.CREATED);
                cartService.saveCart(cart);
                return new ResponseEntity<>(Optional.of(cartService.getCartDTO(Optional.of(cart))), HttpStatus.OK);
            }
            else{
                cartItems = cart.getCartProducts();
                if(cartItems.containsKey(productId)){
                    cartItems.put(productId, cartItems.get(productId) + quantity);
                }
                else {
                    cartItems.put(productId, quantity);
                }
//                return new ResponseEntity<>(cartService.saveCart(cart), HttpStatus.OK);
                cartService.saveCart(cart);
                return new ResponseEntity<>(Optional.of(cartService.getCartDTO(Optional.of(cart))), HttpStatus.OK);
            }
        }
        throw new InsufficientQuantityAvailableException(productId, quantity);
    }

    @DeleteMapping
//    public ResponseEntity<Cart> removeProductFromCart(
    public ResponseEntity<Optional<CartDTO>> removeProductFromCart(
            @RequestParam Long productId,
            @RequestParam int quantity
    ){
        if(quantity <= 0) throw new NegativeQuantityException(quantity);
        Long cartId = 1L;

        Cart cart = cartService.getCartById(cartId).isPresent() ? cartService.getCartById(cartId).get() : null;
        if(cart == null) throw new CartNotFoundException(cartId);
        if(productService.existsById(productId)){
            Map<Long, Integer> cartItems = cart.getCartProducts();
            if(cartItems.containsKey(productId)){
                int finalQty = cartItems.get(productId) - quantity >= 0 ? cartItems.get(productId) - quantity : 0;
                if(finalQty <= 0) {
                    cartItems.remove(productId);
                }
                else {
                    cartItems.put(productId, finalQty);
                }

//                return new ResponseEntity<>(cartService.saveCart(cart), HttpStatus.OK);
                cartService.saveCart(cart);
                return new ResponseEntity<>(Optional.of(cartService.getCartDTO(Optional.of(cart))), HttpStatus.OK);
            }
            else {
                throw new ProductNotFoundException(productId);
            }
        }
        throw new ProductNotFoundException(productId);
    }

    // http://localhost:8001/cart?productId=46&quantity=2

//    @GetMapping
//    public Iterable<Cart> getAllCarts(){
//        return cartService.getAllCarts();
//    }

//    @GetMapping("{id}")
//    public Optional<CartDTO> getCartById(@PathVariable Long id){
//        return cartService.getCartDetailsById(id);
//    }

    @GetMapping
    public Optional<CartDTO> getCart(){
        return cartService.getCart();
    }
}
