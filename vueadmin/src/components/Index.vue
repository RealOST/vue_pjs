<template>
  <div>
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item to="{ path: '/index' }">首页</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="index">
      <el-row class="card-row" :gutter="20">
        <el-col :span="6">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class="el-icon-user"></i>
              <span>访问</span>
            </div>
            <div class="card-body">
              <h1>34151</h1>
              <p>访问总量</p>
              <p style="float: right">56421</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class="el-icon-download"></i>
              <span>下载</span>
            </div>
            <div class="card-body">
              <h1>34151</h1>
              <p>下载总量</p>
              <p style="float: right">56421</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class="el-icon-wallet"></i>
              <span>收入</span>
            </div>
            <div class="card-body">
              <h1>34151</h1>
              <p>总用户</p>
              <p style="float: right">56421</p>
            </div>
          </el-card>
        </el-col>
        <el-col :span="6">
          <el-card class="box-card">
            <div slot="header" class="clearfix">
              <i class="el-icon-coordinate"></i>
              <span>用户</span>
            </div>
            <div class="card-body">
              <h1>34151</h1>
              <p>总收入</p>
              <p style="float: right">56421</p>
            </div>
          </el-card>
        </el-col>
      </el-row>

      <el-row class="card-row" :gutter="20">
        <el-col :span="3">
          <el-link :underline="false" href="/#/login">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-eleme"></i>
              <div>登录系统</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/index">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-s-home"></i>
              <div>首页</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/member">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-user-solid"></i>
              <div>会员管理</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/baseconfig">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-s-data"></i>
              <div>基本配置</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/user">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-user"></i>
              <div>用户管理</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/role">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-s-check"></i>
              <div>角色管理</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/book">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-notebook-1"></i>
              <div>图书列表</div>
            </el-card>
          </el-link>
        </el-col>
        <el-col :span="3">
          <el-link :underline="false" href="/#/brand">
            <el-card class="box-card-lite" shadow="hover">
              <i class="el-icon-menu"></i>
              <div>品牌列表</div>
            </el-card>
          </el-link>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
export default {};
</script>

<style scoped>
.index {
  margin: 0px 10px 10px;
  min-height: 650px;
}
.card-row {
  margin-left: -7.5px;
  margin-right: -7.5px;
}
.box-card {
  width: 270px;
  height: 150px;
}
.box-card-lite {
  width: 123px;
  height: 100px;
}
.el-card {
  margin-top: 15px;
}
.el-link i {
  display: block;
  font-size: 32px;
  margin-bottom: 15px;
}
span {
  margin-left: 5px;
}
.clearfix:before,
.clearfix:after {
  display: table;
  content: "";
}
.clearfix:after {
  clear: both;
}
.card-body h1 {
  font-size: 150%;
  color: rgb(144, 147, 153);
  margin: 0px;
  padding: 0px;
}
.card-body p {
  float: left;
  color: rgb(144, 147, 153);
}
</style>
