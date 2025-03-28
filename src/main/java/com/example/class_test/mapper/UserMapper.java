package com.example.class_test.mapper;

import com.example.class_test.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author wms
 * @since 2025-03-28
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}
