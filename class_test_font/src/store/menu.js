import { defineStore } from "pinia";
import router from "@/router";

function addRoutes(menuList) {
    let routes = router.options.routes;
    console.log(typeof routes);
    routes.forEach(routesItem => {
        if (routesItem.name === "index") {
            menuList.forEach(menu => {
                let route = {
                    path: "/" + menu.menuclick,
                    name: menu.menuname,
                    component: () => import(`../components/` + menu.menucomponent),
                    meta: {
                        title: menu.menuname,
                    },
                };
                routesItem.children.push(route);
            });

        }
    })
    router.addRoutes(routes);
}
export const useMenuStore = defineStore({
    id: "menu",
    state: () => ({
        menuList: [], // 存储菜单列表
    }),
    actions: {
        setMenuList(menuList) {
            localStorage.setItem("menuList", JSON.stringify(menuList)); // 手动存储
            this.menuList = menuList; // 手动更新
            addRoutes(menuList); // 手动添加路由
        },

    },
});
