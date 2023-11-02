package com.springboot.security.controller;

import com.springboot.security.data.dto.SubscribeDto;
import com.springboot.security.data.entity.Subscribe;
import com.springboot.security.data.entity.User;
import com.springboot.security.data.repository.SubscribeRepository;
import com.springboot.security.data.repository.UserRepository;
import com.springboot.security.service.impl.SubscribeService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@RequestMapping("/subscribe")
public class SubscribeApiController {
    @Autowired
    SubscribeService subscribeService;
    @Autowired
    SubscribeRepository subscribeRepository;


    @Autowired
    UserRepository userRepository;
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("{username}")
    public ResponseEntity<String> subscribe(@PathVariable String username){
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String currentUser = userDetails.getUsername();
        try {
            subscribeService.saveSubscribe(username, currentUser);
            return ResponseEntity.ok().body("구독 성공");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }


    }
    @Transactional
    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/get/{username}")
    public ResponseEntity<List<SubscribeDto>> getSubscribe(@PathVariable String username) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<SubscribeDto> subscribeDtoList = subscribeService.findSubScibeInfo(userDetails);

        if (subscribeDtoList == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(subscribeDtoList);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "X-AUTH-TOKEN", value = "로그인 성공 후 발급 받은 access_token", required = true, dataType = "String", paramType = "header")
    })
    @GetMapping("/delete/{username}")
    public ResponseEntity<String> unSubscribe(@PathVariable String username){

        subscribeService.deleteSubscribe(username);

        return ResponseEntity.ok().body("구독해제성공");


    }
}
