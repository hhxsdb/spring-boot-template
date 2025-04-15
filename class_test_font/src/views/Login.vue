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
                        // 调用登录接口
                        const res = await login(this.loginForm);
                        if (res.code === 200) {
                            // 提取用户信息，移除密码字段
                            const { password, ...userInfo } = res.data.user;

                            // 存储用户信息(不包含密码)
                            localStorage.setItem('userInfo', JSON.stringify(userInfo));

                            // 存储真实token
                            localStorage.setItem('token', res.data.token);

                            this.$message.success("登录成功");
                            // 跳转到个人中心页面
                            this.$router.push("/userCenter");
                        } else {
                            this.$message.error(res.msg || "登录失败");
                        }
                    } catch (error) {
                        console.error("登录失败:", error);
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
