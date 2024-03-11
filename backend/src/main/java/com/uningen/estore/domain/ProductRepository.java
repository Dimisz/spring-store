package com.uningen.estore.domain;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Optional<Product> findById(Long id);
    boolean existsById(Long id);
//    List<Product> findAllByCategory(String category, Pageable pageable);
//    Page<Product> getAllProductsWithPagination(Pageable pageRequest);
    @Transactional
    void deleteById(Long id);
}
