package com.example.class_test.service;

import com.example.class_test.entity.Student;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2025-03-21
 */
public interface StudentService extends IService<Student> {
  Student getStudentWithClass(Long studentId);
  List<Student> getAllStudentsWithClass();
  List<Map<String, Object>> getStudentCourses();
}
