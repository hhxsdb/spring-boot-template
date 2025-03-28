package com.example.class_test.service.impl;

import com.example.class_test.entity.Teacher;
import com.example.class_test.mapper.TeacherMapper;
import com.example.class_test.service.TeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
  
  @Autowired
  private RedisTemplate<String, Object> redisTemplate;
  
  public List<Map<String, Object>> getTeachersWithClass(){
    return teacherMapper.getTeachersWithClass();
  }
  
  public Teacher getTeacherByIdWithCache(Long teacherId) {
    // 定义缓存key
    String cacheKey = "teacher:" + teacherId;
    
    // 先从缓存中获取数据
    Teacher teacher = (Teacher) redisTemplate.opsForValue().get(cacheKey);
    
    // 如果缓存中没有数据，则从数据库查询
    if (teacher == null) {
      teacher = teacherMapper.selectById(teacherId);
    }
      if (teacher != null) {
        // 将查询结果存入缓存，并设置过期时间为1小时
        redisTemplate.opsForValue().set(cacheKey, teacher, 1, TimeUnit.HOURS);
    }
    return teacher;
  }
}
