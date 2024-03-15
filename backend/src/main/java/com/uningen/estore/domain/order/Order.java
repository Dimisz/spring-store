package com.uningen.estore.domain.order;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buyerId;
    @Embedded
    private ShippingAddress shippingAddress;
    private Instant orderDate;

    @ElementCollection
    @CollectionTable(name = "order_product_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_id")
    @Column(name = "product_qty")
    private Map<Long, Integer> orderItems;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;
    private String paymentIntentId;
}
