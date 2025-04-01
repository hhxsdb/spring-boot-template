<template>
  <div id="app">
    <router-view />
  </div>
</template>

<style>
* {
  margin: 0px;
  padding: 0px;
}
</style>

<script>
import router from "@/router";
export default {
  name: "App",
  router,
  data() {
    return {
      menuList: localStorage.getItem("menuList") ? JSON.parse(localStorage.getItem("menuList")) : [],
    };
  },
  methods: {
    // addRoutes() {
    //   let routes = router.options.routes;
    //   console.log(typeof routes);
    //   // 遍历添加导航
    //   routes.forEach(routesItem => {
    //     if (routesItem.name === "index") {
    //       this.menuList.forEach(menu => {
    //         let route = {
    //           path: "/" + menu.menuclick,
    //           name: menu.menuname,
    //           component: () => import(`@/components/` + menu.menucomponent),
    //           meta: {
    //             title: menu.menuname,
    //           },
    //         };
    //         routesItem.children.push(route);
    //       });

    //     }
    //   })
    //   router.addRoutes(routes);
    // }
  },
  created() {
    // this.addRoutes();
  },
  mounted() {

  },
};  
</script>
