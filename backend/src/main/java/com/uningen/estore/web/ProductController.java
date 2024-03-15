package com.uningen.estore.web;

import com.uningen.estore.domain.product.Product;
import com.uningen.estore.domain.product.ProductFilters;
import com.uningen.estore.domain.product.ProductService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

//@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public Page<Product> getProductsPaginated(
            @RequestParam int pageNumber,
            @RequestParam int pageSize,
            @RequestParam String orderBy,
            @RequestParam(required = false) List<String> brands,
            @RequestParam(required = false) List<String> categories,
            @RequestParam(required = false) String searchTerm,
            @CookieValue(value = "userid", defaultValue = "unknown") String userIdFromCookie,
            HttpServletResponse httpServletResponse
    ){
        // as soon as user enters the app, random id is assigned
        // the id is stored as a cookie to track user actions
        // before user logs in
        // upon successful login the cookie is deleted and real userId is used
        if(userIdFromCookie.equals("unknown")){
            Cookie cookie = new Cookie("userid", UUID.randomUUID().toString());
            cookie.setMaxAge(12 * 60 * 60); // 12 hours
            cookie.setSecure(true);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            httpServletResponse.addCookie(cookie);
        }

        return productService.findProductsPaginated(pageNumber, pageSize, orderBy, brands, categories, searchTerm);
    }

    @GetMapping("filters")
    public ProductFilters getFilters(HttpServletRequest httpServletRequest){
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
