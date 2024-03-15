package com.uningen.estore.domain.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {
    private Long id;
    private String buyerId;
    private ShippingAddress shippingAddress;
    private Instant date;
    private List<OrderItem> orderItems;
    private double subtotal;
    private double deliveryFee;
    private OrderStatus orderStatus;
    private double total;

    public OrderDetails(Order order, List<OrderItem> orderItems){
        this.id = order.getId();
        this.buyerId = order.getBuyerId();
        this.shippingAddress = order.getShippingAddress();
        this.date = order.getOrderDate();
        this.orderItems = new ArrayList<>(orderItems);
        this.subtotal = calculateSubtotal(orderItems);
        this.deliveryFee = this.subtotal > 100.0 ? 0.0 : 5.0;
        this.orderStatus = order.getOrderStatus();
        this.total = this.subtotal + this.deliveryFee;
    }

    private double calculateSubtotal(List<OrderItem> orderItems){
        double sum = 0;
        for(OrderItem orderItem : orderItems){
            sum += orderItem.getPrice() * orderItem.getQuantity();
        }
        return sum;
    }
}
