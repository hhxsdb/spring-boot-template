package com.example.class_test.mapper;

import com.example.class_test.entity.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author wms
 * @since 2025-03-21
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {
  @Select("SELECT s.id, s.student_name AS studentName, s.class_id AS classId, c.class_name AS className " +
      "FROM student s " +
      "LEFT JOIN class c ON s.class_id = c.id " +
      "WHERE s.id = #{studentId}")
  Student getStudentWithClass(Long studentId);

  @Select("SELECT s.id AS student_id, s.student_name, s.class_id, c.class_name " +
      "FROM student s LEFT JOIN class c ON s.class_id = c.id")
  List<Student> selectAllStudentsWithClass();
// sql语句在studentMapper.xml
  @Select("")
  List<Map<String, Object>> getStudentCourses();
}
