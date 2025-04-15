package com.example.class_test.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.class_test.common.QueryPageParam;
import com.example.class_test.common.Result;
import com.example.class_test.entity.User;
import com.example.class_test.security.JwtTokenUtil;
import com.example.class_test.service.PasswordService;
import com.example.class_test.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
@Api(tags = "用户管理接口")
public class UserController {

    private final UserService userService;
    private final JwtTokenUtil jwtTokenUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final PasswordService passwordService;

    public UserController(
            UserService userService,
            JwtTokenUtil jwtTokenUtil,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder,
            PasswordService passwordService) {
        this.userService = userService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.authenticationManager = authenticationManager;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;
    }

    @PostMapping("/login")
    @ApiOperation("用户登录")
    public Result login(@RequestBody User user) {
        try {
            String username = user.getUsername();
            String password = user.getPassword();
            
            // 先通过用户名获取用户信息
            User dbUser = userService.getUserByUsername(username);
            if (dbUser == null) {
                return Result.fail();
            }
            
            // 使用我们的自定义密码服务验证密码
            if (!passwordService.matches(password, dbUser.getPassword())) {
                return Result.fail();
            }
            
            // 如果是明文密码，则更新为BCrypt加密的密码
            passwordService.updatePasswordToBCrypt(dbUser, password);
            
            // 重新获取更新后的用户
            dbUser = userService.getById(dbUser.getId());
            
            // 生成JWT令牌
            String token = jwtTokenUtil.generateToken(dbUser);

            // 构建并返回响应数据
            Map<String, Object> resultMap = new HashMap<>();
            resultMap.put("token", token);
            resultMap.put("user", dbUser);

            return Result.success(resultMap);
        } catch (Exception e) {
            e.printStackTrace();
            return Result.fail();
        }
    }
    
    @PostMapping("/register")
    @ApiOperation("用户注册")
    public Result register(@RequestBody User user) {
        // 检查用户名是否已存在
        User existUser = userService.getUserByUsername(user.getUsername());
        if (existUser != null) {
            return Result.fail();
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        // 保存用户
        boolean saved = userService.save(user);
        return saved ? Result.success() : Result.fail();
    }
    
    @GetMapping("/list")
    @ApiOperation("获取用户列表")
    public Result list() {
        List<User> list = userService.list();
        return Result.success(list, (long) list.size());
    }
    
    @GetMapping("/page")
    @ApiOperation("分页查询用户")
    public Result page(@RequestBody QueryPageParam query) {
        Page<User> page = new Page<>();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());
        
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        
        // 获取参数
        Map<String, Object> param = query.getParams();
        if (param != null) {
            String username = (String) param.get("username");
            if (username != null && !username.equals("")) {
                wrapper.like(User::getUsername, username);
            }
        }
        
        IPage<User> result = userService.page(page, wrapper);
        return Result.success(result.getRecords(), result.getTotal());
    }
    
    @PostMapping("/save")
    @ApiOperation("新增用户")
    public Result save(@RequestBody User user) {
        // 检查用户名是否已存在
        User existUser = userService.getUserByUsername(user.getUsername());
        if (existUser != null) {
            return Result.fail();
        }
        
        // 加密密码
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        
        boolean saved = userService.save(user);
        return saved ? Result.success() : Result.fail();
    }
    
    @PutMapping("/update")
    @ApiOperation("更新用户")
    public Result update(@RequestBody User user) {
        // 如果提供了密码，则需要加密
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            // 如果没有提供密码，从数据库获取当前密码
            User currentUser = userService.getById(user.getId());
            if (currentUser != null) {
                user.setPassword(currentUser.getPassword());
            }
        }
        
        boolean updated = userService.updateById(user);
        return updated ? Result.success() : Result.fail();
    }
    
    @DeleteMapping("/delete/{id}")
    @ApiOperation("删除用户")
    public Result delete(@PathVariable Long id) {
        boolean removed = userService.removeById(id);
        return removed ? Result.success() : Result.fail();
    }
    
    @GetMapping("/{id}")
    @ApiOperation("根据ID查询用户")
    public Result getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return user != null ? Result.success(user) : Result.fail();
    }
}
