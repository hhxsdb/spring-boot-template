import request from '@/utils/request';

// 获取教师列表
export function getTeacherList(params) {
    return request({
        url: '/teacher/list',
        method: 'get',
        params
    });
}

// 分页查询教师
export function pageTeacher(data) {
    return request({
        url: '/teacher/page',
        method: 'post',
        data
    });
}

// 获取教师详情
export function getTeacherById(id) {
    return request({
        url: `/teacher/${id}`,
        method: 'get'
    });
}

// 添加教师
export function addTeacher(data) {
    return request({
        url: '/teacher/add',
        method: 'post',
        data
    });
}

// 更新教师信息
export function updateTeacher(data) {
    return request({
        url: '/teacher/update',
        method: 'put',
        data
    });
}

// 删除教师
export function deleteTeacher(id) {
    return request({
        url: `/teacher/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除教师
export function batchDeleteTeacher(ids) {
    return request({
        url: '/teacher/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 获取教师授课课程
export function getTeacherCourses(teacherId) {
    return request({
        url: `/teacher/courses/${teacherId}`,
        method: 'get'
    });
} 