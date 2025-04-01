<template>
    <div class="teacher-manage">
        <h1>教师管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>教师列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加教师</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="teacherName" label="教师姓名"></el-table-column>
                <el-table-column prop="gender" label="性别">
                    <template slot-scope="scope">
                        <span>{{ scope.row.gender === 1 ? '男' : '女' }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="subject" label="教授科目"></el-table-column>
                <el-table-column prop="phone" label="联系电话"></el-table-column>
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
    name: 'TeacherManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    teacherName: '张老师',
                    gender: 1,
                    subject: '数学',
                    phone: '13800138000'
                },
                {
                    id: 2,
                    teacherName: '李老师',
                    gender: 0,
                    subject: '语文',
                    phone: '13900139000'
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加教师功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑教师: ${row.teacherName}`);
        },
        handleDelete(row) {
            this.$confirm(`确定要删除教师 ${row.teacherName} 吗?`, '提示', {
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
.teacher-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>