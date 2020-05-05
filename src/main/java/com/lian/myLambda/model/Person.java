package com.lian.myLambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ted
 * @date 2020/5/1 1:09
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private String japaneseName;
    private Integer personId;
    private String englishName;
    private String country;
    private Integer grade;
    private Long createTime;
    private Long updateTime;
    private String idCard;

    public Person(Integer personId,String japaneseName, String englishName,Long createTime,String idCard,Integer grade,String country){
        this.japaneseName = japaneseName;
        this.personId = personId;
        this.englishName = englishName;
        this.createTime = createTime;
        this.idCard = idCard;
        this.grade = grade;
        this.country = country;
    }
}
