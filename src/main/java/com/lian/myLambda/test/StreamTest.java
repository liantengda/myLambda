package com.lian.myLambda.test;

import com.lian.myLambda.model.Person;

import java.util.ArrayList;
import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/16 10:13
 */
public class StreamTest {
    public static void main(String[] args) throws Exception {
//        //从一段话中挑出单词来
//        BufferedReader reader = new BufferedReader(new FileReader(new File("C:\\util\\javaEngineer\\javaproject\\myLambda\\src\\main\\resources\\wordTest")));
//        String blankSpace = " ";
//        Stream<String> stringStream = reader.lines();
//        Stream<String> streamCondition1 = stringStream.flatMap(line -> Stream.of(line.split(blankSpace)));
//        Stream<String> streamCondition2 = streamCondition1.filter(word -> word.length() > 0);
//        Stream<String> streamCondition3 = streamCondition2.filter(word -> word.contains("he"));
//        List<String> streamResult = streamCondition3.collect(Collectors.toList());
//        System.out.println("筛选出来的单词数组为"+streamResult);
//        reader.close();
//
        long now = System.currentTimeMillis();
        Person black = new Person(1, "落葉吹雪", "ted", now, "1101",1,"中国",15000.00);
        Person red = new Person(2, "ココア", "kokoa", now, "1102",1,"中国",5000.00);
        Person yellow = new Person(3, "千尋", "chihiro", now, "1103",2,"中国",4000.00);
        Person green = new Person(4, "千夜", "chiya", now, "1104",2,"中国",6000.00);
        Person red2 = new Person(5, "小百合", "koyuri", now, "1105",3,"中国",16000.00);
        ArrayList<Person> personArrayList = new ArrayList<>();
        personArrayList.add(black);
        personArrayList.add(red);
        personArrayList.add(yellow);
        personArrayList.add(green);
        personArrayList.add(red2);
        //需求---》算出英文名包含c字母的中国员工的平均工资
        Stream<Person> dataSourceStream = personArrayList.stream();
        Stream<Person> englishStream = dataSourceStream.filter(person -> person.getEnglishName().contains("c"));
        Stream<Person> countryStream = englishStream.filter(person -> person.getCountry().equals("中国"));
        DoubleStream salaryStream = englishStream.mapToDouble(Person::getSalary);
        OptionalDouble average = salaryStream.average();
        System.out.println("算出英文名中包含c字母的中国员工的平均工资-->"+average);

//
//        //根据特定属性去重,并输出特定属性
//        personArrayList.stream().collect(Collectors.groupingBy(p->p.getJapaneseName(),Collectors.summingLong(Person::getPersonId))).forEach((name, personId)-> System.out.println("name:"+name+":"+"personId"+personId));
//
//       //算出英文名包含c字母的员工的平均工资
//        Stream<Person> sourceStream = personArrayList.stream();
//        Stream<Person> englishStream = sourceStream.filter(person -> person.getEnglishName().contains("c"));
//        DoubleStream salaryStream = englishStream.mapToDouble(Person::getSalary);
//        OptionalDouble average = salaryStream.average();
//        System.out.println(average);
//        Map<String, Long> collect = stream.collect(Collectors.groupingBy(p -> p.getJapaneseName(), Collectors.summingLong(Person::getPersonId)));
//        collect.forEach((name,personId)-> System.out.println("name:"+name+":"+"personId"+personId));
//        personArrayList.forEach(value-> System.out.println(value));
//        Stream<Person> k = personArrayList.parallelStream().filter(p -> p.getEnglishName().startsWith("k"));
//        System.out.println("是否并行--"+k.isParallel());
//        Stream<Person> o = k.filter(p -> p.getGrade()>2);
//        System.out.println("是否并行--"+o.isParallel());
//        List<Person> parallelList = o.collect(Collectors.toList());
//        parallelList.forEach(value-> System.out.println(value));

//        Integer[] integers = {1,2,3,4,5,6,7,8};
//        Stream<Integer> dataStream = Stream.of(integers);
//        Stream<Integer> integerCondition1 = dataStream.filter(i -> i > 1);
//        Stream<Integer> integerCondition2 = integerCondition1.filter(i -> i > 2);
//        Stream<Integer> integerCondition3 = integerCondition2.filter(i -> i > 3);
//        Stream<Integer> integerCondition4 = integerCondition3.filter(integer -> integer > 4);
//        Stream<Integer> integerCondition5 = integerCondition4.filter(integer -> integer > 5);
//        Stream<Integer> integerCondition6 = integerCondition5.filter(integer -> integer > 7);
//        long count = integerCondition6.count();
//        System.out.println(count);

    }
}
