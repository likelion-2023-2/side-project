package com.springboot.security.service.impl;

import com.springboot.security.data.repository.ProductRepository;
import com.springboot.security.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService {
    private final Logger LOGGER = LoggerFactory.getLogger(UserInfoService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
