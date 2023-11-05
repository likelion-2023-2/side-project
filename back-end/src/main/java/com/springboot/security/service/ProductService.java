package com.springboot.security.service;


import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.data.entity.User;

import java.util.List;

public interface ProductService {

    ProductResponseDto getProduct(Long number);

    ProductResponseDto saveProduct(ProductDto productDto, User user);

    ProductResponseDto changeProductContent(Long number, String content) throws Exception;

    void deleteProduct(Long number) throws Exception;

    public List<ProductResponseDto> getProductList();

}