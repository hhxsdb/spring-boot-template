package com.example.class_test.controller;


import com.example.class_test.entity.Student;
import com.example.class_test.service.StudentService;
import java.util.List;
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
public class StudentController {
  @Autowired
  private StudentService studentService;

  @GetMapping("/student/{id}")
  public Student getStudent(@PathVariable("id") Long studentId) {
    System.out.println(studentService.getStudentWithClass(studentId));
  return studentService.getStudentWithClass(studentId);
  }
  @GetMapping("/students")
  public List<Student> getAllStudentsWithClass() {
    return studentService.getAllStudentsWithClass();
  }
}
