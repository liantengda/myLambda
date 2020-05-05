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
    private static Map<Integer,Person> personTable = new HashMap<>();
     static {
         long now = System.currentTimeMillis();
         personTable.put(1,new Person(1,"落葉吹雪","ted",now,"1101",1));
         personTable.put(2,new Person(2,"ココア","kokoa",now,"1102",1));
         personTable.put(3,new Person(3,"千尋","chihiro",now,"1103",2));
         personTable.put(4,new Person(4,"千夜","chiya",now,"1104",2));
         personTable.put(5,new Person(5,"小百合","koyuri",now,"1105",3));
         personTable.put(6,new Person(6,"アリア","aria",now,"1006",3));
    }

    /**
     * 将人员按年级分组
     * @return
     */
    public Map<Integer,List<Person>> findPersonListGroupByGrade(){
        List<Person> personList = personTable.entrySet().stream().map(m -> m.getValue()).collect(Collectors.toList());
        Map<Integer, List<Person>> listGroupByGrade = personList.stream().collect(Collectors.groupingBy(p -> p.getGrade()));
        return listGroupByGrade;
    }

    public  Person add(Person Person){
        Person.setCreateTime(System.currentTimeMillis());
        Person put = personTable.put(Person.getPersonId(), Person);
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

    public  List<Person> findPersonList(){
        List<Person> collect = personTable.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        return collect;
    }

    public List<Person> findPersonByIdCard(String idCard){
        List<Person> collect = personTable.entrySet().stream().map(e -> e.getValue()).collect(Collectors.toList());
        List<Person> existCollect = collect.stream().filter(e -> e.getIdCard().equals(idCard)).collect(Collectors.toList());
        return existCollect;
    }


    public static void main(String[] args) {

    }
}
