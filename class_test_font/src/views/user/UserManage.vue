<template>
    <div class="user-manage">
        <h1>用户管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>用户列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加用户</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%" v-loading="loading" border stripe>
                <el-table-column prop="id" label="ID" width="100" align="center"></el-table-column>
                <el-table-column prop="username" label="用户名" min-width="180"></el-table-column>
                <el-table-column label="账号类型" width="120" align="center">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.username === 'admin'" type="danger">管理员</el-tag>
                        <el-tag v-else type="success">普通用户</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="200" align="center">
                    <template slot-scope="scope">
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <!-- 用户表单弹窗，标题固定为添加用户 -->
        <el-dialog title="添加用户" :visible.sync="dialogVisible" width="500px">
            <el-form ref="userForm" :model="userForm" :rules="rules" label-width="80px">
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="userForm.username" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="userForm.password" type="password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="submitForm">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
import { getUserList, addUser } from '@/api/user';

export default {
    name: 'UserManage',
    data() {
        return {
            tableData: [],
            loading: false,
            dialogVisible: false,
            userForm: {
                username: '',
                password: ''
            },
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
                ]
            }
        }
    },
    created() {
        this.fetchUserList();
    },
    methods: {
        // 获取用户列表
        async fetchUserList() {
            this.loading = true;
            try {
                const res = await getUserList();
                if (res.code === 200) {
                    // 处理返回数据，移除password字段
                    this.tableData = res.data.map(item => {
                        const { password, ...rest } = item;
                        return rest;
                    });
                } else {
                    this.$message.error(res.msg || '获取用户列表失败');
                }
            } catch (error) {
                console.error('获取用户列表出错:', error);
                this.$message.error('获取用户列表失败');
            } finally {
                this.loading = false;
            }
        },
        // 打开添加用户弹窗
        handleAdd() {
            this.userForm = {
                username: '',
                password: ''
            };
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.$refs.userForm && this.$refs.userForm.clearValidate();
            });
        },
        // 提交表单 - 只处理添加用户
        async submitForm() {
            this.$refs.userForm.validate(async (valid) => {
                if (valid) {
                    try {
                        // 直接调用添加用户API
                        const res = await addUser(this.userForm);
                        if (res.code === 200) {
                            this.$message.success('添加成功');
                            this.dialogVisible = false;
                            this.fetchUserList(); // 刷新列表
                        } else {
                            this.$message.error(res.msg || '添加失败');
                        }
                    } catch (error) {
                        console.error('添加用户失败:', error);
                        this.$message.error('添加失败');
                    }
                }
            });
        },
        handleDelete(row) {
            this.$confirm(`确定要删除用户 ${row.username} 吗?`, '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning'
            }).then(() => {
                this.$message({
                    type: 'success',
                    message: '删除成功!'
                });
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });
        }
    }
}
</script>

<style scoped>
.user-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>