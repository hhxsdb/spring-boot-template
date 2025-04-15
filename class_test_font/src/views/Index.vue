<template>
    <el-container style="height: 100vh; border: 1px solid #eee">
        <Aside :isCollapse="isCollapse"></Aside>
        <el-container>
            <el-header style="text-align: right; font-size: 12px;padding:0px">
                <Header @changeCollapse="handleCollapse" :isCollapse="isCollapse"></Header>
            </el-header>
            <el-main>
                <router-view></router-view>
            </el-main>
        </el-container>
    </el-container>
</template>
<style></style>

<script>
import Main from "../components/index/Main.vue";
import Aside from "../components/index/Aside.vue";
import Header from "../components/index/Header.vue";
export default {
    components: {
        Aside,
        Header,
        Main
    },
    data() {
        return {
            isCollapse: false,
        }
    },
    methods: {
        handleCollapse() {
            this.isCollapse = !this.isCollapse;
        }
    },
};
</script>