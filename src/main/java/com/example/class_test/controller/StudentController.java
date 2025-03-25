package com.example.class_test.controller;


import com.example.class_test.entity.Student;
import com.example.class_test.service.StudentService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author wms
 * @since 2025-03-21
 */
@RestController
@RequestMapping("/student")
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping("/{id}")
  public Student getStudent(@PathVariable("id") Long studentId) {
    System.out.println(studentService.getStudentWithClass(studentId));
  return studentService.getStudentWithClass(studentId);
  }
  @GetMapping("/list")
  public List<Student> getAllStudentsWithClass() {
    return studentService.getAllStudentsWithClass();
  }
  @GetMapping("/courseList")
  public List<Map<String, Object>> getAllStudentsWithClasswithCourses() {
    return studentService.getStudentCourses();
  }
}
