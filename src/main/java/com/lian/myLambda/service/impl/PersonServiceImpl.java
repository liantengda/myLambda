package com.lian.myLambda.service.impl;


import com.lian.myLambda.constant.PersonEnum;
import com.lian.myLambda.mapper.PersonMapper;
import com.lian.myLambda.model.Person;
import com.lian.myLambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper personMapper;

    @Override
    public Person sel(int id){
        Person Person = personMapper.findOneByPersonId(id);
        PersonEnum.PERSON_NOT_FOUND.assertNotNull(Person);
        return Person;
    }

    @Override
    public List<Person> list() {
        List<Person> PersonList = personMapper.findPersonList();
        return PersonList;
    }

    @Override
    public Map<Integer, List<Person>> listGroupByGrade() {
        Map<Integer, List<Person>> personListGroupByGrade = personMapper.findPersonListGroupByGrade();
        return personListGroupByGrade;
    }

    @Override
    public Set<Integer> listGrade() {
        Set<Integer> gradeSet = personMapper.findGradeCollectByPersonList();
        return gradeSet;
    }

    @Override
    public Person add(Person person) {
        Person add = personMapper.add(person);
        return add;
    }

    @Override
    public Person upd(Person Person) {
        Person upd = personMapper.upd(Person);
        return upd;
    }

    @Override
    public Person del(Integer id) {
        Person Person = personMapper.findOneByPersonId(id);
        PersonEnum.PERSON_ALREDY_DELETED.assertNotNull(Person);
        Person del = personMapper.del(id);
        return del;
    }

}
