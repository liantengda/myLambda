package com.lian.myLambda.controller;

import com.lian.myLambda.common.core.pojo.response.R;
import com.lian.myLambda.model.Person;
import com.lian.myLambda.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author Ted
 * @Date 2020-05-05
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonService personService;

    /**
     * 根据id获取成员
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public R<Person> GetPerson(@PathVariable("id") Integer id){
        return new R<>(personService.sel(id));
    }

    /**
     * 查询所有成员
     * @return
     */
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public R<List<Person>> list(){
        return new R<>(personService.list());
    }

    /**
     * 根据年级对人员列表分组并返回
     * @return
     */
    @RequestMapping(value = "/listByGrade",method = RequestMethod.GET)
    @ResponseBody
    public R<Map<Integer,List<Person>>> listGroupByGrade(){
        return new R<>(personService.listGroupByGrade());
    }

    /**
     * 列出成员所有的年级列表
     * @return
     */
    @RequestMapping(value = "/listGrade",method = RequestMethod.GET)
    @ResponseBody
    public R<Set<Integer>> listGradeCollect(){
        return new R<>(personService.listGrade());
    }

    /**
     * 成员列表中包含的国家，用symbol拼接后返回
     * @param symbol
     * @return
     */
    @RequestMapping(value = "/countryStr/{symbol}",method =RequestMethod.GET)
    @ResponseBody
    public R<String> putTogetherCountry(@PathVariable("symbol") String symbol){
        return new R<>(personService.putTogetherCountry(symbol));
    }

    /**
     * 添加一个成员
     * @param Person
     * @return
     */
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
