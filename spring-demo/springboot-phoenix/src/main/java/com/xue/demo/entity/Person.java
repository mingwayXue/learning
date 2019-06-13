package com.xue.demo.entity;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@ToString
@RequiredArgsConstructor
public class Person {
    private Integer id;
    private String name;
    private Integer age;
}
