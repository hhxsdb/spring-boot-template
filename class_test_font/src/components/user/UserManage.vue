<template>
    <div>
        <el-form label-width="80px" :model="searchForm" style="padding:10px" ref="searchFormRef" inline>
            <!-- TODO: 根据需求添加更多搜索条件 -->
            <el-form-item label="" prop="keyword">
                <el-input placeholder="关键字搜索" v-model="searchForm.keyword" style="width: 200px;margin-right: 10px;"
                    prefix-icon="el-icon-search" @keyup.enter.native="loadData"></el-input>
            </el-form-item>
            <!-- TODO: 根据需求添加状态筛选 -->
            <el-form-item label="" prop="status">
                <el-select v-model="searchForm.status" placeholder="状态">
                    <el-option label="正常" value="1"></el-option>
                    <el-option label="禁用" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-button type="primary" icon="el-icon-search" @click="loadData">搜索</el-button>
            <el-button type="danger" icon="el-icon-refresh" @click="resetForm('searchFormRef')">重置</el-button>
            <el-button type="success" icon="el-icon-plus" @click="handleAdd">新增</el-button>
        </el-form>

        <!-- TODO: 根据需求修改表格列 -->
        <el-table :data="tableData" :header-cell-style="{ background: '#f2f2f2', color: '#000000' }" border>
            <el-table-column prop="id" label="ID" width="80"></el-table-column>
            <el-table-column prop="username" label="用户名"></el-table-column>
            <el-table-column prop="nickname" label="昵称"></el-table-column>
            <el-table-column prop="email" label="邮箱"></el-table-column>
            <el-table-column prop="phone" label="手机号"></el-table-column>
            <el-table-column prop="role" label="角色">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.roleType">{{ scope.row.roleName }}</el-tag>
                </template>
            </el-table-column>
            <el-table-column prop="status" label="状态">
                <template slot-scope="scope">
                    <el-tag :type="scope.row.status === 1 ? 'success' : 'danger'">
                        {{ scope.row.status === 1 ? '正常' : '禁用' }}
                    </el-tag>
                </template>
            </el-table-column>
            <el-table-column label="操作" width="150" fixed="right">
                <template slot-scope="scope">
                    <el-button size="mini" type="primary" @click="handleEdit(scope.row)">编辑</el-button>
                    <el-button size="mini" type="danger" @click="handleDelete(scope.row)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <!-- 分页 -->
        <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum"
            :page-sizes="[10, 20, 50, 100]" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper"
            :total="total">
        </el-pagination>

        <!-- 新增/编辑弹窗 -->
        <el-dialog :title="dialogTitle" :visible.sync="dialogVisible" width="500px" @close="resetForm('formRef')">
            <el-form :model="form" ref="formRef" label-width="80px" :rules="rules">
                <!-- TODO: 根据需求修改表单字段 -->
                <el-form-item label="用户名" prop="username">
                    <el-input v-model="form.username" placeholder="请输入用户名" :disabled="mode === 'edit'"></el-input>
                </el-form-item>
                <el-form-item label="昵称" prop="nickname">
                    <el-input v-model="form.nickname" placeholder="请输入昵称"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password" v-if="mode === 'add'">
                    <el-input v-model="form.password" type="password" placeholder="请输入密码" show-password></el-input>
                </el-form-item>
                <el-form-item label="邮箱" prop="email">
                    <el-input v-model="form.email" placeholder="请输入邮箱"></el-input>
                </el-form-item>
                <el-form-item label="手机号" prop="phone">
                    <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
                </el-form-item>
                <el-form-item label="角色" prop="roleId">
                    <el-select v-model="form.roleId" placeholder="请选择角色">
                        <el-option v-for="item in roleOptions" :key="item.value" :label="item.label"
                            :value="item.value">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-switch v-model="form.status" :active-value="1" :inactive-value="0"></el-switch>
                </el-form-item>
            </el-form>
            <div slot="footer">
                <el-button @click="dialogVisible = false">取 消</el-button>
                <el-button type="primary" @click="handleSubmit">确 定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
export default {
    name: 'UserManage',
    data() {
        return {
            // 搜索表单
            searchForm: {
                keyword: '',
                status: ''
            },
            // 表格数据
            tableData: [],
            // 分页参数
            pageNum: 1,
            pageSize: 10,
            total: 0,
            // 弹窗控制
            dialogVisible: false,
            mode: 'add', // add 或 edit
            // 表单数据
            form: {
                id: '',
                username: '',
                nickname: '',
                password: '',
                email: '',
                phone: '',
                roleId: '',
                status: 1
            },
            // TODO: 根据需求修改角色选项
            roleOptions: [
                { value: '1', label: '管理员' },
                { value: '2', label: '普通用户' }
            ],
            // TODO: 根据需求修改表单验证规则
            rules: {
                username: [
                    { required: true, message: '请输入用户名', trigger: 'blur' },
                    { min: 2, max: 20, message: '长度在 2 到 20 个字符', trigger: 'blur' }
                ],
                password: [
                    { required: true, message: '请输入密码', trigger: 'blur' },
                    { min: 6, max: 20, message: '长度在 6 到 20 个字符', trigger: 'blur' }
                ],
                email: [
                    { required: true, message: '请输入邮箱', trigger: 'blur' },
                    { type: 'email', message: '请输入正确的邮箱地址', trigger: 'blur' }
                ],
                phone: [
                    { required: true, message: '请输入手机号', trigger: 'blur' },
                    { pattern: /^1[3-9]\d{9}$/, message: '请输入正确的手机号', trigger: 'blur' }
                ],
                roleId: [
                    { required: true, message: '请选择角色', trigger: 'change' }
                ]
            }
        }
    },
    computed: {
        dialogTitle() {
            return this.mode === 'add' ? '新增用户' : '编辑用户'
        }
    },
    created() {
        this.loadData()
    },
    methods: {
        // 加载表格数据
        async loadData() {
            try {
                // TODO: 调用实际的API接口
                const res = await this.$request.post('/user/page', {
                    pageNum: this.pageNum,
                    pageSize: this.pageSize,
                    ...this.searchForm
                })
                if (res.code === 200) {
                    this.tableData = res.data.list
                    this.total = res.data.total
                }
            } catch (error) {
                console.error('加载数据失败', error)
                this.$message.error('加载数据失败')
            }
        },
        // 重置表单
        resetForm(formName) {
            this.$refs[formName].resetFields()
            if (formName === 'formRef') {
                this.form = {
                    id: '',
                    username: '',
                    nickname: '',
                    password: '',
                    email: '',
                    phone: '',
                    roleId: '',
                    status: 1
                }
            }
        },
        // 新增按钮点击
        handleAdd() {
            this.mode = 'add'
            this.dialogVisible = true
        },
        // 编辑按钮点击
        handleEdit(row) {
            this.mode = 'edit'
            this.dialogVisible = true
            this.$nextTick(() => {
                this.form = { ...row }
            })
        },
        // 删除按钮点击
        handleDelete(row) {
            this.$confirm('确认删除该用户?', '提示', {
                type: 'warning'
            }).then(async () => {
                try {
                    // TODO: 调用实际的删除API
                    const res = await this.$request.delete(`/user/${row.id}`)
                    if (res.code === 200) {
                        this.$message.success('删除成功')
                        this.loadData()
                    }
                } catch (error) {
                    this.$message.error('删除失败')
                }
            }).catch(() => { })
        },
        // 提交表单
        handleSubmit() {
            this.$refs.formRef.validate(async valid => {
                if (valid) {
                    try {
                        // TODO: 调用实际的保存API
                        const api = this.mode === 'add' ? '/user' : `/user/${this.form.id}`
                        const method = this.mode === 'add' ? 'post' : 'put'
                        const res = await this.$request[method](api, this.form)
                        if (res.code === 200) {
                            this.$message.success(`${this.mode === 'add' ? '新增' : '编辑'}成功`)
                            this.dialogVisible = false
                            this.loadData()
                        }
                    } catch (error) {
                        this.$message.error(`${this.mode === 'add' ? '新增' : '编辑'}失败`)
                    }
                }
            })
        },
        // 分页大小改变
        handleSizeChange(val) {
            this.pageSize = val
            this.loadData()
        },
        // 页码改变
        handleCurrentChange(val) {
            this.pageNum = val
            this.loadData()
        }
    }
}
</script>

<style lang="scss" scoped>
.el-pagination {
    margin-top: 20px;
    text-align: right;
}
</style>
