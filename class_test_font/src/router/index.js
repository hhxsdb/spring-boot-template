import Vue from "vue";
import VueRouter from "vue-router";


Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "index",
    component: () => import("../views/Index.vue"),
    children: [
      {
        path: "User",
        name: "User",
        component: () => import("../views/user/UserManage.vue"),
      },
      {
        path: "userCenter",
        name: "UserCenter",
        component: () => import("../views/UserCenter.vue"),
      },
      {
        path: "", // 默认子路由
        redirect: "User" // 默认重定向到用户管理页面
      }
    ],
  },
  {
    path: "/login",
    name: "Login",
    component: () => import("../views/Login.vue"),
  },
];

const router = new VueRouter({
  routes,
});



// export function resetRouter() {
//   router.matcher = new VueRouter({
//     mode: 'history',
//     routes: []
//   }).matcher
// }
export default router;
