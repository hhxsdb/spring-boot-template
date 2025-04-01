<template>
    <div class="admin-manage">
        <h1>管理员管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>管理员列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加管理员</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="username" label="用户名"></el-table-column>
                <el-table-column prop="realname" label="真实姓名"></el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
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
    name: 'AdminManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    username: 'admin',
                    realname: '超级管理员',
                    createTime: '2023-01-01 00:00:00'
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加管理员功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑管理员: ${row.username}`);
        },
        handleDelete(row) {
            this.$confirm(`确定要删除管理员 ${row.username} 吗?`, '提示', {
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
.admin-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>