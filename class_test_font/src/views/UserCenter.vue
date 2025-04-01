<template>
    <div class="user-info">
        <!-- 标题 -->
        <h2>个人信息</h2>
        <!-- 个人信息展示 -->
        <el-descriptions :column="1" border>
            <el-descriptions-item label="用户ID">{{ userInfo.id }}</el-descriptions-item>
            <el-descriptions-item label="用户名">{{ userInfo.username }}</el-descriptions-item>
            <el-descriptions-item label="昵称">{{ userInfo.nickname }}</el-descriptions-item>
            <el-descriptions-item label="邮箱">{{ userInfo.email }}</el-descriptions-item>
            <el-descriptions-item label="手机">{{ userInfo.phone }}</el-descriptions-item>
            <el-descriptions-item label="角色">
                <el-tag :type="userInfo.roleType">{{ userInfo.roleName }}</el-tag>
            </el-descriptions-item>
            <el-descriptions-item label="状态">
                <el-tag :type="userInfo.status === 1 ? 'success' : 'danger'">
                    {{ userInfo.status === 1 ? '正常' : '禁用' }}
                </el-tag>
            </el-descriptions-item>
        </el-descriptions>
    </div>
</template>

<script>
export default {
    name: 'UserInfo',
    data() {
        return {
            // TODO: 根据实际存储的用户信息修改默认值
            userInfo: JSON.parse(localStorage.getItem('userInfo')) || {
                id: '',
                username: '',
                nickname: '',
                email: '',
                phone: '',
                roleName: '',
                roleType: 'info',
                status: 0
            }
        };
    }
};
</script>

<style scoped>
.user-info {
    padding: 20px;
    max-width: 600px;
    margin: 0 auto;
}

h2 {
    text-align: center;
    margin-bottom: 20px;
}
</style>