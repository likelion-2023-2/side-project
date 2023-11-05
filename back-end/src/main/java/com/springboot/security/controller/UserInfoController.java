package com.springboot.security.controller;

import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.data.dto.UserInfoDto;
import com.springboot.security.service.ProductService;
import com.springboot.security.service.impl.UserDetailsServiceImpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.hibernate.Hibernate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import  com.springboot.security.data.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserInfoController {

    private final Logger LOGGER = LoggerFactory.getLogger(UserInfoController.class);
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    UserInfoController(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService=userDetailsService;
    }

    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("{username}")
    public ResponseEntity<UserInfoDto> getUserInfo(@PathVariable String username) {
        long currentTime = System.currentTimeMillis();
        LOGGER.info("[UserDetails] Request Data :: userName : {}", username);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        User user=(User)userDetails;
        Hibernate.initialize(user.getSubscriptions());
        Hibernate.initialize(user.getRoles());

        if (userDetails == null) {
            return ResponseEntity.notFound().build();
        }


        LOGGER.info(
                "[getProduct] Response Data :: productId : {}, productContent : {}, productFilename : {}",
                userDetails.getUsername()
        );
        LOGGER.info("[UserDetails] Response Time : {}ms", System.currentTimeMillis() - currentTime);

        UserInfoDto userinfo=new UserInfoDto();
        userinfo.setUid(user.getUid());
        userinfo.setName(user.getName());
        userinfo.setPosts(user.getPosts());

        return ResponseEntity.ok(userinfo);
    }
}
