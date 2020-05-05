package com.lian.myLambda.service;

import com.lian.myLambda.model.Score;

import java.util.List;
import java.util.Map;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 15:51
 */
public interface ScoreService {

    List<Score> findAllScoreList();

    /**
     * 获取所有人员信息
     * @return
     */
    List<Score> findPersonListByScoreList();

    /**
     * 获取每个成员的平均分
     * @return
     */
    Map<Integer,Double> findAllPersonAverageScore();

    /**
     * 获取每个人的总分
     * @return
     */
    Map<Integer,Double> findAllTotalScoreEveryOne();

    Map<String,Object> findStatisticOfCourse(String course);
}
