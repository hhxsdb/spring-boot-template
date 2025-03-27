package com.example.class_test.mapper;

import com.example.class_test.entity.Teacher;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wms
 * @since 2025-03-25
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Select("SELECT t.id, t.teacher_name AS teacherName, c.class_name AS className " +
            "FROM teacher t " +
            "LEFT JOIN class c ON t.id = c.teacher_id")
    List<Map<String, Object>> getTeachersWithClass();
}
