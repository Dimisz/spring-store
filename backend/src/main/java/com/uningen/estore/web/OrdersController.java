package com.uningen.estore.web;

import com.uningen.estore.config.JwtService;
import com.uningen.estore.domain.cart.Cart;
import com.uningen.estore.domain.cart.CartRepository;
import com.uningen.estore.domain.cart.CartService;
import com.uningen.estore.domain.order.*;
import com.uningen.estore.domain.user.AppUser;
import com.uningen.estore.domain.user.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
//@CrossOrigin(origins = "http://localhost:5173")
public class OrdersController {
    private final AppUserRepository appUserRepository;
    private final JwtService jwtService;
    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final OrdersService ordersService;
    @PostMapping
    public ResponseEntity<Long> createOrder(
            @RequestHeader(value = "Authorization", required = false) String authHeader,
            @RequestBody OrderDTO orderDTO){
        String jwtToken = authHeader.substring(7); //"Bearer <token>"
        Optional<AppUser> optionalUser = appUserRepository.findByEmail(jwtService.extractUserEmail(jwtToken));
        if(optionalUser.isPresent()){
            Optional<Cart> optionalCart = cartService.getCartById(optionalUser.get().getEmail());
            if(optionalCart.isPresent()){
                Long savedOrderId = ordersService.createOrder(optionalUser, optionalCart, orderDTO);
                return ResponseEntity.ok(savedOrderId);
            }
        }
        return ResponseEntity.unprocessableEntity().build();
    }

    @GetMapping
    public Iterable<OrderDetails> getAllOrders(
            @RequestHeader(value = "Authorization", required = false) String authHeader
    ){
        String jwtToken = authHeader.substring(7); //"Bearer <token>"
        String extractedEmail = jwtService.extractUserEmail(jwtToken);
        return ordersService.findAllOrders(extractedEmail);
    }

    @GetMapping("{id}")
    public OrderDetails getOrderById(@PathVariable Long orderId){
        return ordersService.findOrder(orderId);
    }
}


