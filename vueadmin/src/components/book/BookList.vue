<template>
  <div id="book">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>图书管理</el-breadcrumb-item>
      <el-breadcrumb-item>图书列表</el-breadcrumb-item>
    </el-breadcrumb>
    <el-card class="card-container">
      <el-form :inline="true" :model="book" class="demo-form-inline">
        <el-form-item label="编号">
          <el-input
            v-model="book.bookID"
            placeholder="请输入编号"
            clearable
            @clear="getBookList"
          ></el-input>
        </el-form-item>
        <el-form-item label="书籍名称">
          <el-input
            v-model="book.bookName"
            placeholder="请输入书籍名称"
            clearable
            @clear="getBookList"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
      </el-form>
      <el-button type="danger" plain @click="delBatch">批量删除</el-button>
      <el-button type="success" @click="addBook" plain>新增</el-button>
      <el-table :data="bookData" style="width: 100%" @select="checkSelect">
        <el-table-column type="selection" width="55"> </el-table-column>
        <el-table-column align="center" prop="bookID" label="编号">
        </el-table-column>

        <el-table-column align="center" prop="bookName" label="书籍名称">
        </el-table-column>
        <el-table-column align="center" prop="bookCounts" label="数量">
        </el-table-column>
        <el-table-column align="center" prop="detail" label="详情">
        </el-table-column>

        <el-table-column align="center" prop="operation" label="操作">
          <template slot-scope="scope">
            <el-button
              type="primary"
              size="small"
              @click="updateBook(scope.row)"
              >修改</el-button
            >
            <el-button type="danger" size="small" @click="delBook(scope.row)"
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
        <el-form :model="bookForm" label-width="100px" class="demo-ruleForm">
          <el-form-item label="书籍名称">
            <el-input v-model="bookForm.bookName"></el-input>
          </el-form-item>
          <el-form-item label="书籍数量">
            <el-input v-model="bookForm.bookCounts"></el-input>
          </el-form-item>
          <el-form-item label="描述">
            <el-input type="textarea" v-model="bookForm.detail"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('bookForm')">{{
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
    this.getBookList();
  },
  data() {
    return {
      dialogMode: "add",
      dialogTitle: "添加图书",
      multipleSelection: [],
      selectedIds: [],
      totalCount: 400,
      currentPage: 1,
      pageSize: 5,
      dialogVisible: false,
      bookData: [],
      book: {
        bookID: "",
        bookName: "",
      },
      bookForm: {
        bookID: "",
        bookName: "",
        bookCounts: "",
        detail: "",
      },
    };
  },
  methods: {
    getBookList() {
      // console.log(this.bookData);
      this.$http({
        method: "post",
        url: `/api/selByPACBook?currentPage=${this.currentPage}&pageSize=${this.pageSize}`,
        data: this.book,
      }).then((res) => {
        this.bookData = res.data.rows;
        this.totalCount = res.data.totalCount;
      });
    },
    //每页显示条数发生变化
    handleSizeChange(val) {
      // console.log("当前显示条数", val);
      this.pageSize = val;
      this.getBookList();
    },
    //当前页发生变化
    handleCurrentChange(val) {
      // console.log("当前页", val);
      this.currentPage = val;
      this.getBookList();
    },

    //点击查询按钮
    onSubmit() {
      // console.log("当前查询的品牌", this.book);
      this.getBookList();
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
      console.log(this.multipleSelection[0].bookID);
      console.log(this.multipleSelection);
    },
    //修改
    updateBook(row) {
      this.dialogMode = "edit";
      this.dialogTitle = "编辑图书";
      //弹出编辑窗口
      this.dialogVisible = true;
      this.$http({
        method: "get",
        url: `/api/selBook?id=${row.bookID}`,
      }).then((resp) => {
        // console.log("获取修改项",resp.data);
        this.bookForm = resp.data;
      });
    },

    addBook() {
      this.dialogMode = "add";
      this.dialogTitle = "添加图书";
      this.dialogVisible = true;
      this.bookForm = {};
    },

    submitForm() {
      if (this.dialogMode === "add") {
        this.$http({
          method: "post",
          url: "/api/addBook",
          data: this.bookForm,
        }).then((resp) => {
          if (resp.data == "success") {
            this.$message({
              message: "添加成功",
              type: "success",
            });
            this.getBookList();
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
          url: "/api/updateBook",
          data: this.bookForm,
        }).then((resp) => {
          if (resp.data == "success") {
            this.getBookList();
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

    delBook(row) {
      // console.log(`选中的删除项：${row.id}`);
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          this.$http({
            method: "get",
            url: `/api/delBook?id=${row.bookID}`,
          }).then((resp) => {
            if (resp.data == "success") {
              this.getBookList();
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
      console.log("multipleSelection:", this.multipleSelection);
      this.$confirm("此操作将删除该数据, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      })
        .then(() => {
          //用户点击确认按钮
          for (let i = 0; i < this.multipleSelection.length; i++) {
            let selectionElement = this.multipleSelection[i];
            this.selectedIds[i] = selectionElement.bookID;
            console.log(`勾选的id`, this.selectedIds[i]);
          }

          console.log(`勾选的数组`, this.selectedIds);
          // 发送ajax请求，添加数据
          this.$http({
            method: "post",
            url: "/api/delBooks",
            data: this.selectedIds,
          }).then((resp) => {
            if (resp.data == "success") {
              //删除成功
              // 重新查询数据
              this.getBookList();
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
