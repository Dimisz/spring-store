package com.uningen.estore.domain.order;


import com.uningen.estore.domain.cart.Cart;
import com.uningen.estore.domain.cart.CartRepository;
import com.uningen.estore.domain.product.Product;
import com.uningen.estore.domain.product.ProductRepository;
import com.uningen.estore.domain.product.ProductService;
import com.uningen.estore.domain.user.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.*;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;
    public Long createOrder(Optional<AppUser> optionalUser, Optional<Cart> optionalCart, OrderDTO orderDTO){
        Map<Long, Integer> productFromOptionalCart = optionalCart.get().getCartProducts();
        Map<Long, Integer> copiedProducts = new HashMap<>();
        for(Long productId : productFromOptionalCart.keySet()){
            copiedProducts.put(productId, productFromOptionalCart.get(productId));
        }

        Order order = new Order(null,
                optionalUser.get().getEmail(),
                orderDTO.getShippingAddress(),
                Instant.now(),
                copiedProducts,
                OrderStatus.PAYMENT_RECEIVED,
                "paymentIDFromStripe"
        );
        Order savedOrder = orderRepository.save(order);
        if(orderDTO.isSaveAddress()){
            optionalUser.get().setShippingAddress(orderDTO.getShippingAddress());
        }
        cartRepository.removeById(optionalUser.get().getEmail());
        if(savedOrder != null){
            return savedOrder.getId();
        }
        return -1L; // invalid exit status
    }

    public List<OrderDetails> findAllOrders(String extractedEmail){
        if(extractedEmail != null){
            List<OrderDetails> allOrders = new ArrayList<>();
            Iterable<Order> ordersFromDB = orderRepository.findAllByBuyerId(extractedEmail);
            for(Order currentOrder : ordersFromDB){
                allOrders.add(new OrderDetails(currentOrder, extractOrderItemsFromOrder(currentOrder)));
            }
            return allOrders;
        }
        throw new NoOrdersFoundException();
    }

    public OrderDetails findOrder(Long orderId){
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if(optionalOrder.isPresent()){
            return new OrderDetails(optionalOrder.get(), extractOrderItemsFromOrder(optionalOrder.get()));
        }
        throw new OrderNotFoundException(orderId);
    }

    public List<OrderItem> extractOrderItemsFromOrder(Order order){
        // reshape order items from Map to List
        List<OrderItem> orderItemsList = new ArrayList<>();
        Map<Long, Integer> orderProducts = order.getOrderItems();
        List<OrderItem> orderItems = new ArrayList<>();
        if(orderProducts != null && !orderProducts.isEmpty()){
            for(Long productId : orderProducts.keySet()){
                Product product = productService.viewProductDetails(productId);
                Integer productQty = orderProducts.get(productId);
                System.out.println(("Adding product"));
                OrderItem orderItem = new OrderItem(product.getId(), product.getName(), product.getPictureUrl(), product.getPrice(), productQty);
                orderItems.add(orderItem);
            }
        }
        return orderItems;
    }
}
