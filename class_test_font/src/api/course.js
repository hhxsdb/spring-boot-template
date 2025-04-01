import request from '@/utils/request';

// 获取课程列表
export function getCourseList(params) {
    return request({
        url: '/course/list',
        method: 'get',
        params
    });
}

// 分页查询课程
export function pageCourse(data) {
    return request({
        url: '/course/page',
        method: 'post',
        data
    });
}

// 获取课程详情
export function getCourseById(id) {
    return request({
        url: `/course/${id}`,
        method: 'get'
    });
}

// 添加课程
export function addCourse(data) {
    return request({
        url: '/course/add',
        method: 'post',
        data
    });
}

// 更新课程信息
export function updateCourse(data) {
    return request({
        url: '/course/update',
        method: 'put',
        data
    });
}

// 删除课程
export function deleteCourse(id) {
    return request({
        url: `/course/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除课程
export function batchDeleteCourse(ids) {
    return request({
        url: '/course/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 获取课程学生列表
export function getCourseStudents(courseId) {
    return request({
        url: `/course/students/${courseId}`,
        method: 'get'
    });
}

// 获取课程教师信息
export function getCourseTeacher(courseId) {
    return request({
        url: `/course/teacher/${courseId}`,
        method: 'get'
    });
}

// 分配课程教师
export function assignCourseTeacher(data) {
    return request({
        url: '/course/assignTeacher',
        method: 'post',
        data
    });
} 