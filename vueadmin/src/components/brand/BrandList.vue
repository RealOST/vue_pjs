<template>
  <div id="brand">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>品牌管理</el-breadcrumb-item>
      <el-breadcrumb-item>品牌列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="card-container">
      <el-form :inline="true" :model="brand" class="demo-form-inline">
        <el-form-item label="当前状态">
          <el-select
            v-model="brand.status"
            placeholder="当前状态"
            clearable
            @clear="getBrandList"
          >
            <el-option label="启用" value="1"></el-option>
            <el-option label="禁用" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="企业名称">
          <el-input
            v-model="brand.companyName"
            placeholder="企业名称"
            clearable
            @clear="getBrandList"
          ></el-input>
        </el-form-item>
        <el-form-item label="品牌名称">
          <el-input
            v-model="brand.brandName"
            placeholder="品牌名称"
            clearable
            @clear="getBrandList"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
      <el-button type="success" @click="addBrand" plain>新增</el-button>
      <el-table :data="brandData" style="width: 100%" @select="checkSelect">
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column type="index" width="50"> </el-table-column>
        <el-table-column align="center" prop="brandName" label="品牌名称">
        </el-table-column>

        <el-table-column align="center" prop="companyName" label="企业名称">
        </el-table-column>
        <el-table-column align="center" prop="ordered" label="排序">
        </el-table-column>
        <el-table-column align="center" prop="statusStr" label="当前状态">
          <template slot-scope="scope">
            <el-switch v-model="scope.row.statusBool"></el-switch>
          </template>
        </el-table-column>

        <el-table-column align="center" prop="operation" label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="small"
              @click="updateBrand(scope.row)"
              >修改</el-button
            >
            <el-button type="danger" size="small" @click="delBrand(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <!--分页工具条-->
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="totalCount"
      >
      </el-pagination>

      <!--添加/修改表单-->
      <el-dialog
        :title="dialogTitle"
        :visible.sync="dialogVisible"
        width="30%"
        :before-close="handleClose"
      >
        <el-form
          :model="brandForm"
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="品牌名称" prop="brandName">
            <el-input v-model="brandForm.brandName"></el-input>
          </el-form-item>
          <el-form-item label="企业名称" prop="companyName">
            <el-input v-model="brandForm.companyName"></el-input>
          </el-form-item>
          <el-form-item label="排序">
            <el-input v-model="brandForm.ordered"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input
              type="textarea"
              v-model="brandForm.description"
            ></el-input>
          </el-form-item>
          <el-form-item label="状态">
            <el-switch
              :active-value="1"
              :inactive-value="0"
              v-model="brandForm.status"
            >
            </el-switch>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('brandForm')">{{
              dialogMode === "add" ? "添加" : "修改"
            }}</el-button>
            <el-button @click="dialogVisible = false">取消</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </el-card>
  </div>
</template>

<script>
export default {
  mounted() {
    this.getBrandList();
  },
  data() {
    return {
      dialogMode: "add",
      dialogTitle: "添加品牌",
      multipleSelection: [],
      selectedIds: [],
      totalCount: 400,
      currentPage: 1,
      pageSize: 5,
      dialogVisible: false,
      brandData: [],
      brand: {
        companyName: "",
        brandName: "",
        status: "",
      },
      brandForm: {
        id: "",
        brandName: "",
        companyName: "",
        ordered: "",
        status: "",
        description: "",
      },
      rules: {
        brandName: [
          { required: true, message: "请输入品牌名称", trigger: "blur" },
          {
            min: 2,
            max: 10,
            message: "长度在 2 到 10 个字符",
            trigger: "blur",
          },
        ],
        companyName: [
          { required: true, message: "请输入企业名称", trigger: "blur" },
        ],
      },
    };
  },
  methods: {
    getBrandList() {
      // console.log(this.brandData);
      this.$http({
        method: "post",
        url: `/api/selectByPACBrand?currentPage=${this.currentPage}&pageSize=${this.pageSize}`,
        data: this.brand,
      }).then((res) => {
        this.brandData = res.data.rows;
        this.totalCount = res.data.totalCount;
      });
    },
    //每页显示条数发生变化
    handleSizeChange(val) {
      // console.log("当前显示条数", val);
      this.pageSize = val;
      this.getBrandList();
    },
    //当前页发生变化
    handleCurrentChange(val) {
      // console.log("当前页", val);
      this.currentPage = val;
      this.getBrandList();
    },

    //点击查询按钮
    onSubmit() {
      // console.log("当前查询的品牌", this.brand);
      this.getBrandList();
    },
    handleClose() {
      this.dialogVisible = false;
    },

    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
    //选中复选框
    checkSelect(data) {
      console.log(`选中项数据：`, data);
      this.multipleSelection = data;
      console.log(this.multipleSelection[0].id);
    },
    //修改
    updateBrand(row) {
      this.dialogMode = "edit";
      this.dialogTitle = "编辑品牌";
      //弹出编辑窗口
      this.dialogVisible = true;
      this.$http({
        method: "get",
        url: `/api/selById?id=${row.id}`,
      }).then((resp) => {
        // console.log("获取修改项",resp.data);
        this.brandForm = resp.data;
      });
    },

    addBrand() {
      this.dialogMode = "add";
      this.dialogTitle = "添加品牌";
      this.dialogVisible = true;
      this.brandForm = {};
    },

    submitForm() {
      if (this.dialogMode === "add") {
        this.$http({
          method: "post",
          url: "/api/addBrand",
          data: this.brandForm,
        }).then((resp) => {
          if (resp.data == "success") {
            this.$message({
              message: "添加成功",
              type: "success",
            });
            this.getBrandList();
            this.dialogVisible = false;
          } else {
            this.$message({
              message: "添加失败",
              type: "error",
            });
          }
        });
      } else {
        this.$http({
          method: "post",
          url: "/api/updateBrand",
          data: this.brandForm,
        }).then((resp) => {
          if (resp.data == "success") {
            this.getBrandList();
            this.dialogVisible = false;
            this.$message({
              message: "恭喜你，修改成功",
              type: "success",
            });
          } else {
            this.$message({
              message: "修改失败",
              type: "error",
            });
          }
        });
      }
    },

    delBrand(row) {
      // console.log(`选中的删除项：${row.id}`);
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            method: "get",
            url: `/api/delBrand?id=${row.id}`,
          }).then((resp) => {
            if (resp.data == "success") {
              this.getBrandList();
              this.$message({
                message: "恭喜你，删除成功",
                type: "success",
              });
            } else {
              this.$message({
                message: "删除失败",
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },

    //批量删除
    delBatch() {
      //用户点击确认按钮
      //1. 创建id数组 [1,2,3], 从 this.multipleSelection 获取即可
      // for (let i = 0; i < this.multipleSelection.length; i++) {
      //   let selectionElement = this.multipleSelection[i];
      //   this.checkedBrands[i] = selectionElement.id;
      // }
      // 弹出确认提示框
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //用户点击确认按钮
          //1. 创建id数组 [1,2,3], 从 this.multipleSelection 获取即可
          for (let i = 0; i < this.multipleSelection.length; i++) {
            let selectionElement = this.multipleSelection[i];
            this.selectedIds[i] = selectionElement.id;
          }

          console.log(`勾选的数组`, this.selectedIds);
          // 发送ajax请求，添加数据
          this.$http({
            method: "post",
            url: "/api/deleteByIdsBrand",
            data: this.selectedIds,
          }).then((resp) => {
            if (resp.data == "success") {
              //删除成功
              // 重新查询数据
              this.getBrandList();
              // 弹出消息提示
              this.$message({
                type: "success",
                message: "恭喜你，删除成功",
              });
            } else {
              this.$message({
                message: "删除失败",
                type: "error",
              });
            }
          });
        })
        .catch(() => {
          //用户点击取消按钮
          this.$message({
            type: "info",
            message: "已取消删除",
          });
        });
    },
  },
};
</script>

<style></style>
