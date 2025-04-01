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
        path: "Admin",
        name: "Admin",
        component: () => import("../views/admin/AdminManage.vue"),
      },
      {
        path: "User",
        name: "User",
        component: () => import("../views/user/UserManage.vue"),
      },
      {
        path: "Teacher",
        name: "Teacher",
        component: () => import("../views/teacher/TeacherManage.vue"),
      },
      {
        path: "Student",
        name: "Student",
        component: () => import("../views/student/StudentManage.vue"),
      },
      {
        path: "Class",
        name: "Class",
        component: () => import("../views/class/ClassManage.vue"),
      },
      {
        path: "Course",
        name: "Course",
        component: () => import("../views/course/CourseManage.vue"),
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
