package com.example.class_test.controller;

import com.example.class_test.common.Result;
import com.example.class_test.entity.User;
import com.example.class_test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wms
 * @since 2025-03-28
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody User user) {
        Map<String, Object> result = new HashMap<>();
        
        // 验证用户名和密码
        User dbUser = userService.getUserByUsername(user.getUsername());
        if (dbUser == null) {
            result.put("code", 401);
            result.put("message", "用户名不存在");
            return result;
        }
        
        if (!dbUser.getPassword().equals(user.getPassword())) {
            result.put("code", 401);
            result.put("message", "密码错误");
            return result;
        }
        
        // 生成token（这里简单使用用户ID作为token）
        String token = String.valueOf(dbUser.getId());
        
        // 将用户信息存入Redis，设置过期时间为24小时
        redisTemplate.opsForValue().set("token:" + token, dbUser, 24, TimeUnit.HOURS);
        
        result.put("code", 200);
        result.put("message", "登录成功");
        result.put("token", token);
        result.put("user", dbUser);
        
        return result;
    }
//redis 检查登陆状态
    @GetMapping("/checkLogin")
    public Result checkLogin(@RequestParam String token) {
        // 从Redis中获取用户信息
        User user = (User) redisTemplate.opsForValue().get("token:" + token);
        
        if (user != null) {
            return Result.success(user);
        } else {
            return Result.fail();
        }
    }
}
