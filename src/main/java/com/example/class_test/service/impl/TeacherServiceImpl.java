package com.example.class_test.service.impl;

import com.example.class_test.entity.Teacher;
import com.example.class_test.mapper.TeacherMapper;
import com.example.class_test.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author wms
 * @since 2025-03-25
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {
  @Autowired
  private TeacherMapper teacherMapper;
  public List<Map<String, Object>> getTeachersWithClass(){
    return teacherMapper.getTeachersWithClass();
  }
}
