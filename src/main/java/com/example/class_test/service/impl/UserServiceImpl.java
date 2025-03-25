package com.example.class_test.service.impl;

import com.example.class_test.entity.User;
import com.example.class_test.mapper.UserMapper;
import com.example.class_test.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2025-03-13
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
