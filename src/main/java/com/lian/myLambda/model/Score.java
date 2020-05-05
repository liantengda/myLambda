package com.lian.myLambda.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * 此处的分数类，我专门设计成了不够第二范式的
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 15:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Score {
    private Integer scoreId;
    private Integer personId;
    private String name;
    private String course;
    private Double score;
    private Long createTime;
    private Long updateTime;

    public Score(Integer scoreId, Integer personId, String course, Double score, Long createTime,String name) {
        this.scoreId = scoreId;
        this.personId = personId;
        this.course = course;
        this.score = score;
        this.createTime = createTime;
        this.name = name;
    }
}
