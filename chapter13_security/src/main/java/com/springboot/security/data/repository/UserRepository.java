package com.springboot.security.data.repository;

import com.springboot.security.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

// 예제 13.7
public interface UserRepository extends JpaRepository<User, Long> {

    User getByUid(String uid);

    User findByName(String name);

}