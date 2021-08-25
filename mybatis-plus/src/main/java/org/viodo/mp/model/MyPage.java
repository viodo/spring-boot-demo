package org.viodo.mp.model;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 15:45
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class MyPage<T> extends Page<T> {

    private static final long serialVersionUID = 5194933845448697148L;

    private Integer selectInt;

    private String selectStr;

    private String name;

    public MyPage(long current, long size) {
        super(current, size);
    }
}
