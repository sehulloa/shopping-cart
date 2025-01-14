package com.ulloa.productservice.service;

import com.ulloa.productservice.dto.ProductDto;
import com.ulloa.productservice.exception.ProductServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final RestTemplate restTemplate;
    private final String externalApiUrl;

    public ProductServiceImpl(RestTemplate restTemplate,
                              @Value("${product.api.url:https://fakestoreapi.com/products}") String externalApiUrl) {
        this.restTemplate = restTemplate;
        this.externalApiUrl = externalApiUrl;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        try {
            ProductDto[] products = restTemplate.getForObject(externalApiUrl, ProductDto[].class);
            return Arrays.asList(products);
        } catch (Exception ex) {
            throw new ProductServiceException("Failed to fetch products from external API", ex);
        }
    }

    @Override
    public ProductDto getProductById(Long id) {
        try {
            return restTemplate.getForObject(externalApiUrl + "/" + id, ProductDto.class);
        } catch (Exception ex) {
            throw new ProductServiceException("Failed to fetch product by ID", ex);
        }
    }

}
