import { defineStore } from "pinia";

export const useUserStore = defineStore({
    id: "user",
    state: () => ({
        userInfo: null, // 存储用户信息
    }),
    actions: {
        setUserInfo(userData) {
            localStorage.setItem("userInfo", JSON.stringify(userData)); // 手动存储
        },
        logout() {
            this.userInfo = null;
        },
    },
});
