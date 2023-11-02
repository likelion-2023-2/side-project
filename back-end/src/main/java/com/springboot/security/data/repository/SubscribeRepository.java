package com.springboot.security.data.repository;

import com.springboot.security.data.entity.Subscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.springboot.security.data.entity.User;

import java.util.List;

public interface SubscribeRepository extends JpaRepository<Subscribe, Long> {
    Subscribe save(Subscribe subscribe);

    void delete(Subscribe subscribe);

    Subscribe findByaddUser(User user);
    List<Subscribe> findBytoUser(User user);

    Subscribe findById(int id);


}
