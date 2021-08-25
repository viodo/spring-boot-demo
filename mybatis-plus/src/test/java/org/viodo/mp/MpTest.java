package org.viodo.mp;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.viodo.mp.entity.User;
import org.viodo.mp.mapper.UserMapper;

import java.util.List;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 13:33
 */
@SpringBootTest
public class MpTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        Assertions.assertEquals(5, userList.size());
        userList.forEach(System.out::println);
    }
}
