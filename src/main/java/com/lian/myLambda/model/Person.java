package com.lian.myLambda.model;

import lombok.Data;

/**
 * @author Ted
 * @date 2020/5/1 1:09
 */
@Data
public class Person {
    private String name;
    private Long personId;
    private String englishName;

    public Person(String name, Long personId, String englishName){
        this.name = name;
        this.personId = personId;
        this.englishName = englishName;
    }
}
