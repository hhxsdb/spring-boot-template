<template>
    <div class="class-manage">
        <h1>班级管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>班级列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加班级</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="className" label="班级名称"></el-table-column>
                <el-table-column prop="majorName" label="所属专业"></el-table-column>
                <el-table-column prop="headTeacher" label="班主任"></el-table-column>
                <el-table-column prop="studentCount" label="学生人数"></el-table-column>
                <el-table-column prop="createTime" label="创建时间"></el-table-column>
                <el-table-column label="操作" width="180">
                    <template slot-scope="scope">
                        <el-button size="mini" @click="handleEdit(scope.row)">编辑</el-button>
                        <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                    </template>
                </el-table-column>
            </el-table>11
        </el-card>
    </div>
</template>

<script>
export default {
    name: 'ClassManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    className: '计算机科学1班',
                    majorName: '计算机科学与技术',
                    headTeacher: '张老师',
                    studentCount: 30,
                    createTime: '2021-09-01'
                },
                {
                    id: 2,
                    className: '计算机科学2班',
                    majorName: '计算机科学与技术',
                    headTeacher: '李老师',
                    studentCount: 32,
                    createTime: '2021-09-01'
                },
                {
                    id: 3,
                    className: '软件工程1班',
                    majorName: '软件工程',
                    headTeacher: '王老师',
                    studentCount: 35,
                    createTime: '2021-09-01'
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加班级功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑班级: ${row.className}`);
        },
        handleDelete(row) {
            this.$confirm(`确定要删除班级 ${row.className} 吗?`, '提示', {
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
.class-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>