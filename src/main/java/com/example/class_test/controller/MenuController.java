package com.example.class_test.controller;


import com.example.class_test.common.Result;
import com.example.class_test.entity.Menu;
import com.example.class_test.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2025-04-01
 */
@RestController
@RequestMapping("/menu")
@Api(tags = "菜单管理接口")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @GetMapping("/list")
    @ApiOperation("获取所有菜单")
    public Result getAllMenus() {
        List<Menu> list = menuService.list();
        return Result.success(list, (long) list.size());
    }
}
