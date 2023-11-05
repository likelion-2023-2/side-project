package com.springboot.security.service.impl;


import com.springboot.security.data.dto.ProductDto;
import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.data.entity.Product;
import com.springboot.security.data.entity.User;
import com.springboot.security.data.repository.ProductRepository;
import com.springboot.security.service.ProductService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class ProductServiceImpl implements ProductService {

    private final Logger LOGGER = LoggerFactory.getLogger(ProductServiceImpl.class);
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponseDto getProduct(Long number) {
        LOGGER.info("[getProduct] product number : {}", number);
        Product product = productRepository.findById(number).orElseThrow(RuntimeException::new);

        LOGGER.info(
            "[getProduct] found Product :: productId : {}, productName : {}, productPrice : {}, productStock : {}",
            product.getNumber(), product.getContent(), product.getFilename());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(product.getNumber());
        productResponseDto.setContent(product.getContent());
        productResponseDto.setFilename(product.getFilename());
        productResponseDto.setWriter(product.getWriter());

        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getProductList() {
        LOGGER.info("[getProductList] Retrieving the list of all products.");

        // 상품 목록 조회
        List<Product> productList = productRepository.findAll();

        // ProductResponseDto로 변환
        List<ProductResponseDto> productResponseList = productList.stream()
                .map(product -> {
                    ProductResponseDto productResponseDto = new ProductResponseDto();
                    productResponseDto.setNumber(product.getNumber());
                    productResponseDto.setContent(product.getContent());
                    productResponseDto.setFilename(product.getFilename());
                    productResponseDto.setWriter(product.getWriter());
                    return productResponseDto;
                })
                .collect(Collectors.toList());

        LOGGER.info("[getProductList] Found {} products.", productResponseList.size());

        return productResponseList;
    }
    @Transactional
    @Override
    public ProductResponseDto saveProduct(ProductDto productDto, User user) {
        LOGGER.info("[saveProduct] productName : {}", productDto.getContent());
        Product product = new Product();

        product.setContent(productDto.getContent());
        product.setFilename(productDto.getFilename());
        product.setCreatedAt(LocalDateTime.now());
        product.setUpdatedAt(LocalDateTime.now());
        product.setWriter(user);

        Product savedProduct = productRepository.save(product);
        LOGGER.info("[saveProduct] saved ProductId : {}", savedProduct.getNumber());

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(savedProduct.getNumber());
        productResponseDto.setContent(savedProduct.getContent());
        productResponseDto.setFilename(savedProduct.getFilename());
        Hibernate.initialize(savedProduct.getWriter());
        productResponseDto.setWriter(savedProduct.getWriter());

        return productResponseDto;
    }


    @Override
    public ProductResponseDto changeProductContent(Long number, String content) {
        LOGGER.info("[changeProductName] request productId : {}", number);
        Product foundProduct = productRepository.findById(number)
            .orElseThrow(RuntimeException::new);
        LOGGER.info("[changeProductName] found Product's name : {}", foundProduct.getContent());
        foundProduct.setContent(content);
        LOGGER.info("[changeProductName] change Product's name : {}", content);

        Product changedProduct = productRepository.save(foundProduct);

        ProductResponseDto productResponseDto = new ProductResponseDto();
        productResponseDto.setNumber(changedProduct.getNumber());
        productResponseDto.setFilename(changedProduct.getFilename());
        productResponseDto.setContent(changedProduct.getContent());

        return productResponseDto;
    }

    @Override
    public void deleteProduct(Long number) {
        LOGGER.info("[deleteProduct] delete ProductId : {}", number);
        productRepository.deleteById(number);
    }
}
