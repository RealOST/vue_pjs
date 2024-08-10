<template>
  <div class="userlist">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>系统管理</el-breadcrumb-item>
      <el-breadcrumb-item>会员管理</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="card-container">
      <div>
        <el-form :inline="true" :model="formInline" class="demo-form-inline">
          <el-form-item label="角色名称">
            <el-input placeholder="请输入角色名称" clearable></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-select
              v-model="formInline.region"
              placeholder="请选择"
              clearable
            >
              <el-option label="区域一" value="shanghai"></el-option>
              <el-option label="区域二" value="beijing"></el-option>
            </el-select>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">查询</el-button>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="onSubmit">重置</el-button>
          </el-form-item>
        </el-form>
        <div class="btn-group">
          <el-button type="success" icon="el-icon-plus" size="small"
            >添加</el-button
          >
          <el-button type="primary" icon="el-icon-edit" size="small"
            >修改</el-button
          >
          <el-button type="danger" icon="el-icon-delete" size="small"
            >删除</el-button
          >
        </div>
        <el-table
          :data="tableData"
          border
          style="width: 100%"
          :header-cell-style="{ 'text-align': 'center' }"
          :cell-style="{ 'text-align': 'center' }"
        >
          <el-table-column type="selection" width="55"> </el-table-column>
          <el-table-column type="index" label="编号"> </el-table-column>
          <el-table-column prop="name" label="角色名称"> </el-table-column>
          <el-table-column prop="status" label="状态"> </el-table-column>
          <el-table-column prop="date" label="描述"> </el-table-column>
          <el-table-column fixed="right" label="操作" width="150">
            <template slot-scope="scope">
              <el-button
                @click="handleClick(scope.row)"
                type="primary"
                size="small"
                >修改</el-button
              >
              <el-button type="danger" size="small">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage4"
          :page-sizes="[100, 200, 300, 400]"
          :page-size="100"
          layout="total, sizes, prev, pager, next, jumper"
          :total="400"
        >
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  data() {
    return {
      formInline: {
        user: "",
        region: "",
      },
      tableData: [
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
        {
          name: "王小虎",
          status: "启用",
          date: "2016-05-02",
        },
      ],
      currentPage1: 1,
      currentPage2: 2,
      currentPage3: 4,
      currentPage4: 8,
    };
  },
  methods: {
    onSubmit() {
      console.log("submit!");
    },
    handleClick(row) {
      console.log(row);
    },
    handleSizeChange(val) {
      console.log(`每页 ${val} 条`);
    },
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`);
    },
  },
};
</script>

<style></style>
