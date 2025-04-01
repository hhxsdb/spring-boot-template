<template>
    <div class="login-container">
        <el-card class="login-card">
            <h2 class="title">系统登录</h2>
            <el-form ref="loginForm" :model="loginForm" :rules="rules" label-width="80px">
                <!-- TODO: 根据需求修改登录表单字段 -->
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="loginForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="loginForm.password" type="password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <!-- 登录按钮 -->
                <el-form-item>
                    <el-button type="primary" @click="handleLogin">登录</el-button>
                    <el-button @click="resetForm">重置</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>
import { login } from "@/api/user";

export default {
    name: "Login",
    data() {
        return {
            // 登录表单数据
            loginForm: {
                username: "",
                password: "",
            },
            // TODO: 根据需求修改表单验证规则
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                ],
            },
        };
    },
    methods: {
        // 登录方法
        handleLogin() {
            this.$refs.loginForm.validate(async (valid) => {
                if (valid) {
                    try {
                        // TODO: 根据实际接口修改登录逻辑
                        const res = await login(this.loginForm);
                        if (res.code === 200) {
                            // 存储用户信息
                            localStorage.setItem('userInfo', JSON.stringify(res.data));

                            // 模拟获取菜单数据并存储 TODO
                            const menuData = {
                                code: 200,
                                msg: "成功",
                                total: 6,
                                data: [
                                    {
                                        id: 1,
                                        menucode: "001",
                                        menuname: "管理员管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "Admin",
                                        menuright: "0",
                                        menucomponent: "admin/AdminManage.vue",
                                        menuicon: "el-icon-s-custom"
                                    },
                                    {
                                        id: 2,
                                        menucode: "002",
                                        menuname: "用户管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "User",
                                        menuright: "0,1",
                                        menucomponent: "user/UserManage.vue",
                                        menuicon: "el-icon-user-solid"
                                    },
                                    {
                                        id: 3,
                                        menucode: "003",
                                        menuname: "教师管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "Teacher",
                                        menuright: "0,1",
                                        menucomponent: "teacher/TeacherManage.vue",
                                        menuicon: "el-icon-s-check"
                                    },
                                    {
                                        id: 4,
                                        menucode: "004",
                                        menuname: "学生管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "Student",
                                        menuright: "0,1",
                                        menucomponent: "student/StudentManage.vue",
                                        menuicon: "el-icon-reading"
                                    },
                                    {
                                        id: 5,
                                        menucode: "005",
                                        menuname: "班级管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "Class",
                                        menuright: "0,1",
                                        menucomponent: "class/ClassManage.vue",
                                        menuicon: "el-icon-s-grid"
                                    },
                                    {
                                        id: 6,
                                        menucode: "006",
                                        menuname: "课程管理",
                                        menulevel: "1",
                                        menuparentcode: null,
                                        menuclick: "Course",
                                        menuright: "0,1,2",
                                        menucomponent: "course/CourseManage.vue",
                                        menuicon: "el-icon-notebook-2"
                                    }
                                ]
                            };

                            // 将菜单数据存储到localStorage
                            localStorage.setItem('menuList', JSON.stringify(menuData.data));

                            // 存储token用于权限校验
                            localStorage.setItem('token', 'admin-token');

                            this.$message.success("登录成功");
                            // 跳转到根路径
                            this.$router.push("/");
                        } else {
                            this.$message.error(res.message || "登录失败");
                        }
                    } catch (error) {
                        this.$message.error("登录失败，请重试");
                    }
                }
            });
        },
        // 重置表单
        resetForm() {
            this.$refs.loginForm.resetFields();
        },
    },
};
</script>

<style scoped>
.login-container {
    width: 100vw;
    height: 100vh;
    display: flex;
    justify-content: center;
    align-items: center;
    background: #f5f5f5;
}

.login-card {
    width: 400px;
    padding: 20px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}

.title {
    text-align: center;
    margin-bottom: 20px;
    color: #333;
}
</style>
