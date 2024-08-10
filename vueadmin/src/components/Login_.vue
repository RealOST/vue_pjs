<template>
  <div class="login-container">
    <!-- 登录盒子  -->
    <div class="login-box">
      <p>快吧商城后台系统</p>
      <!-- 登录表单 -->
      <el-form class="login-form" label-width="0px" rules="loginFormRules">
        <!-- 用户名 -->
        <el-form-item>
          <el-input prefix-icon="iconfont icon-user"></el-input>
        </el-form-item>
        <!-- 密码 -->
        <el-form-item>
          <el-input prefix-icon="iconfont icon-3702mima"></el-input>
        </el-form-item>
        <el-form-item class="link">
          <el-link href="/#/index">游客登录</el-link>
        </el-form-item>
        <!-- 按钮 -->
        <el-form-item class="btns">
          <el-button type="primary">登录</el-button>
          <el-button type="info">重置</el-button>
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
        username: "admin",
        password: "123456",
      },
      // 表单验证规则
      loginFormRules: {
        username: [
          { required: true, message: "请输入登录名", trigger: "blur" },
          {
            min: 3,
            max: 10,
            message: "登录名长度在 3 到 10 个字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "请输入密码", trigger: "blur" },
          {
            min: 6,
            max: 15,
            message: "密码长度在 6 到 15 个字符",
            trigger: "blur",
          },
        ],
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
    login() {
      // 点击登录的时候先调用validate方法验证表单内容是否有误
      this.$refs.loginFormRef.validate(async (valid) => {
        console.log(this.loginFormRules);
        // 如果valid参数为true则验证通过
        if (!valid) {
          return;
        }
        // 发送请求进行登录
        const { data: res } = await this.$http.post("login", this.loginForm);
        //
        console.log(res);
        if (res.meta.status !== 200) {
          return this.$message.error("登录失败:" + res.meta.msg);
          // console.log("登录失败:"+res.meta.msg)
        }
        this.$message.success("登录成功");
        console.log(res);
        // 保存token
        window.sessionStorage.setItem("token", res.data.token);
        // 导航至/home
        this.$router.push("/home"); // location.href
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
