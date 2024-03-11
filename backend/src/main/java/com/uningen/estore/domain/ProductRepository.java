package com.uningen.estore.domain;

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
//    List<Product> findAllByCategory(String category);
//    List<Product> findAllByBrand(List<String> brand);
//    List<Product> findByBrandInOrCategoryInIgnoreCase(Collection<String> brands, Collection<String> categories);
//    Page<Product> findByBrandInOrCategoryInIgnoreCase(Collection<String> brands, Collection<String> categories, Pageable pageable);
    Page<Product> findByNameInOrBrandInOrCategoryInIgnoreCase(Collection<String> brands, Collection<String> categories, Collection<String> searchTerms,Pageable pageable);

//    List<Product> findAllByCategory(String category, Pageable pageable);
//    Page<Product> getAllProductsWithPagination(Pageable pageRequest);
    @Transactional
    void deleteById(Long id);
}
