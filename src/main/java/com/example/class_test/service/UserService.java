package com.example.class_test.service;

import com.example.class_test.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wms
 * @since 2025-03-28
 */
public interface UserService extends IService<User> {
    User getUserByUsername(String username);
}
