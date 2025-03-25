package com.example.class_test.service.impl;

import com.example.class_test.entity.Student;
import com.example.class_test.mapper.StudentMapper;
import com.example.class_test.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2025-03-21
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
  @Autowired
  private StudentMapper studentMapper;

  // 查询学生以及对应的班级信息
  public Student getStudentWithClass(Long studentId) {
    return studentMapper.getStudentWithClass(studentId);
  }
  public List<Student> getAllStudentsWithClass() {
    return studentMapper.selectAllStudentsWithClass();
  }
}
