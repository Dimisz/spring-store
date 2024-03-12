package com.uningen.estore.domain.product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

public interface ProductRepository extends ListCrudRepository<Product, Long>, PagingAndSortingRepository<Product, Long> {
    Optional<Product> findById(Long id);
    boolean existsById(Long id);
    Page<Product> findByCategoryInOrBrandInAndNameContaining(Collection<String> categories, Collection<String> brands, String searchTerm, Pageable pageRequest);
    Page<Product> findByCategoryInAndNameContaining(Collection<String> categories, String searchTerm, Pageable pageRequest);
    Page<Product> findByBrandInAndNameContaining(Collection<String> brands, String searchTerm, Pageable pageRequest);
    Page<Product> findByNameContaining(String searchTerm, Pageable pageRequest);
    @Transactional
    void deleteById(Long id);
}
