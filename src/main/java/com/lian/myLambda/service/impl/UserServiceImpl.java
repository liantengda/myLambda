package com.lian.myLambda.service.impl;


import com.lian.myLambda.constant.UserEnum;
import com.lian.myLambda.model.User;
import com.lian.myLambda.service.UserService;
import com.lian.myLambda.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl  implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public User sel(int id){
        User user = userMapper.findOneByUserId(id);
        UserEnum.USER_NOT_FOUND.assertNotNull(user);
        return user;
    }

    @Override
    public List<User> list() {
        List<User> userList = userMapper.findUserList();
        return userList;
    }

    @Override
    public User add(User user) {
        List<User> existCollect = userMapper.findUserByIdCard(user.getIdCard());
        UserEnum.USER_ALREDY_EXIST.assertEquals(existCollect.size(),0);
        User add = userMapper.add(user);
        return add;
    }

    @Override
    public User upd(User user) {
        User upd = userMapper.upd(user);
        return upd;
    }

    @Override
    public User del(Integer id) {
        User user = userMapper.findOneByUserId(id);
        UserEnum.USER_ALREDY_DELETED.assertNotNull(user);
        User del = userMapper.del(id);
        return del;
    }

}
