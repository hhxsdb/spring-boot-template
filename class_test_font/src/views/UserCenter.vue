<template>
    <div class="user-center">
        <el-row :gutter="20">
            <!-- 用户基本信息卡片 -->
            <el-col :span="8">
                <el-card class="user-card" shadow="hover">
                    <div class="user-avatar">
                        <el-avatar :size="100" icon="el-icon-user-solid" :src="avatarUrl"></el-avatar>
                    </div>
                    <div class="user-name">{{ userInfo.username }}</div>
                    <div class="user-role">
                        <el-tag v-if="userInfo.username === 'admin'" type="danger">系统管理员</el-tag>
                        <el-tag v-else type="success">普通用户</el-tag>
                    </div>
                    <div class="user-id">账号ID: {{ userInfo.id }}</div>
                </el-card>
            </el-col>

            <!-- 详细信息卡片 -->
            <el-col :span="16">
                <el-card shadow="hover">
                    <div slot="header" class="clearfix">
                        <span>个人详细信息</span>
                    </div>

                    <!-- 基本信息 -->
                    <el-divider content-position="left">基本信息</el-divider>
                    <el-form label-width="100px" class="user-info-form">
                        <el-form-item label="用户名">
                            <span>{{ userInfo.username }}</span>
                        </el-form-item>
                        <el-form-item label="用户ID">
                            <span>{{ userInfo.id }}</span>
                        </el-form-item>
                        <el-form-item label="用户角色">
                            <span v-if="userInfo.username === 'admin'">系统管理员</span>
                            <span v-else>普通用户</span>
                        </el-form-item>
                    </el-form>

                    <!-- 账户安全 -->
                    <el-divider content-position="left">账户安全</el-divider>
                    <el-form label-width="100px" class="user-info-form">
                        <el-form-item label="登录密码">
                            <span>********</span>
                            <el-button type="text" style="margin-left: 10px;"
                                @click="showChangePasswordDialog">修改密码</el-button>
                        </el-form-item>
                    </el-form>

                    <!-- 登录信息 -->
                    <el-divider content-position="left">登录信息</el-divider>
                    <el-form label-width="100px" class="user-info-form">
                        <el-form-item label="登录状态">
                            <el-tag type="success">已登录</el-tag>
                        </el-form-item>
                        <el-form-item label="登录时间">
                            <span>{{ loginTime }}</span>
                        </el-form-item>
                    </el-form>
                </el-card>
            </el-col>
        </el-row>

        <!-- 修改密码对话框 -->
        <el-dialog title="修改密码" :visible.sync="passwordDialogVisible" width="30%">
            <el-form :model="passwordForm" :rules="passwordRules" ref="passwordForm" label-width="100px">
                <el-form-item label="原密码" prop="oldPassword">
                    <el-input v-model="passwordForm.oldPassword" type="password" show-password></el-input>
                </el-form-item>
                <el-form-item label="新密码" prop="newPassword">
                    <el-input v-model="passwordForm.newPassword" type="password" show-password></el-input>
                </el-form-item>
                <el-form-item label="确认密码" prop="confirmPassword">
                    <el-input v-model="passwordForm.confirmPassword" type="password" show-password></el-input>
                </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="passwordDialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleChangePassword">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'UserCenter',
    data() {
        // 确认密码验证
        const validateConfirmPassword = (rule, value, callback) => {
            if (value !== this.passwordForm.newPassword) {
                callback(new Error('两次输入的密码不一致'));
            } else {
                callback();
            }
        };

        return {
            userInfo: JSON.parse(localStorage.getItem('userInfo')) || { id: '', username: '' },
            avatarUrl: '', // 头像URL，可以根据用户名生成不同的头像
            loginTime: new Date().toLocaleString(), // 模拟登录时间

            // 修改密码相关
            passwordDialogVisible: false,
            passwordForm: {
                oldPassword: '',
                newPassword: '',
                confirmPassword: ''
            },
            passwordRules: {
                oldPassword: [
                    { required: true, message: '请输入原密码', trigger: 'blur' }
                ],
                newPassword: [
                    { required: true, message: '请输入新密码', trigger: 'blur' },
                    { min: 6, message: '密码长度不能小于6个字符', trigger: 'blur' }
                ],
                confirmPassword: [
                    { required: true, message: '请确认密码', trigger: 'blur' },
                    { validator: validateConfirmPassword, trigger: 'blur' }
                ]
            }
        };
    },
    created() {
        this.generateAvatar();
    },
    methods: {
        // 生成头像URL
        generateAvatar() {
            // 这里使用用户名的第一个字符作为头像的标识
            // 实际项目中可以使用用户ID从后端获取头像或使用第三方服务如Gravatar
            if (this.userInfo.username === 'admin') {
                this.avatarUrl = 'https://cube.elemecdn.com/0/88/03b0d39583f48206768a7534e55bcpng.png';
            } else {
                this.avatarUrl = '';
            }
        },
        // 显示修改密码对话框
        showChangePasswordDialog() {
            this.passwordDialogVisible = true;
            if (this.$refs.passwordForm) {
                this.$refs.passwordForm.resetFields();
            }
        },
        // 处理修改密码
        handleChangePassword() {
            this.$refs.passwordForm.validate(valid => {
                if (valid) {
                    // 这里应该调用API修改密码
                    this.$message.success('密码修改成功');
                    this.passwordDialogVisible = false;
                }
            });
        }
    }
};
</script>

<style scoped>
.user-center {
    padding: 20px;
}

.user-card {
    text-align: center;
    height: 100%;
}

.user-avatar {
    margin: 20px 0;
}

.user-name {
    font-size: 18px;
    font-weight: bold;
    margin: 10px 0;
}

.user-role {
    margin: 10px 0;
}

.user-id {
    color: #999;
    font-size: 12px;
    margin-top: 10px;
}

.user-info-form {
    margin-top: 10px;
}

.el-divider {
    margin: 20px 0 10px;
}
</style>