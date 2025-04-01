# Vue2 Admin Template

基于 Vue2 + Element UI 的后台管理系统模板

## 特性

- 基于 Vue2、Element-UI
- 支持 Vuex 和 Pinia 状态管理
- 内置权限验证
- axios 请求封装
- 环境配置
- 全局 SCSS 样式
- 响应式布局

## 开发

```bash
# 克隆项目
git clone [项目地址]

# 安装依赖
npm install

# 启动服务
npm run serve
```

## 发布

```bash
# 构建生产环境
npm run build
```

## 目录结构

```
├── public                     # 静态资源
├── src                       # 源代码
│   ├── api                   # 所有请求
│   ├── assets                # 主题 字体等静态资源
│   ├── components            # 全局公用组件
│   ├── router               # 路由
│   ├── store                # 全局 store管理
│   ├── styles               # 全局样式
│   ├── utils                # 全局公用方法
│   ├── views                # views 所有页面
│   ├── App.vue              # 入口页面
│   ├── main.js              # 入口文件
│   └── permission.js        # 权限管理
├── .env.development         # 开发环境变量
├── .env.production          # 生产环境变量
├── vue.config.js            # vue-cli 配置
└── package.json             # package.json
```

## 其他说明

1. 权限验证：使用路由守卫进行权限验证
2. 环境配置：区分开发环境和生产环境
3. 请求封装：统一处理请求和响应
4. 状态管理：支持 Vuex 和 Pinia 两种方案
