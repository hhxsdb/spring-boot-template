module.exports = {
    devServer: {
        port: 8080,
        proxy: {
            '/api': {
                target: 'http://localhost:8090', // 修改为后端服务器地址
                changeOrigin: true,
                pathRewrite: {
                    '^/api': '' // 重写路径
                }
            }
        }
    }
} 