import request from '@/utils/request';

// 获取学生列表
export function getStudentList(params) {
    return request({
        url: '/student/list',
        method: 'get',
        params
    });
}

// 分页查询学生
export function pageStudent(data) {
    return request({
        url: '/student/page',
        method: 'post',
        data
    });
}

// 获取学生详情
export function getStudentById(id) {
    return request({
        url: `/student/${id}`,
        method: 'get'
    });
}

// 添加学生
export function addStudent(data) {
    return request({
        url: '/student/add',
        method: 'post',
        data
    });
}

// 更新学生信息
export function updateStudent(data) {
    return request({
        url: '/student/update',
        method: 'put',
        data
    });
}

// 删除学生
export function deleteStudent(id) {
    return request({
        url: `/student/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除学生
export function batchDeleteStudent(ids) {
    return request({
        url: '/student/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 查询学生所选课程
export function getStudentCourses(studentId) {
    return request({
        url: `/student/courses/${studentId}`,
        method: 'get'
    });
}

// 批量导入学生
export function importStudents(data) {
    return request({
        url: '/student/import',
        method: 'post',
        data,
        headers: { 'Content-Type': 'multipart/form-data' }
    });
}

// 导出学生数据
export function exportStudents(params) {
    return request({
        url: '/student/export',
        method: 'get',
        params,
        responseType: 'blob'
    });
} 