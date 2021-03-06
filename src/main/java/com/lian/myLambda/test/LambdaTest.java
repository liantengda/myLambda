package com.lian.myLambda.test;

import com.lian.myLambda.model.Person;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/5 12:16
 */
public class LambdaTest {

    public static void main(String[] args) throws Exception {
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
        personArrayList.forEach(value-> System.out.println(value));
        System.out.println("---------->");
        ArrayList<Person> list2 = new ArrayList<>();
        //数组排序
        Arrays.sort(personArray,(Person p1,Person p2)->p2.getPersonId()-p1.getPersonId());

        for (Person p:personArray) {
            System.out.println(p);
        }

        //数组中挑选符合某条件的属性
        Person[] objects = Stream.of(personArray).filter(person -> person.getEnglishName().contains("ch")).toArray(Person[]::new);
        for (Object o: objects) {
            System.out.println("挑选数组中符合某种属性的人"+o);
        }

        //从一段话中挑出单词来
        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\util\\javaEngineer\\javaproject\\myLambda\\src\\main\\resources\\wordTest")));
        String REGEXP = " ";
        Stream<String> stringStream = reader.lines();
        Stream<String> streamCondition1 = stringStream.flatMap(line -> Stream.of(line.split(REGEXP)));
        Stream<String> streamCondition2 = streamCondition1.filter(word -> word.length() > 2);
        Stream<String> streamCondition3 = streamCondition2.filter(word -> word.contains("he"));
        List<String> streamResult = streamCondition3.collect(Collectors.toList());
        System.out.println("筛选出来的单词数组为"+streamResult);
        reader.close();
        //根据特定属性去重,并输出特定属性
        personArrayList.stream().collect(Collectors.groupingBy(p->p.getJapaneseName(),Collectors.summingLong(Person::getPersonId))).forEach((name, personId)-> System.out.println("name:"+name+":"+"personId"+personId));
        personArrayList.forEach(value-> System.out.println(value));
        //根据特定属性将集合分组，key -> List<Person>
        Map<String, List<Person>> collect15 = personArrayList.stream().collect(Collectors.groupingBy(p -> p.getEnglishName()));
        System.out.println(collect15);
        //根据特定属性将集合分组，并转换为map<key1,value2>
        Map<String, Long> collect16 = personArrayList.stream().collect(Collectors.groupingBy(p -> p.getJapaneseName(), Collectors.summingLong(Person::getPersonId)));
        //将某属性集合到一个集合中
        List<String> collect = personArrayList.stream().map(Person::getJapaneseName).collect(Collectors.toList());
        System.out.println(collect);
        List<String> collect17 = personArrayList.stream().map(Person::getEnglishName).collect(Collectors.toList());
        System.out.println(collect17);
        //将某属性去重后并入一个集合中
        TreeSet<String> collect1 = personArrayList.stream().map(Person::getJapaneseName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect1);
        TreeSet<String> collect18 = personArrayList.stream().map(Person::getEnglishName).collect(Collectors.toCollection(TreeSet::new));
        System.out.println(collect18);
        //将某属性转换为字符串并连接起来
        String collect2 = personArrayList.stream().map(Person::getJapaneseName).collect(Collectors.joining(","));
        System.out.println(collect2);
        String collect19 = personArrayList.stream().map(Person::getEnglishName).collect(Collectors.joining("**"));
        System.out.println(collect19);
        //计算某属性的加和
        Long collect3 = personArrayList.stream().collect(Collectors.summingLong(Person::getPersonId));
        System.out.println(collect3);
        //根据某属性将对象分组
        Map<Integer, List<Person>> collect4 = personArrayList.stream().collect(Collectors.groupingBy(Person::getPersonId));
        System.out.println(collect4);
        Map<String, List<Person>> collect20 = personArrayList.stream().collect(Collectors.groupingBy(Person::getEnglishName));
        System.out.println(collect20);
        //根据某属性将对象分组，分组和再加和某属性
        Map<String, Long> collect5 = personArrayList.stream().collect(Collectors.groupingBy(Person::getEnglishName, Collectors.summingLong(Person::getPersonId)));
        System.out.println(collect5);
        Map<String, Long> collect21 = personArrayList.stream().collect(Collectors.groupingBy(Person::getEnglishName, Collectors.summingLong(Person::getPersonId)));
        System.out.println(collect21);
        //根据某属性去重
        ArrayList<Person> collect6 = personArrayList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Person::getPersonId))), ArrayList::new));
        System.out.println(collect6);
        ArrayList<Person> collect22 = personArrayList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Person::getEnglishName))
        ), ArrayList::new));
        System.out.println(collect22);
        //根据某属性去重
        List<Person> collect7 = personArrayList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                ()->new TreeSet<>(Comparator.comparing(Person::getJapaneseName))
        ),ArrayList::new));
        System.out.println(collect7);
        //双冒号的用法
        personArrayList.forEach(Person::getJapaneseName);
        personArrayList.forEach(Person::getPersonId);
        //map对list进行大写处理，并给另外一个List赋值。
        List<String> collect8 = personArrayList.stream().map(item -> item.getEnglishName().toUpperCase()).collect(Collectors.toList());
        System.out.println(collect8);
        List<String> collect23 = personArrayList.stream().map(item -> item.getEnglishName().toUpperCase()).collect(Collectors.toList());
        System.out.println(collect23);
        //使用filter对list进行过滤处理
        List<Person> collect9 = personArrayList.stream().filter(item -> item.getEnglishName().startsWith("h")).collect(Collectors.toList());
        System.out.println(collect9);
        List<Person> 黄 = personArrayList.stream().filter(item -> item.getJapaneseName().contains("黄")).collect(Collectors.toList());
        System.out.println(黄);
        //求某数字属性的各种算术值
        LongSummaryStatistics statistics = personArrayList.stream().mapToLong(item -> item.getPersonId()).summaryStatistics();
        System.out.println("max"+statistics.getMax());
        System.out.println("min:"+statistics.getMin());
        System.out.println("sun:"+statistics.getSum());
        System.out.println("count:"+statistics.getCount());
        System.out.println("average:"+statistics.getAverage());
        //将list转成Map
        ArrayList<Person> collect11 = personArrayList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Person::getPersonId))
        ), ArrayList::new));
        Map<Integer, String> collect10 = collect11.stream().collect(Collectors.toMap(Person::getPersonId, Person::getJapaneseName));
        System.out.println(collect10);
        //将list转成Map
        ArrayList<Person> collect13 = personArrayList.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(
                () -> new TreeSet<>(Comparator.comparing(Person::getPersonId))
        ), ArrayList::new));
        Map<Integer, Person> collect12 = collect13.stream().collect(Collectors.toMap(Person::getPersonId, Person -> Person));
        System.out.println(collect12);
        //按照某个字段，将List转为Map
        Map<Integer, List<Person>> collect14 = personArrayList.stream().collect(Collectors.groupingBy(Person::getPersonId));
        System.out.println(collect14);
        Map<String, List<Person>> collect24 = personArrayList.stream().collect(Collectors.groupingBy(Person::getJapaneseName));
        System.out.println(collect24);
        Map<String, Integer> collect25 = personArrayList.stream().collect(Collectors.toMap(Person::getJapaneseName, Person::getPersonId));
        System.out.println(collect25);
    }
}
