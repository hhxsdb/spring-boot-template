import Vue from "vue";
import App from "./App.vue";
import router from "./router";
import store from "./store";
// ele-ui 引入
import ElementUI from "element-ui";
import "element-ui/lib/theme-chalk/index.css";
// pinia 引入
import { createPinia, PiniaVuePlugin } from "pinia";
// 引入全局样式
import "@/styles/index.scss";
// 权限控制
// import "./permission";
// 引入封装的请求
import request from "@/utils/request";

Vue.use(ElementUI, {
  size: "medium"
});
Vue.use(PiniaVuePlugin);

// 挂载请求
Vue.prototype.$request = request;

const pinia = createPinia();

Vue.config.productionTip = false;

new Vue({
  pinia,
  router,
  store,
  render: (h) => h(App),
}).$mount("#app");
