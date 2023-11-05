package com.springboot.security.service.impl;

import com.springboot.security.data.dto.ProductResponseDto;
import com.springboot.security.data.dto.SubscribeDto;
import com.springboot.security.data.dto.UserInfoDto;
import com.springboot.security.data.entity.Product;
import com.springboot.security.data.entity.Subscribe;
import com.springboot.security.data.entity.User;
import com.springboot.security.data.repository.SubscribeRepository;
import com.springboot.security.data.repository.UserRepository;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class SubscribeService {
    @Autowired
    SubscribeRepository subscribeRepository;
    @Autowired
    UserRepository userRepository;

    @Transactional
    public void saveSubscribe(String username, String currentUser) {
        // 사용자 정보를 가져오기
        User addUser = userRepository.findByName(username);
        User toUser = userRepository.findByName(currentUser);

        if (toUser != null && addUser != null) {
            // 이미 구독한 경우 확인
            boolean isSubscribed = toUser.getSubscriptions().stream()
                    .anyMatch(subscribe -> subscribe.getAddUser().equals(addUser));

            if (isSubscribed) {
                // 이미 구독한 경우 메시지 출력
                throw new RuntimeException("이미 구독하셨습니다.");
            }

            // Subscribe 엔티티 생성 및 toUser 및 addUser 설정
            Subscribe subscribe = new Subscribe();
            subscribe.setToUser(toUser);
            subscribe.setAddUser(addUser);

            // Subscribe 엔티티 저장
            subscribeRepository.save(subscribe);

            // toUser의 subscribe 리스트에 subscribe 엔티티 추가
            toUser.getSubscriptions().add(subscribe);
        }
    }

    @Transactional
    public void deleteSubscribe(String username) {

        User user = userRepository.findByName(username);
        Subscribe subscribe = subscribeRepository.findByaddUser(user);
        if (user != null) {
            subscribeRepository.delete(subscribe);
        }
    }

    @Transactional
    public List<UserInfoDto> findSubScibeInfo(UserDetails user){
        Hibernate.initialize(user);
        User toUser = (User)user;


        List<Subscribe> subscribeList = subscribeRepository.findBytoUser(toUser);


        // ProductResponseDto로 변환
        List<SubscribeDto> subscribeDtoList = subscribeList.stream()
                .map(subscribe -> {
                    SubscribeDto subscribeDto = new SubscribeDto();
                    Hibernate.initialize(subscribe.getAddUser());
                    subscribeDto.setAddUser(subscribe.getAddUser());
                    return subscribeDto;
                })
                .collect(Collectors.toList());

        List<UserInfoDto> userInfoDtoList = new ArrayList<UserInfoDto>();
        subscribeDtoList.forEach((subscribeDto)->{
            UserInfoDto userInfoDto = new UserInfoDto();
            userInfoDto.setUid(subscribeDto.getAddUser().getUid());
            userInfoDto.setName(subscribeDto.getAddUser().getName());
            userInfoDto.setPosts(subscribeDto.getAddUser().getPosts());
            userInfoDtoList.add(userInfoDto);
        });

        return userInfoDtoList;
    }
}
