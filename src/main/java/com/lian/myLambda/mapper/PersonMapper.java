package com.lian.myLambda.mapper;

import com.lian.myLambda.common.core.util.CopyNonNullUtils;
import com.lian.myLambda.constant.PersonEnum;
import com.lian.myLambda.model.Person;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Ted
 * @version 1.0
 * @date 2020/5/4 18:58
 */
@Repository
public class PersonMapper {
    private static Map<Integer,Person> PersonTable = new HashMap<>();
     static {
         long now = System.currentTimeMillis();
         PersonTable.put(1,new Person(1,"落葉吹雪","ted",now,"1101"));
         PersonTable.put(2,new Person(2,"ココア","kokoa",now,"1102"));
         PersonTable.put(3,new Person(3,"千尋","chihiro",now,"1103"));
         PersonTable.put(4,new Person(4,"千夜","chiya",now,"1104"));
         PersonTable.put(5,new Person(5,"小百合","koyuri",now,"1105"));
    }

    public  Person add(Person Person){
         Person.setCreateTime(System.currentTimeMillis());
        Person put = PersonTable.put(Person.getPersonId(), Person);
        return put;
    }

    public  Person upd(Person Person){
        Person PersonExist = PersonTable.get(Person.getPersonId());
        PersonEnum.PERSON_NOT_FOUND.assertNotNull(PersonExist);
        CopyNonNullUtils.copyNonNullProperties(Person,PersonExist);
        PersonExist.setUpdateTime(System.currentTimeMillis());
        Person put = PersonTable.put(PersonExist.getPersonId(), PersonExist);
        return put;
    }

    public  Person del(Integer id){
        Person remove = PersonTable.remove(id);
        return remove;
    }

    public  Person findOneByPersonId(Integer id){
        Person Person = PersonTable.get(id);
        return Person;
    }

    public  List<Person> findPersonList(){
        List<Person> collect = PersonTable.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return collect;
    }

    public List<Person> findPersonByIdCard(String idCard){
        List<Person> collect = PersonTable.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        System.out.println(collect);
        List<Person> existCollect = collect.stream().filter(e -> e.getIdCard().equals(idCard)).collect(Collectors.toList());
        return existCollect;
    }


    public static void main(String[] args) {

    }
}
