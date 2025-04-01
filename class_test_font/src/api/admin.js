import request from '@/utils/request';

// 获取管理员列表
export function getAdminList(params) {
    return request({
        url: '/admin/list',
        method: 'get',
        params
    });
}

// 分页查询管理员
export function pageAdmin(data) {
    return request({
        url: '/admin/page',
        method: 'post',
        data
    });
}

// 获取管理员详情
export function getAdminById(id) {
    return request({
        url: `/admin/${id}`,
        method: 'get'
    });
}

// 添加管理员
export function addAdmin(data) {
    return request({
        url: '/admin/add',
        method: 'post',
        data
    });
}

// 更新管理员信息
export function updateAdmin(data) {
    return request({
        url: '/admin/update',
        method: 'put',
        data
    });
}

// 删除管理员
export function deleteAdmin(id) {
    return request({
        url: `/admin/delete/${id}`,
        method: 'delete'
    });
}

// 批量删除管理员
export function batchDeleteAdmin(ids) {
    return request({
        url: '/admin/batchDelete',
        method: 'delete',
        data: { ids }
    });
}

// 重置管理员密码
export function resetAdminPassword(id) {
    return request({
        url: `/admin/resetPassword/${id}`,
        method: 'put'
    });
} 