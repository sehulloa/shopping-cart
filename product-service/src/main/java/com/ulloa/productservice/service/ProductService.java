package com.ulloa.productservice.service;

import com.ulloa.productservice.dto.ProductDto;

import java.util.List;

public interface ProductService {

    List<ProductDto> getAllProducts();
    ProductDto getProductById(Long id);
}
