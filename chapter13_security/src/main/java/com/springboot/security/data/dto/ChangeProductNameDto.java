package com.springboot.security.data.dto;

public class ChangeProductNameDto {

    private Long number;

    private String content;



    public ChangeProductNameDto() {
    }

    public ChangeProductNameDto(Long number, String content, String filename) {
        this.number = number;
        this.content = content;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    @Override
    public String toString() {
        return "ChangeProductNameDto{" +
                "number=" + number +
                ", content='" + content + '\'' +
                '}';
    }
}
