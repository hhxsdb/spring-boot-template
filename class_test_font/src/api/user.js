import request from '@/utils/request';

//  登陆
export function login(data) {
    return request({
        url: '/login',
        method: 'post',
        data
    });
}

// 获取用户列表
export function getUserList(params) {
    return request({
        url: '/user/list',
        method: 'get',
        params
    });
}

// 分页查询用户
export function pageUser(data) {
    return request({
        url: '/user/page',
        method: 'post',
        data
    });
}

// 获取用户详情
export function getUserById(id) {
    return request({
        url: `/user/${id}`,
        method: 'get'
    });
}

// 添加用户
export function addUser(data) {
    return request({
        url: '/user/add',
        method: 'post',
        data
    });
}

// 更新用户信息
export function updateUser(data) {
    return request({
        url: '/user/update',
        method: 'put',
        data
    });
}

// 删除用户
export function deleteUser(id) {
    return request({
        url: `/user/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除用户
export function batchDeleteUser(ids) {
    return request({
        url: '/user/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 重置用户密码
export function resetUserPassword(id) {
    return request({
        url: `/user/resetPassword/${id}`,
        method: 'put'
    });
}

// 获取当前用户信息
export function getUserInfo() {
    return request({
        url: '/user/info',
        method: 'get'
    });
}

// 退出登录
export function logout() {
    return request({
        url: '/logout',
        method: 'post'
    });
}

// GET请求模板
export function getTemplate(params) {
    return request({
        url: '/your-get-path',
        method: 'get',
        params
    });
}

// GET请求（带ID）模板
export function getByIdTemplate(id) {
    return request({
        url: `/your-path/${id}`,
        method: 'get'
    });
}

// POST请求模板
export function postTemplate(data) {
    return request({
        url: '/your-post-path',
        method: 'post',
        data
    });
}

// PUT请求模板
export function putTemplate(data) {
    return request({
        url: '/your-put-path',
        method: 'put',
        data
    });
}

// DELETE请求模板
export function deleteTemplate(data) {
    return request({
        url: '/your-delete-path',
        method: 'delete',
        data
    });
}

// 分页查询模板
export function pageTemplate(data) {
    return request({
        url: '/your-page-path',
        method: 'post',
        data
    });
}
