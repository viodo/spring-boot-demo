package org.viodo.mp.controller;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.viodo.mp.entity.User;
import org.viodo.mp.mapper.UserMapper;
import org.viodo.mp.model.MyPage;
import org.viodo.mp.model.UserChildren;

import java.util.Random;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 13:46
 */
@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserMapper userMapper;

    @GetMapping("list")
    public String list() {
        return JSON.toJSONString(userMapper.selectList(null));
    }

    @GetMapping("add")
    public String add() {
        Random random = new Random();
        User user = new User();
        user.setAge(random.nextInt(100));
        user.setEmail("email" + random.nextInt(100));
        user.setName("test" + random.nextInt(100));
        userMapper.insert(user);
        return JSON.toJSONString(user);
    }

    @GetMapping("page")
    public String page() {
        Page<User> page = new Page<>(1, 3);
        Page<User> result = userMapper.selectPage(page, Wrappers.<User>lambdaQuery().ge(User::getAge, 1).orderByAsc(User::getAge));
        return JSON.toJSONString(result);
    }

    @GetMapping("page2")
    public String page2() {
        MyPage<UserChildren> myPage = new MyPage<>(1, 5);
        MyPage<UserChildren> result = userMapper.userChildrenPage(myPage);
        return JSON.toJSONString(result);
    }
}
