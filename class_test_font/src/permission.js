import router from './router'
import store from './store'
import { Message } from 'element-ui'
import NProgress from 'nprogress'
import 'nprogress/nprogress.css'

NProgress.configure({ showSpinner: false })

const whiteList = ['/login'] // 不重定向白名单

router.beforeEach(async (to, from, next) => {
    NProgress.start()

    // 获取token
    const hasToken = localStorage.getItem('token')

    if (hasToken) {
        if (to.path === '/login') {
            // 如果已登录，重定向到首页
            next({ path: '/' })
            NProgress.done()
        } else {
            // 有token，放行
            next()
        }
    } else {
        if (whiteList.indexOf(to.path) !== -1) {
            // 在免登录白名单，直接进入
            next()
        } else {
            // 没有token且不在白名单，重定向到登录页
            next(`/login?redirect=${to.path}`)
            NProgress.done()
        }
    }
})

router.afterEach(() => {
    NProgress.done()
}) 