package com.springboot.security.data.dto;

import com.springboot.security.data.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoDto {
    private String uid;
    private String name;
    private List<Product> posts;

}
