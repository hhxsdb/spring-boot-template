<template>
    <div class="user-manage">
        <h1>用户管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>用户列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加用户</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="realname" label="真实姓名"></el-table-column>
                <el-table-column prop="role" label="角色">
                    <template slot-scope="scope">
                        <el-tag v-if="scope.row.role === 0">管理员</el-tag>
                        <el-tag v-else-if="scope.row.role === 1" type="success">用户</el-tag>
                        <el-tag v-else type="info">未知</el-tag>
                    </template>
                </el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>
    </div>
</template>

<script>
export default {
    name: 'UserManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    username: 'admin',
                    realname: '管理员',
                    role: 0
                },
                {
                    id: 2,
                    username: 'test',
                    realname: '测试用户',
                    role: 1
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加用户功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑用户: ${row.username}`);
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