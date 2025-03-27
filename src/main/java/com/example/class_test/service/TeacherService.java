package com.example.class_test.service;

import com.example.class_test.entity.Teacher;
import com.baomidou.mybatisplus.extension.service.IService;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author wms
 * @since 2025-03-25
 */
public interface TeacherService extends IService<Teacher> {
  List<Map<String, Object>> getTeachersWithClass();
}
