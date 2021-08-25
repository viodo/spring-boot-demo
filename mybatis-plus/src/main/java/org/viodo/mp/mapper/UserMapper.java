package org.viodo.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.CacheNamespace;
import org.viodo.mp.config.MybatisRedisCache;
import org.viodo.mp.entity.User;

/**
 * 文件描述
 *
 * @author xc.chen
 * @date 2021/8/25 13:32
 */
@CacheNamespace(implementation = MybatisRedisCache.class)
public interface UserMapper extends BaseMapper<User> {
}
