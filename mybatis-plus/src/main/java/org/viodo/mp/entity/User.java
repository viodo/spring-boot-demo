package org.viodo.mp.entity;

import lombok.Data;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 13:32
 */
@Data
public class User {

    private Long id;

    private String name;

    private Integer age;

    private String email;
}
