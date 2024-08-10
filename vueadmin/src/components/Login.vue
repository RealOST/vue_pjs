<template>
  <div class="login-container">
    <!-- 登录盒子  -->
    <div class="login-box">
      <p>vue后台管理系统</p>
      <!-- 登录表单 -->
      <el-form class="login-form" label-width="0px" :model="loginForm">
        <!-- 用户名 -->
        <el-form-item>
          <el-input
            prefix-icon="iconfont icon-user"
            v-model="loginForm.username"
          ></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item>
          <el-input
            prefix-icon="iconfont icon-3702mima"
            v-model="loginForm.password"
          ></el-input>
        </el-form-item>
        <el-form-item class="link">
          <el-link href="/#/index" type="primary">游客登录</el-link>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary" @click="login">登录</el-button>
          <el-button type="info" @click="reset">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      // 数据绑定
      loginForm: {
        username: "",
        password: "",
      },
    };
  },
  // 添加行为，
  methods: {
    // 添加表单重置方法
    resetLoginForm() {
      // this=>当前组件对象，其中的属性$refs包含了设置的表单ref
      //   console.log(this)
      this.$refs.LoginFormRef.resetFields();
    },
    reset() {
      this.loginForm = {};
    },
    login() {
      // 发送请求进行登录
      this.$http({
        method: "post",
        url: "/api/login",
        data: this.loginForm,
      }).then((resp) => {
        console.log(resp);
        if (resp.data == "success") {
          this.$message.success("登录成功");
          // 导航至/index
          localStorage.setItem("uname", this.loginForm.username);
          this.$router.push({
            name: "Home",
          }); // location.href
        } else {
          this.$message.error("登录失败");
        }
      });
    },
  },
};
// :model="" 绑定表单数据对象 :rules="" 绑定验证规则
</script>
<style lang="less" scoped>
.login-container {
  position: relative;
  height: 100%;
  background: url(../assets/img/bg.png) no-repeat;
  background-size: cover;
}
.login-box {
  width: 450px;
  height: 300px;
  background: #fff;
  border-radius: 10px;
  position: absolute;
  left: 50%;
  top: 50%;
  transform: translate(-50%, -50%);
  p {
    height: 100%;
    width: 100%;
    font-size: 32px;
    text-align: center;
    font-weight: bold;
  }
}
.login-form {
  position: absolute;
  bottom: 0;
  width: 100%;
  padding: 0 20px;
  box-sizing: border-box;
}
.btns {
  display: flex;
  justify-content: flex-end;
}
.link {
  display: flex;
  float: left;
}
</style>
