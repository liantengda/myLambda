package com.lian.myLambda.service.impl;

import com.lian.myLambda.mapper.ScoreMapper;
import com.lian.myLambda.model.Score;
import com.lian.myLambda.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 15:51
 */
@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    ScoreMapper scoreMapper;

    @Override
    public List<Score> findAllScoreList() {
        List<Score> allScoreList = scoreMapper.findAllScoreList();
        return allScoreList;
    }

    @Override
    public List<Score> findPersonListByScoreList() {
        List<Score> allpersonList = scoreMapper.findAllPersonListByScoreList();
        return allpersonList;
    }

    @Override
    public Map<Integer, Double> findAllPersonAverageScore() {
        Map<Integer, Double> allAverageScore = scoreMapper.findAllAverageScoreGroupByPersonId();
        return allAverageScore;
    }

    @Override
    public Map<Integer, Double> findAllTotalScoreEveryOne() {
        Map<Integer, Double> totalScoreEveryOne = scoreMapper.findTotalScoreEveryOne();
        return totalScoreEveryOne;
    }

    @Override
    public Map<String, Object> findStatisticOfCourse(String course) {
        Map<String, Object> statisticOfCourse = scoreMapper.findStatisticOfCourse(course);
        return statisticOfCourse;
    }
}
