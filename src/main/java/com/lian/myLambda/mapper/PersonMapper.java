package com.lian.myLambda.mapper;

import com.lian.myLambda.common.core.util.CopyNonNullUtils;
import com.lian.myLambda.constant.PersonEnum;
import com.lian.myLambda.model.Person;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/4 18:58
 */
@Repository
public class PersonMapper {
    private static Map<Integer,Person> personTable = new HashMap<>();
     static {
         long now = System.currentTimeMillis();
         personTable.put(1,new Person(1,"落葉吹雪","ted",now,"1101",1,"中国"));
         personTable.put(2,new Person(2,"ココア","kokoa",now,"1102",1,"日本"));
         personTable.put(3,new Person(3,"千尋","chihiro",now,"1103",2,"韩国"));
         personTable.put(4,new Person(4,"千夜","chiya",now,"1104",2,"澳大利亚"));
         personTable.put(5,new Person(5,"小百合","koyuri",now,"1105",3,"中国"));
         personTable.put(6,new Person(6,"アリア","aria",now,"1006",3,"日本"));
    }

    /**
     * Map--->list
     * personTable中key对应数据库中的主键，即主码。
     *
     * 获取人员总列表
     * @return
     */
    public  List<Person> findPersonList(){
        List<Person> collect = personTable.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return collect;
    }

    /**
     * 将人员按年级分组--->按照对象的某属性对list进行分组
     *
     * list->根据grade分组
     * @return
     */
    public Map<Integer,List<Person>> findPersonListGroupByGrade(){
        List<Person> personList = findPersonList();
        Map<Integer, List<Person>> listGroupByGrade = personList.stream().collect(Collectors.groupingBy(p -> p.getGrade()));
        return listGroupByGrade;
    }

    /**
     * 根据身份证号筛选人员列表-->根据list集合中的单个属性筛选符合条件的集合
     *
     * list->根据idCard找到存在的人员集合
     * @param idCard
     * @return
     */
    public List<Person> findPersonByIdCard(String idCard){
        List<Person> personList = findPersonList();
        List<Person> existCollect = personList.stream().filter(e -> e.getIdCard().equals(idCard)).collect(Collectors.toList());
        return existCollect;
    }

    /**
     * 根据身份证或者id主键筛选人员列表-->根据list集合中对象的属性筛选出符合条件的集合
     *
     * list->根据idCard或者personId找到存在的人员集合（多条件筛选，与上一个用法类似）
     * @param idCard
     * @param personId
     * @return
     */
    public List<Person> findPersonListByIdCardOrPersonId(String idCard,Integer personId){
        List<Person> personList = findPersonList();
        List<Person> existCollect = personList.stream().filter(
                e -> e.getIdCard().equals(idCard) || e.getPersonId().equals(personId)
        ).collect(Collectors.toList());
        return existCollect;
    }

    /**
     * 将grade去重，返回grade集合--->根据对象某属性去重，返回该属性集合
     *
     * list->根据人员列表获取人员的年级分类集合。
     * @return
     */
    public Set<Integer> findGradeCollectByPersonList(){
        List<Person> personList = findPersonList();
        TreeSet<Integer> gradeCollect = personList.stream().map(Person::getGrade).collect(Collectors.toCollection(TreeSet::new));
        return gradeCollect;
    }

    /**
     *
     * 将人员中包含的国家用某个字符拼接起来
     * @return
     */
    public String putTogetherCountry(String symbol){
        List<Person> personList = findPersonList();
        String countryStr = personList.stream().map(Person::getCountry).collect(Collectors.toCollection(TreeSet::new)).stream().collect(Collectors.joining(symbol));
        return countryStr;
    }

    /**
     * 对list集合对象中的某个属性进行操作
     *
     * 将人员英文名称大写
     * @return
     */
    public List<Person> upperCaseEnglishName(){
        List<Person> personList = findPersonList();
        personList.forEach(item -> item.setEnglishName(item.getEnglishName().toUpperCase()));
        return personList;
    }

    public  Person add(Person person){
        List<Person> existCollect = findPersonListByIdCardOrPersonId(person.getIdCard(), person.getPersonId());
        PersonEnum.PERSON_ALREDY_EXIST.assertEquals(existCollect.size(),0);
        person.setCreateTime(System.currentTimeMillis());
        Person put = personTable.put(person.getPersonId(), person);
        return put;
    }


    public  Person upd(Person Person){
        Person PersonExist = personTable.get(Person.getPersonId());
        PersonEnum.PERSON_NOT_FOUND.assertNotNull(PersonExist);
        CopyNonNullUtils.copyNonNullProperties(Person,PersonExist);
        PersonExist.setUpdateTime(System.currentTimeMillis());
        Person put = personTable.put(PersonExist.getPersonId(), PersonExist);
        return put;
    }

    public  Person del(Integer id){
        Person remove = personTable.remove(id);
        return remove;
    }

    public  Person findOneByPersonId(Integer id){
        Person Person = personTable.get(id);
        return Person;
    }



    public static void main(String[] args) {

    }
}
