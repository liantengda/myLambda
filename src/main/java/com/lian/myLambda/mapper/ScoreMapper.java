package com.lian.myLambda.mapper;

import com.lian.myLambda.model.Score;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 *  注意：我专门把score表设计成了不符合第二范式的。（也就是数据十分冗余，例如用户姓名只依靠personId就可以知道了，而这里写了四遍）
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 15:31
 */
@Repository
public class ScoreMapper {
    private static Map<Integer,Score> scoreTable = new HashMap<>();
    static {
        long now = System.currentTimeMillis();
        scoreTable.put(1,new Score(1,1,"钱没意思",100.00,now,"马秀才"));
        scoreTable.put(2,new Score(1,1,"女性经济",99.99,now,"马秀才"));
        scoreTable.put(3,new Score(1,1,"福报哲学",99.8,now,"马秀才"));
        scoreTable.put(4,new Score(2,2,"脸盲修养",100.00,now,"刘解元"));
        scoreTable.put(5,new Score(2,2,"兄弟论",99.00,now,"刘解元"));
        scoreTable.put(6,new Score(2,2,"老实人",99.00,now,"刘解元"));
        scoreTable.put(7,new Score(3,3,"小目标",100.00,now,"王进士"));
        scoreTable.put(8,new Score(3,3,"够意思",100.00,now,"王进士"));
        scoreTable.put(9,new Score(3,3,"有想法",100.00,now,"王进士"));
        scoreTable.put(10,new Score(4,4,"这还行",100.00,now,"撒将军"));
        scoreTable.put(11,new Score(4,4,"能凑合",99.00,now,"撒将军"));
        scoreTable.put(12,new Score(4,4,"十分巧",99.00,now,"撒将军"));
        scoreTable.put(13,new Score(1,1,"吹牛逼",100.00,now,"马秀才"));
        scoreTable.put(14,new Score(2,2,"吹牛逼",100.00,now,"刘解元"));
        scoreTable.put(15,new Score(3,3,"吹牛逼",100.00,now,"王进士"));
        scoreTable.put(16,new Score(4,4,"吹牛逼",100.00,now,"撒将军"));

    }

    /**
     * map->list
     *
     * 获得所有分数集合
     * @return
     */
    public List<Score> findAllScoreList(){
        List<Score> collect = scoreTable.entrySet().stream().map(s -> s.getValue()).collect(Collectors.toList());
        return collect;
    }

    /**
     * 根据某属性去重之后在返回集合
     *
     * 根据personId去重返回分数列表--->为了获得每个人员的简单信息（实际开发中，一般只取id，然后关联用户表，此处取整个集合）。
     * @return
     */
    public List<Score> findAllPersonListByScoreList(){
        List<Score> allScoreList = findAllScoreList();
        ArrayList<Score> collect = allScoreList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Score::getPersonId))
        ), ArrayList::new));
        return collect;
    }

    /**
     * 根据某属性分组后，再对其中某些属性进行算数运算（平均，加和等等）
     *
     * 根据personId分组后，求得每个人员的平均分
     * @return
     */
    public Map<Integer,Double> findAllAverageScoreGroupByPersonId(){
        List<Score> allScoreList = findAllScoreList();
        Map<Integer, Double> allAverageScore = allScoreList.stream().collect(Collectors.groupingBy(Score::getPersonId, Collectors.averagingDouble(Score::getScore)));
        return allAverageScore;
    }

    /**
     * 根据personId分组后，求得每个人员的分数总和
     * @return
     */
    public Map<Integer,Double> findTotalScoreEveryOne(){
        List<Score> allScoreList = findAllScoreList();
        Map<Integer, Double> totalScoreCollect = allScoreList.stream().collect(Collectors.groupingBy(Score::getPersonId, Collectors.summingDouble(Score::getScore)));
        return totalScoreCollect;
    }

    /**
     * 取得分数列表中某门课程的所有统计数据（最高分，最低分，平均分，总分，以及学习这们课程的人数）
     * @param course
     * @return
     */
    public Map<String,Object> findStatisticOfCourse(String course){
        List<Score> allScoreList = findAllScoreList();
        DoubleSummaryStatistics courseStatistics = allScoreList.stream().filter(p -> p.getCourse().equals(course)).mapToDouble(item -> item.getScore()).summaryStatistics();
        HashMap<String, Object> courseStatisticMap = new HashMap<>();
        courseStatisticMap.put("max",courseStatistics.getMax());
        courseStatisticMap.put("min",courseStatistics.getMin());
        courseStatisticMap.put("sun",courseStatistics.getSum());
        courseStatisticMap.put("count",courseStatistics.getCount());
        courseStatisticMap.put("average",courseStatistics.getAverage());
        return courseStatisticMap;
    }
}
