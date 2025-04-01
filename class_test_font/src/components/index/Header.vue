<template>
    <el-header
        style="text-align: right; font-size: 12px ; padding: 10px;display: flex; justify-content: space-between; align-items: center;">
        <i type="menu" :class="isCollapse ? 'el-icon-s-unfold' : 'el-icon-s-fold'" @click="changeCollapse()"
            style="font-size: 24px; cursor: pointer; margin-right: 10px"></i>
        <!-- todo -->
        <h3 style="font-size: 24px;">欢迎来到</h3>
        <el-dropdown>
            <span>{{ userInfo.name }}</span>
            <el-dropdown-menu slot="dropdown">
                <el-dropdown-item @click.native="to_user()">个人中心</el-dropdown-item>
                <el-dropdown-item @click.native="logout">退出登录</el-dropdown-item>
            </el-dropdown-menu>
            <i class="el-icon-setting" style="margin-left: 15px"></i>
        </el-dropdown>
    </el-header>
</template>
<script>
export default {
    name: 'Header',
    data() {
        return {
            icon: 'el-icon-s-fold',
            userInfo: JSON.parse(localStorage.getItem('userInfo') || '{}')
        }
    },
    props: {
        isCollapse: {
            type: Boolean,
            default: false
        }
    },
    methods: {
        to_user() {
            this.$router.push('/userCenter');
        },
        logout() {
            this.$router.push('/login');
            localStorage.removeItem('userInfo');
            localStorage.removeItem('menuList');

        },
        changeCollapse() {
            this.$emit('changeCollapse');
        }
    }
}
</script>
<style lang="scss" scoped>
.el-header {
    background-color: white;
    color: #333;
    line-height: 60px;
    border-bottom: 1px solid grey;
}
</style>
