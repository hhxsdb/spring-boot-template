<template>
    <!-- 这是一个用于应用初始化的组件，不需要渲染任何内容 -->
    <div style="display: none;"></div>
</template>

<script>
import { getMenuList } from "@/api/menu";
import { useMenuStore } from "@/store/menu";

export default {
    name: "AppInit",
    data() {
        return {};
    },
    created() {
        // 组件创建时立即获取菜单数据
        this.fetchMenuList();
    },
    methods: {
        // 获取菜单列表
        async fetchMenuList() {
            try {
                // 调用API获取菜单列表
                const menuRes = await getMenuList();
                if (menuRes.code === 200) {
                    // 将菜单数据存储到sessionStorage
                    sessionStorage.setItem('menuList', JSON.stringify(menuRes.data));
                    // 同时保存到localStorage作为备份
                    localStorage.setItem('menuList', JSON.stringify(menuRes.data));

                    // 如果使用了Pinia store，也更新store中的数据
                    try {
                        const menuStore = useMenuStore();
                        menuStore.setMenuList(menuRes.data);
                    } catch (storeError) {
                        console.error('更新菜单Store失败:', storeError);
                    }
                } else {
                    console.error('获取菜单列表失败:', menuRes.msg);
                    this.$message.error('获取菜单列表失败: ' + (menuRes.msg || '未知错误'));
                }
            } catch (error) {
                console.error('获取菜单列表出错:', error);
                this.$message.error('获取菜单列表失败: ' + (error.message || '未知错误'));
            }
        }
    }
};
</script>