<template>
    <div class="course-manage">
        <h1>课程管理</h1>
        <el-card class="box-card">
            <div slot="header" class="clearfix">
                <span>课程列表</span>
                <el-button style="float: right; padding: 3px 0" type="text" @click="handleAdd">添加课程</el-button>
            </div>
            <el-table :data="tableData" style="width: 100%">
                <el-table-column prop="id" label="ID" width="80"></el-table-column>
                <el-table-column prop="courseName" label="课程名称"></el-table-column>
                <el-table-column prop="courseCode" label="课程代码"></el-table-column>
                <el-table-column prop="credit" label="学分"></el-table-column>
                <el-table-column prop="teacherName" label="授课教师"></el-table-column>
                <el-table-column prop="courseHours" label="课时"></el-table-column>
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
    name: 'CourseManage',
    data() {
        return {
            tableData: [
                {
                    id: 1,
                    courseName: 'Java程序设计',
                    courseCode: 'CS001',
                    credit: 4,
                    teacherName: '张老师',
                    courseHours: 64
                },
                {
                    id: 2,
                    courseName: '数据结构',
                    courseCode: 'CS002',
                    credit: 3,
                    teacherName: '李老师',
                    courseHours: 48
                },
                {
                    id: 3,
                    courseName: '计算机网络',
                    courseCode: 'CS003',
                    credit: 3,
                    teacherName: '王老师',
                    courseHours: 48
                },
                {
                    id: 4,
                    courseName: '操作系统',
                    courseCode: 'CS004',
                    credit: 4,
                    teacherName: '赵老师',
                    courseHours: 64
                }
            ]
        }
    },
    methods: {
        handleAdd() {
            this.$message.success('添加课程功能开发中...');
        },
        handleEdit(row) {
            this.$message.success(`编辑课程: ${row.courseName}`);
        },
        handleDelete(row) {
            this.$confirm(`确定要删除课程 ${row.courseName} 吗?`, '提示', {
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
.course-manage {
    padding: 20px;
}

.box-card {
    margin-top: 20px;
}
</style>