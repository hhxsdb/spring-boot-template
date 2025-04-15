package com.example.class_test.service;

import com.example.class_test.entity.User;
import com.example.class_test.mapper.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    public PasswordService(PasswordEncoder passwordEncoder, UserMapper userMapper) {
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
    }

    /**
     * 验证用户密码，支持明文和BCrypt
     * @param rawPassword 用户输入的原始密码
     * @param userPassword 数据库中存储的密码
     * @return 验证是否通过
     */
    public boolean matches(String rawPassword, String userPassword) {
        // 先尝试BCrypt验证
        if (isBCryptPassword(userPassword)) {
            return passwordEncoder.matches(rawPassword, userPassword);
        } 
        // 如果不是BCrypt密码，则直接比较明文密码
        return rawPassword.equals(userPassword);
    }

    /**
     * 检查密码是否使用BCrypt加密
     * @param password 要检查的密码
     * @return 是否为BCrypt加密密码
     */
    public boolean isBCryptPassword(String password) {
        return password != null && password.startsWith("$2a$") && password.length() >= 60;
    }

    /**
     * 将用户密码更新为BCrypt加密密码
     * @param user 用户对象
     * @param rawPassword 原始密码
     */
    public void updatePasswordToBCrypt(User user, String rawPassword) {
        // 如果当前密码不是BCrypt格式，则更新为BCrypt格式
        if (!isBCryptPassword(user.getPassword())) {
            user.setPassword(passwordEncoder.encode(rawPassword));
            userMapper.updateById(user);
        }
    }
} 