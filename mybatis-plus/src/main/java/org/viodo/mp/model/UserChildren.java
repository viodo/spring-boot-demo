package org.viodo.mp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.viodo.mp.entity.Children;
import org.viodo.mp.entity.User;

import java.util.List;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 15:32
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserChildren extends User {

    private List<Children> childrenList;
}
