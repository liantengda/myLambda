package com.lian.myLambda.service.impl;


import com.lian.myLambda.constant.PersonEnum;
import com.lian.myLambda.mapper.PersonMapper;
import com.lian.myLambda.model.Person;
import com.lian.myLambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    PersonMapper PersonMapper;

    @Override
    public Person sel(int id){
        Person Person = PersonMapper.findOneByPersonId(id);
        PersonEnum.PERSON_NOT_FOUND.assertNotNull(Person);
        return Person;
    }

    @Override
    public List<Person> list() {
        List<Person> PersonList = PersonMapper.findPersonList();
        return PersonList;
    }

    @Override
    public Person add(Person Person) {
        List<Person> existCollect = PersonMapper.findPersonByIdCard(Person.getIdCard());
        PersonEnum.PERSON_ALREDY_EXIST.assertEquals(existCollect.size(),0);
        Person add = PersonMapper.add(Person);
        return add;
    }

    @Override
    public Person upd(Person Person) {
        Person upd = PersonMapper.upd(Person);
        return upd;
    }

    @Override
    public Person del(Integer id) {
        Person Person = PersonMapper.findOneByPersonId(id);
        PersonEnum.PERSON_ALREDY_DELETED.assertNotNull(Person);
        Person del = PersonMapper.del(id);
        return del;
    }

}
