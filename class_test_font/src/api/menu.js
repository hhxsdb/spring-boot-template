import request from '@/utils/request';

// 获取菜单列表
export function getMenuList() {
    return request({
        url: '/menu/list',
        method: 'get'
    });
}
