package com.springboot.security.data.dto;

import com.springboot.security.data.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductResponseDto {

    private Long number;

    private String content;

    private String filename;

    private User writer;

}
