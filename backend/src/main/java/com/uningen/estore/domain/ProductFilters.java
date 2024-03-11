package com.uningen.estore.domain;

import lombok.Data;

import java.util.Set;

@Data
public class ProductFilters {
    private Set<String> brands;
    private Set<String> types;
}
