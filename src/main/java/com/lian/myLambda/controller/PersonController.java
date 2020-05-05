package com.lian.myLambda.controller;

import com.lian.myLambda.common.core.pojo.response.R;
import com.lian.myLambda.model.Person;
import com.lian.myLambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author Ted
 * @Date 2020-05-05
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public R<Person> GetPerson(@PathVariable("id") Integer id){
        return new R<>(personService.sel(id));
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public R<List<Person>> list(){
        return new R<>(personService.list());
    }

    @RequestMapping(value = "/listByGrade",method = RequestMethod.GET)
    @ResponseBody
    public R<Map<Integer,List<Person>>> listGroupByGrade(){
        return new R<>(personService.listGroupByGrade());
    }

    @RequestMapping(value = "/",method = RequestMethod.POST)
    @ResponseBody
    public R<Person> add(@RequestBody Person Person){
        return new R<>(personService.add(Person));
    }

    @RequestMapping(value = "/",method = RequestMethod.PUT)
    @ResponseBody
    public R<Person> upd(@RequestBody Person Person){
        return new R<>(personService.upd(Person));
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    @ResponseBody
    public R<Person> del(@PathVariable("id")Integer id){
        return new R<>(personService.del(id));
    }

}
