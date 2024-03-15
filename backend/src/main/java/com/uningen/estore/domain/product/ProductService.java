package com.uningen.estore.domain.product;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {
    private final ProductRepository productRepository;

    public Iterable<Product> viewProductList(){
        return productRepository.findAll();
    }

    public boolean checkAvailability(Long id, int qty){
        return productRepository.existsById(id) && productRepository.findById(id).get().getQuantityInStock() >= qty;
    }

    public boolean existsById(Long id){
        return productRepository.existsById(id);
    }

    public Page<Product> findProductsPaginated(
            int pageNumber,
            int pageSize,
            String orderBy,
            List<String> brands,
            List<String> categories,
            String searchTerms
            ){
        PageRequest pageRequest = switch (orderBy){
            case "priceDesc":
                yield PageRequest.of(pageNumber, pageSize, Sort.by("price").descending());
            case "price":
                yield PageRequest.of(pageNumber, pageSize, Sort.by("price"));
            default:
                yield PageRequest.of(pageNumber, pageSize, Sort.by("name"));
        };
        String searchTerm = searchTerms == null ? "" : searchTerms.trim().toLowerCase();


        if(categories != null && brands != null){
            return productRepository.findByCategoryInOrBrandInAndNameContaining(categories, brands, searchTerm, pageRequest);
        }
        else if(categories != null && brands == null){
            return productRepository.findByCategoryInAndNameContaining(categories, searchTerm, pageRequest);
        }
        else if(categories == null && brands != null){
            return productRepository.findByBrandInAndNameContaining(brands, searchTerm, pageRequest);
        }
        else {
            return productRepository.findByNameContaining(searchTerm, pageRequest);
        }
    }


    public Product viewProductDetails(Long id){
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id));
    }

    public Product addProductToCatalog(Product product){

        if(product.getId() != null && productRepository.existsById(product.getId())){
            throw new ProductAlreadyExistsException(product.getId());
        }
        return productRepository.save(Product.of(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getPictureUrl(),
                product.getCategory(),
                product.getBrand(),
                product.getQuantityInStock()));
    }

    public void removeProductFromCatalog(Long id){
        productRepository.deleteById(id);
    }

    public Product editProductDetails(Long id, Product product){
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setName(product.getName());
                    existingProduct.setDescription(product.getDescription());
                    existingProduct.setPrice(product.getPrice());
                    existingProduct.setPictureUrl(product.getPictureUrl());
                    existingProduct.setCategory(product.getCategory());
                    existingProduct.setBrand(product.getBrand());
                    existingProduct.setQuantityInStock(product.getQuantityInStock());
                    return productRepository.save(existingProduct);
                }).orElseGet(() -> addProductToCatalog(product));
    }

    public Product editProductQuantity(Long id, int newQty){
        return productRepository.findById(id)
                .map(existingProduct -> {
                    existingProduct.setQuantityInStock(existingProduct.getQuantityInStock() + newQty);
                    return productRepository.save(existingProduct);
                }).orElseThrow();
    }

}
