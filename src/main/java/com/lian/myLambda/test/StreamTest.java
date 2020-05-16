package com.lian.myLambda.test;

import com.lian.myLambda.model.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/16 10:13
 */
public class StreamTest {
    public static void main(String[] args) throws Exception {
        //从一段话中挑出单词来
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\util\\javaEngineer\\javaproject\\myLambda\\src\\main\\resources\\wordTest")));
        String blankSpace = " ";
        Stream<String> stringStream = reader.lines();
        Stream<String> streamCondition1 = stringStream.flatMap(line -> Stream.of(line.split(blankSpace)));
        Stream<String> streamCondition2 = streamCondition1.filter(word -> word.length() > 0);
        Stream<String> streamCondition3 = streamCondition2.filter(word -> word.contains("he"));
        List<String> streamResult = streamCondition3.collect(Collectors.toList());
        System.out.println("筛选出来的单词数组为"+streamResult);
        reader.close();

        long now = System.currentTimeMillis();
        Person black = new Person(1, "落葉吹雪", "ted", now, "1101",1,"中国");
        Person red = new Person(2, "ココア", "kokoa", now, "1102",1,"中国");
        Person yellow = new Person(3, "千尋", "chihiro", now, "1103",2,"中国");
        Person green = new Person(4, "千夜", "chiya", now, "1104",2,"中国");
        Person red2 = new Person(5, "小百合", "koyuri", now, "1105",3,"中国");
        Person[] personArray = new Person[]{black,red,green,yellow,red2};
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(black);
        personArrayList.add(red);
        personArrayList.add(yellow);
        personArrayList.add(green);
        personArrayList.add(red2);
        //根据特定属性去重,并输出特定属性
        personArrayList.stream().collect(Collectors.groupingBy(p->p.getJapaneseName(),Collectors.summingLong(Person::getPersonId))).forEach((name, personId)-> System.out.println("name:"+name+":"+"personId"+personId));
        Stream<Person> stream = personArrayList.stream();
        Map<String, Long> collect = stream.collect(Collectors.groupingBy(p -> p.getJapaneseName(), Collectors.summingLong(Person::getPersonId)));
        collect.forEach((name,personId)-> System.out.println("name:"+name+":"+"personId"+personId));
        personArrayList.forEach(value-> System.out.println(value));
    }
}
