<template>
  <el-container class="home-container">
    <!-- 侧边栏 -->
    <el-aside width="240px">
      <div class="navbar-box">
        <div class="logo">vueadmin后台管理系统</div>
      </div>
      <el-menu
        :default-active="activeIndex"
        class="el-menu-vertical"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        unique-opened
        router
      >
        <el-menu-item index="/index">
          <i class="el-icon-s-home"></i>
          <span slot="title">首页</span>
        </el-menu-item>

        <el-menu-item index="/member">
          <i class="el-icon-user"></i>
          <span slot="title">会员管理</span>
        </el-menu-item>

        <el-submenu index="/config">
          <template slot="title">
            <i class="el-icon-s-data"></i>
            <span>配置管理</span>
          </template>
          <el-menu-item index="baseconfig">
            <i class="el-icon-menu"></i>
            <span>基本配置</span>
          </el-menu-item>
        </el-submenu>

        <el-submenu index="/system">
          <template slot="title">
            <i class="el-icon-setting"></i>
            <span>系统管理</span>
          </template>
          <el-menu-item-group>
            <el-menu-item index="/user">
              <i class="el-icon-user"></i>
              <span>用户管理</span>
            </el-menu-item>
            <el-menu-item index="/role">
              <i class="el-icon-s-check"></i>
              <span>角色管理</span>
            </el-menu-item>
          </el-menu-item-group>
        </el-submenu>

        <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-notebook-1"></i>
            <span>图书管理</span>
          </template>

          <el-menu-item index="/book">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>图书列表</span>
            </template>
          </el-menu-item>
        </el-submenu>
        <el-submenu index="6">
          <template slot="title">
            <i class="el-icon-office-building"></i>
            <span>品牌管理</span>
          </template>

          <el-menu-item index="/brand">
            <template slot="title">
              <i class="el-icon-menu"></i>
              <span>品牌列表</span>
            </template>
          </el-menu-item>
        </el-submenu>
      </el-menu>
    </el-aside>
    <!-- 页面主体区域 -->
    <el-container>
      <!-- 头部区域 -->
      <el-header class="page-topbar">
        <div class="navbar-header">
          <div class="d-flex">
            <div class="navbar-header">
              <el-breadcrumb separator-class="el-icon-arrow-right">
                <el-breadcrumb-item
                  :to="item.path"
                  v-for="(item, index) in breadcrumbList"
                  :key="index"
                  >{{ item.name }}</el-breadcrumb-item
                >
              </el-breadcrumb>
              <span class="uname">欢迎回来，{{ uname }}</span>
            </div>
          </div>
          <div class="d-flex">
            <div class="navbar-header">
              <el-avatar
                size="middle"
                :src="require('@/assets/img/avatar.jpg')"
              ></el-avatar>
              <el-button
                type="danger"
                @click="logout()"
                icon="el-icon-switch-button"
                size="small"
              >
              </el-button>
            </div>
          </div>
        </div>
      </el-header>
      <!-- 主体结构 -->
      <el-main>
        <router-view />
      </el-main>
    </el-container>
  </el-container>
</template>
<script>
export default {
  data() {
    return {
      breadcrumbList: [],
      activeIndex: `/index`,
      uname: "",
    };
  },
  methods: {
    logout() {
      window.sessionStorage.clear();
      this.$router.push("/login");
    },
    // handleSelect(key, keyPath) {
    //   this.$router.push(key);
    // },
    generateBreadcrumb() {
      // //获取当前路由信息
      // const matched = this.$route.matched;
      // //初始化面包屑导航数据
      // const breadcrumbList = [];
      // //遍历路由信息，生成面包屑导航数据
      // matched.forEach((item) => {
      //   const { meta, name, path } = item;
      //   console.log(item);
      //   if (meta.breadcrumb) {
      //     breadcrumbList.push({
      //       name,
      //       path,
      //     });
      //   }
      // });
      // //保存面包屑数据
      // this.breadcrumbList = breadcrumbList;
    },
  },
  mounted() {
    this.uname = localStorage.getItem("uname");
    this.$router.push("/index");
    this.generateBreadcrumb();
  },
  watch: {
    $route() {
      this.generateBreadcrumb();
    },
  },
};
</script>

<style lang="less" scoped>
.home-container {
  height: 100%;
}
.el-header {
  color: #fff;
  background-color: #fff;
  .el-button {
    float: right;
  }
}
.el-aside {
  background-color: #545c64;
  padding: 10px 0 30px 0;
}
.el-main {
  background-color: #f2f6fc;
}
.navbar-box {
  border-bottom: 1px solid gray;
  padding: 0 1.5rem;
  font-size: 16px;
}
.logo {
  display: block;
  color: #fff;
  line-height: 60px;
}
.navbar-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin: 0 auto;
  height: 60px;
  padding: 0 calc(20px / 2) 0 0;
}

.el-avatar {
  margin-right: 20px;
}

.el-menu-item,
.el-submenu {
  margin-right: -1px;
}
.el-aside::-webkit-scrollbar {
  display: none;
}
.uname {
  color: black;
  font-size: 22px;
}
</style>
