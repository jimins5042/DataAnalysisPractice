package com.example.DataAnalysisPractice.member;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Member {

    private Long id;
    private String phoneNumber;
    private String name;
    private Integer sex;
    private Integer age;
    private String location;

    public Member(){

    }
    public Member(String name, Integer sex, Integer age, String phoneNumber, String location) {
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.sex = sex;
        this.age = age;
        this.location = location;
    }
}
