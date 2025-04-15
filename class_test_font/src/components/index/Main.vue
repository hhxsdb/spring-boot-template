<template>
    <div>
        <el-form label-width="80px" :model="searchForm" style="padding:10px" ref="searchFormRef" inline>
            <!-- 输入框 -->
            <el-form-item label="" prop="name">
                <el-input placeholder="姓名查询" v-model="searchForm.name" style="width: 200px;margin-right: 10px;"
                    prefix-icon="el-icon-search" @keyup.enter.native="getUsersList"></el-input>
            </el-form-item>
            <!-- 选择框 -->
            <el-form-item label="" prop="sex">
                <el-select v-model="searchForm.sex" placeholder="性别">
                    <el-option label="女" value="0"></el-option>
                    <el-option label="男" value="1"></el-option>
                </el-select>
            </el-form-item>
            <!-- 按钮 -->
            <el-button type="primary" icon="el-icon-search" @click="getUsersList">搜索</el-button>
            <el-button type="danger" icon="el-icon-refresh" @click="resetForm('searchFormRef')">重置</el-button>
            <el-button type="success" icon="el-icon-plus" @click="clickAdd">新增用户</el-button>
        </el-form>
        <el-table :data="tableData" :header-cell-style="{
            background: '#f2f2f2', color: '#000000'
        }" border>
            <el-table-column prop="id" label="ID" width="">
            </el-table-column>
            <el-table-column prop="no" label="账号" width="">
            </el-table-column>
            <el-table-column prop="name" label="姓名">
            </el-table-column>
            <el-table-column prop="password" label="密码">
            </el-table-column>
            <el-table-column prop="age" label="年龄">
            </el-table-column>
            <el-table-column prop="sex" label="性别">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.sex === 1 ? 'primary' : 'success'" disable-transitions>{{ scope.row.sex ===
                        1 ?
                        '男' : '女' }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="phone" label="电话">
            </el-table-column>
            <el-table-column prop="roleId" label="角色">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.roleId === 0 ? 'danger' : (scope.row.roleId === 1 ? 'primary' : 'success')"
                        disable-transitions>{{ scope.row.roleId === 0 ? '超级管理员' : (scope.row.roleId === 1 ? '管理员' :
                            '用户')
                        }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="isvalid" label="是否可用">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.isvalid === 'Y' ? 'primary' : 'danger'" disable-transitions>{{
                        scope.row.isvalid
                            ===
                            'Y' ? '可用' : '不可用'
                    }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150px">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
            :page-sizes="[5, 10]" :page-size="100" layout="total, sizes, prev, pager, next, jumper" :total="total">
        </el-pagination>
        <!-- 新增用户弹窗 -->
        <el-dialog :title="model === 'add' ? '新增用户' : '编辑用户'" :visible.sync="dialogVisible" width="30%">
            <el-form :model="userForm" ref="userFormRef" label-width="80px" :rules="rules">
                <el-form-item label="账号" prop="no">
                    <el-input v-model="userForm.no" placeholder="请输入账号" :disabled="model === 'edit'"></el-input>
                </el-form-item>
                <el-form-item label="姓名" prop="name">
                    <el-input v-model="userForm.name" placeholder="请输入姓名"></el-input> </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="userForm.password" placeholder="请输入密码"></el-input>
                </el-form-item>
                <el-form-item label="年龄" prop="age">
                    <el-input v-model="userForm.age" placeholder="请输入年龄"></el-input>
                </el-form-item>
                <el-form-item label="性别" prop="sex">
                    <el-radio-group v-model="userForm.sex">
                        <el-radio :label="0">女</el-radio>
                        <el-radio :label="1">男</el-radio>
                    </el-radio-group>
                </el-form-item>
                <el-form-item label="电话" prop="phone">
                    <el-input v-model="userForm.phone" placeholder="请输入电话"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="roleId">
                    <el-select v-model="userForm.roleId" placeholder="请选择角色">
                        <el-option label="超级管理员" value="0"></el-option>
                        <el-option label="管理员" value="1"></el-option>
                        <el-option label="用户" value="2"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="是否可用" prop="isvalid">
                    <el-switch v-model="userForm.isvalid"></el-switch>
                </el-form-item> -->
            </el-form>
            <span slot="footer" class="dialog-footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleAddOrEditUser">确 定</el-button>
            </span>
        </el-dialog>
    </div>
</template>
<script>
import { } from "@/api/user"
export default {
    name: 'Main',
    data() {
        // 账号验证规则
        var validateNo = async (rule, value, callback) => {
            if (!value) {
                callback(new Error('请输入账号'));
            } else if (!/^[a-zA-Z0-9]{2,10}$/.test(value)) {
                callback(new Error('账号必须为2-10位字母或数字'));
            } else {
                // **仅在新增用户时检查“账号是否已存在”**
                if (this.model === "add") {
                    const res = await getUserByNo(value);
                    if (res.data.data.length !== 0) {
                        callback(new Error('账号已存在'));
                    } else {
                        callback();
                    }
                } else {
                    callback(); // 编辑模式下直接通过校验
                }
            }
        };
        return {
            tableData: [],
            // 分页参数
            pageNum: 1,
            pageSize: 5,
            total: 0,
            // 搜索参数
            searchForm: {
                name: "",
                sex: ""
            },
            // 新增用户弹窗
            dialogVisible: false,
            // 新增用户表单
            userForm: {
                id: "",
                no: "",
                name: "",
                password: "",
                age: "",
                sex: "",
                phone: "",
                roleId: "",
            },
            // 表单验证规则
            rules: {
                no: [
                    { validator: validateNo, required: true, trigger: 'blur' },

                ],
                name: [
                    { required: true, message: '请输入姓名', trigger: 'blur' },
                    { pattern: /^[\u4e00-\u9fa5a-zA-Z]{2,10}$/, message: '姓名必须是2-10位中文或英文', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { pattern: /^[a-zA-Z0-9]{6,}$/, message: '密码必须为6位字母或数字', trigger: 'blur' }
                ],
                age: [
                    { required: true, message: '请输入年龄', trigger: 'blur' },
                    { pattern: /^(?:[1-9][0-9]?|1[01][0-9]|120)$/, message: '年龄必须是1-120之间的整数', trigger: 'blur' }
                ],
                sex: [
                    { required: true, message: '请选择性别', trigger: 'change' }
                ],
                phone: [
                    { required: true, message: '请输入电话', trigger: 'blur' },
                    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的11位手机号', trigger: 'blur' }
                ],
                roleId: [
                    { required: true, message: '请选择角色', trigger: 'change' }
                ]
            },
            // 弹窗模式
            model: "add"
        }
    },
    mounted() { // 页面加载完成后执行
        this.getUsersList();
    },
    methods: {
        // 获取用户信息 表格数据
        async getUsersList() {
            const res = await getUserPage({
                pageNum: this.pageNum, pageSize: this.pageSize, params: {
                    name: this.searchForm.name,
                    sex: this.searchForm.sex
                }
            });
            this.tableData = res.data.data;
            this.total = res.data.total;
        },
        // 点击编辑 用户信息
        handleEdit(index, row) {
            this.model = "edit";
            this.$nextTick(() => {
                this.resetForm("userFormRef")
            })
            this.userForm = row;
            this.userForm.roleId = String(row.roleId);
            this.dialogVisible = true;

        },
        // 点击删除 用户信息
        handleDelete(index, row) {
            this.$confirm('此操作将永久删除用户, 是否继续?', '提示', {
                confirmButtonText: '确定',
                cancelButtonText: '取消',
                type: 'warning',
            }).then(async () => {
                let res = await deleteUser({ id: row.id });
                if (res.data.code === 200) {
                    this.$message.success("删除成功");
                    this.getUsersList();
                } else {
                    this.$message.error("删除失败");
                }
            }).catch(() => {
                this.$message({
                    type: 'info',
                    message: '已取消删除'
                });
            });

        },
        // 切换分页
        handleSizeChange(size) {
            this.pageSize = size;
            this.getUsersList();
        },
        handleCurrentChange(currentPage) {
            this.pageNum = currentPage;
            this.getUsersList();
        },
        // 重置搜索条件
        resetForm(formName) {
            this.$refs[formName].resetFields();

            this.getUsersList();
        },
        // 点击 新增用户
        clickAdd() {
            this.model = "add";
            this.dialogVisible = true;
            this.$nextTick(() => {
                this.resetForm("userFormRef")
            })
        },
        // 点击提交 新增用户/编辑用户
        handleAddOrEditUser() {
            this.$refs.userFormRef.validate(async (validate) => {
                if (validate) {
                    let res = null;
                    switch (this.model) {
                        case "add":
                            res = await addUser(this.userForm);
                            break;
                        case "edit":
                            res = await editUser(this.userForm);
                            break;
                        default:
                            break;
                    }
                    if (res.data.code === 200) {
                        this.$message.success("成功");
                        this.dialogVisible = false;
                        this.getUsersList();
                    } else {
                        this.$message.error("失败");
                    }

                } else {
                    return "表单校验出错"
                }
            });


        }

    }
}
</script>
<style lang="scss" scoped></style>
