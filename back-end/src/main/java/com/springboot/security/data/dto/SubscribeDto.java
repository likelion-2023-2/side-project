package com.springboot.security.data.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.springboot.security.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SubscribeDto {
    private User addUser;
}
