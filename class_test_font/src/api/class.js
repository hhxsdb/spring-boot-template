import request from '@/utils/request';

// 获取班级列表
export function getClassList(params) {
    return request({
        url: '/class/list',
        method: 'get',
        params
    });
}

// 分页查询班级
export function pageClass(data) {
    return request({
        url: '/class/page',
        method: 'post',
        data
    });
}

// 获取班级详情
export function getClassById(id) {
    return request({
        url: `/class/${id}`,
        method: 'get'
    });
}

// 添加班级
export function addClass(data) {
    return request({
        url: '/class/add',
        method: 'post',
        data
    });
}

// 更新班级信息
export function updateClass(data) {
    return request({
        url: '/class/update',
        method: 'put',
        data
    });
}

// 删除班级
export function deleteClass(id) {
    return request({
        url: `/class/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除班级
export function batchDeleteClass(ids) {
    return request({
        url: '/class/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 获取班级学生列表
export function getClassStudents(classId) {
    return request({
        url: `/class/students/${classId}`,
        method: 'get'
    });
}

// 获取班级课程表
export function getClassSchedule(classId) {
    return request({
        url: `/class/schedule/${classId}`,
        method: 'get'
    });
} 