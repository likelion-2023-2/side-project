package com.springboot.security.data.repository;


import com.springboot.security.data.entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product, Long> {

    /*
    아래 메소드는 책에서 소개하고 있는 Repository의 메소드 명명 규칙 예시입니다.
    */
    List<Product> findByContentLike(String content);

    List<Product> findByContentContaining(String content);

    List<Product> findByContentStartingWith(String content);

    List<Product> findByContentStartsWith(String content);

    List<Product> findByContentEndsWith(String content);



}
