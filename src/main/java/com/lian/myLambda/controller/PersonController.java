package com.lian.myLambda.controller;

import com.lian.myLambda.common.core.pojo.response.R;
import com.lian.myLambda.model.Person;
import com.lian.myLambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author:wjup
 * @Date: 2018/9/26 0026
 * @Time: 14:42
 */

@RestController
@RequestMapping("/Person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public R<Person> GetPerson(@PathVariable("id") Integer id){
        return new R<>(personService.sel(id));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    public R<List<Person>> list(){
        return new R<>(personService.list());
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public R<Person> add(@RequestBody Person Person){
        return new R<>(personService.add(Person));
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    public R<Person> upd(@RequestBody Person Person){
        return new R<>(personService.upd(Person));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public R<Person> del(@PathVariable("id")Integer id){
        return new R<>(personService.del(id));
    }

}
