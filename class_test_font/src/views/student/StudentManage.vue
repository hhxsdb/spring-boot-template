<template>
    <div class="student-manage">
        <h1>学生管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>学生列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加学生</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="studentName" label="学生姓名"></el-table-column>
                <el-table-column prop="studentNo" label="学号"></el-table-column>
                <el-table-column prop="gender" label="性别">
                    <template slot-scope="scope">
                        <span>{{ scope.row.gender === 1 ? '男' : '女' }}</span>
                    </template>
                </el-table-column>
                <el-table-column prop="className" label="班级"></el-table-column>
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
    name: 'StudentManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    studentName: '张三',
                    studentNo: '20210001',
                    gender: 1,
                    className: '计算机科学1班',
                    phone: '13800138001'
                },
                {
                    id: 2,
                    studentName: '李四',
                    studentNo: '20210002',
                    gender: 0,
                    className: '计算机科学1班',
                    phone: '13800138002'
                },
                {
                    id: 3,
                    studentName: '王五',
                    studentNo: '20210003',
                    gender: 1,
                    className: '计算机科学2班',
                    phone: '13800138003'
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加学生功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑学生: ${row.studentName}`);
        },
        handleDelete(row) {
            this.$confirm(`确定要删除学生 ${row.studentName} 吗?`, '提示', {
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
.student-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>