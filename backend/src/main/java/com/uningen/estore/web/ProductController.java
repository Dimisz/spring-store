package com.uningen.estore.web;

import com.uningen.estore.domain.Product;
import com.uningen.estore.domain.ProductFilters;
import com.uningen.estore.domain.ProductService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

//    @GetMapping
//    public Iterable<Product> getAllProducts(){
//        return productService.viewProductList();
//    }

////    @GetMapping("/paginated")
//    @GetMapping
//    public HttpResponse<PagedListHolder<Product>> getAllProductsPaginated(
//            @RequestParam int pageNumber,
//            @RequestParam int pageSize,
//            @RequestParam String orderBy
//            ){
//        return productService.findAllProductsPaginated(pageNumber, pageSize, orderBy);
//    }

    @GetMapping
    public Page<Product> getProductsPaginated(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String orderBy,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) String searchTerm
    ){
        List<String> searchTerms = null;
        if(searchTerm != null){
            searchTerms = Arrays.stream(searchTerm.toLowerCase().trim().split(" ")).toList();
        }

//        System.out.println(types);
        return productService.findProductsPaginated(pageNumber, pageSize, orderBy, brands, categories, searchTerms);

    }

//    @GetMapping("brand")
//    public Page<Product> getByBrand(@RequestParam(required = false) List<String> brand, @RequestParam(required = false) List<String> category){
//        return productService.findAllByBrand(brand, category);
//    }

    @GetMapping("filters")
    public ProductFilters getFilters(){
        Set<String> brands = new HashSet<>();
        Set<String> types = new HashSet<>();
        Iterable<Product> products = productService.viewProductList();
        for(Product product : products){
            brands.add(product.getBrand());
            types.add(product.getCategory());
        }
        ProductFilters filters = new ProductFilters();
        filters.setBrands(brands);
        filters.setTypes(types);
        return filters;
    }

    @GetMapping("{id}")
    public Product getById(@PathVariable Long id){
        return productService.viewProductDetails(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product postProduct(@Valid @RequestBody Product product){
        return productService.addProductToCatalog(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long id){
        productService.removeProductFromCatalog(id);
    }

    @PutMapping("{id}")
    public Product putBook(@PathVariable Long id, @Valid @RequestBody Product product){
        return productService.editProductDetails(id, product);
    }
}
