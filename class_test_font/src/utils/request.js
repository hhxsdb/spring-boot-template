import axios from 'axios';
import { Message } from 'element-ui'; // 如果使用 Element UI，可用 Message 提示错误信息
import store from '@/store';

// 创建 axios 实例
const service = axios.create({
    baseURL: '/api', // 直接使用固定的baseURL
    timeout: 10000
});

// 请求拦截器
service.interceptors.request.use(
    config => {
        // 在请求发送之前做一些处理
        const token = localStorage.getItem('token');
        if (token) {
            config.headers['Authorization'] = `Bearer ${token}`;
        }
        return config;
    },
    error => {
        console.log(error);
        return Promise.reject(error);
    }
);

// 响应拦截器
service.interceptors.response.use(
    response => {
        const res = response.data;
        // 根据自定义错误码判断请求是否成功
        if (res.code === 200) {
            return res;
        } else {
            Message({
                message: res.message || '请求失败',
                type: 'error',
                duration: 5 * 1000
            });
            return Promise.reject(new Error(res.message || '请求失败'));
        }
    },
    error => {
        console.log('err' + error);
        Message({
            message: error.message,
            type: 'error',
            duration: 5 * 1000
        });
        return Promise.reject(error);
    }
);

export default service;
