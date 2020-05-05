package com.lian.myLambda.model;

import lombok.Data;

/**
 * @author Ted
 * @date 2020/5/1 1:09
 */
@Data
public class Person {
    private String JapaneseName;
    private Integer personId;
    private String englishName;
    private Integer grade;
    private Long createTime;
    private Long updateTime;
    private String idCard;

    public Person(Integer personId,String JapaneseName, String englishName,Long createTime,String idCard){
        this.JapaneseName = JapaneseName;
        this.personId = personId;
        this.englishName = englishName;
        this.createTime = createTime;
        this.idCard = idCard;
    }
}
