<template>
    <div>
        <el-aside :width="isCollapse ? '60px' : '150px'" style="background-color: rgb(238, 241, 246)">
            <el-menu style="height: 100vh;" background-color="#545c64" text-color="#fff" active-text-color="#ffd04b"
                :collapse="isCollapse" :collapse-transition="true" :router="true" :default-active="activeMenu">
                <el-menu-item :index="item.menuclick" v-for="(item, index) in menuList" :key="index">
                    <i :class="item.menuicon"></i>
                    <span slot="title">{{ item.menuname }}</span>
                </el-menu-item>
            </el-menu>
        </el-aside>
    </div>
</template>
<script>
export default {
    name: 'Aside',
    data() {
        return {
            menuList: [],
        }
    },
    props: {
        isCollapse: Boolean
    },
    methods: {
        // 获取菜单数据
        getMenuList() {
            try {
                // 优先从sessionStorage获取
                const sessionMenuData = sessionStorage.getItem('menuList');
                if (sessionMenuData) {
                    this.menuList = JSON.parse(sessionMenuData);
                    return;
                }

                // 如果sessionStorage中没有，再从localStorage获取
                const localMenuData = localStorage.getItem('menuList');
                if (localMenuData) {
                    this.menuList = JSON.parse(localMenuData);
                    // 同时将数据也存入sessionStorage
                    sessionStorage.setItem('menuList', localMenuData);
                }
            } catch (error) {
                console.error('获取菜单数据失败', error);
            }
        }
    },
    computed: {
        // 获取当前激活的菜单
        activeMenu() {
            const route = this.$route.path;
            const path = route.split('/');
            return path[path.length - 1] || 'User';
        }
    },
    created() {
        // 组件创建时获取菜单数据
        this.getMenuList();
    },
    mounted() {
        // 组件挂载后，如果没有菜单数据，尝试再次获取
        if (this.menuList.length === 0) {
            this.getMenuList();
        }
    }
}
</script>
<style lang="scss" scoped>
.el-aside {
    color: #333;
    transition: width 0.3s;
}
</style>
