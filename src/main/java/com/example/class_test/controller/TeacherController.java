package com.example.class_test.controller;


import com.example.class_test.common.Result;
import com.example.class_test.entity.Teacher;
import com.example.class_test.service.TeacherService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
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
@RequestMapping("/teacher")
public class TeacherController {
  //查询教师 关联班级
  @Autowired
  private TeacherService teacherService;
  @GetMapping("/list")
  private Result getAllTeacher(){
    return Result.success(teacherService.getTeachersWithClass());
  }
  //redis缓存 通过id获取教师
  @PostMapping("/getTeacher")
  public Teacher getTeacherById(@RequestBody Teacher teacher) {
    return teacherService.getTeacherByIdWithCache(Long.valueOf(teacher.getId()));
  }
}
