package com.lian.myLambda.controller;

import com.lian.myLambda.common.core.pojo.response.R;
import com.lian.myLambda.model.Score;
import com.lian.myLambda.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 15:51
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Autowired
    ScoreService scoreService;

    /**
     * 查询所有分数列表
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public R<List<Score>> list(){
        return new R<>(scoreService.findAllScoreList());
    }

    /**
     * 查询正在上课的人员简单信息列表
     * @return
     */
    @RequestMapping(value = "/listPerson",method = RequestMethod.GET)
    @ResponseBody
    public R<List<Score>> listPerson(){
        return new R<>(scoreService.findPersonListByScoreList());
    }

    /**
     * 查询每个人的平均分
     * @return
     */
    @RequestMapping(value = "/listAverage",method = RequestMethod.GET)
    @ResponseBody
    public R<Map<Integer,Double>> listAllPersonAverageScore(){
        return new R<>(scoreService.findAllPersonAverageScore());
    }

    /**
     * 查询每个人的总分数
     * @return
     */
    @RequestMapping(value = "/listTotalScore",method = RequestMethod.GET)
    @ResponseBody
    public R<Map<Integer,Double>> listAllTotalScoreEveryOne(){
        return new R<>(scoreService.findAllTotalScoreEveryOne());
    }

    /**
     * 取得分数列表中某门课程的所有统计数据（最高分，最低分，平均分，总分，以及学习这们课程的人数）
     * @param course
     * @return
     */
    @RequestMapping(value = "/courseStatistic",method = RequestMethod.GET)
    @ResponseBody
    public R<Map<String,Object>> findStatisticOfCourse(String course){
        return new R<>(scoreService.findStatisticOfCourse(course));
    }
}
