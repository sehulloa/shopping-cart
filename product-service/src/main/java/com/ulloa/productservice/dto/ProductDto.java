package com.ulloa.productservice.dto;

import lombok.Data;

@Data
public class ProductDto {

    private Long id;
    private String title;
    private String description;
    private Double price;
    private String category;
    private String image;

}
