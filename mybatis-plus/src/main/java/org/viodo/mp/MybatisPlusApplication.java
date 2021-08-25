package org.viodo.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 13:31
 */
@SpringBootApplication
@MapperScan("org.viodo.mp")
public class MybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisPlusApplication.class, args);
    }
}
