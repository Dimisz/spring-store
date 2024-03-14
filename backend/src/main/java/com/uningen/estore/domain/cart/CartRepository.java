package com.uningen.estore.domain.cart;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

//@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    Optional<Cart> findById(String userEmail);
    boolean existsById(String email);

    @Transactional
    void removeById(String email);
}
