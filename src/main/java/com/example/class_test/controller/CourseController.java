package com.example.class_test.controller;


import com.example.class_test.common.Result;
import com.example.class_test.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2025-03-25
 */
@RestController
@RequestMapping("/course")
public class CourseController {
  @Autowired
  private CourseService courseService;
  @GetMapping("/list")
  private Result getList(){
    return Result.success(courseService.list(), Long.valueOf(courseService.list().size()));
  }
}
