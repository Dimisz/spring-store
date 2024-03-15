package com.uningen.estore.domain.order;

import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
    Iterable<Order> findAllByBuyerId(String email);
}
