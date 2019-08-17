<template>
  <div id="app">
    <Layout v-if="signed" :headerFixed="headerFixed">
      <HHeader theme="dark">
        <div class="layout-logo"></div>
        <router-link to="/">Home</router-link> |
        <router-link to="/about">About</router-link>
        <h-switch v-model="headerFixed">固定header</h-switch>
        <h-switch v-model="siderFixed" :disabled="!headerFixed">固定Sider</h-switch>
        <h-switch v-model="siderCollapsed">收起菜单</h-switch>
        <Button @click="doSignOut"> 退出 </Button>
        
      </HHeader>
      <Layout :siderFixed="siderFixed" :siderCollapsed="siderCollapsed">
        <Sider theme="white">
          <Menu style="margin-top: 40px;" class="h-menu-white" :datas="menu" @click="onMenu" :option="{keyName:'uri'}" :inlineCollapsed="siderCollapsed"></Menu>
        </Sider>
        <Content style="padding: 0px 30px;">
          <Breadcrumb :datas="datas" style="margin: 16px 0px;"></Breadcrumb>
          <div style="background: rgb(255, 255, 255); padding: 20px; min-height: 480px;">
            <router-view/>
          </div>
          <HFooter class="text-center">Copyright © 2019</HFooter>
        </Content>
      </Layout>
    </Layout>
    <div v-else>
      <div class="signIn-container">
        <div class="signIn-content">
          <div class="signIn-title">管理系统</div>
          <div class="signIn-name signIn-input">
            <input type="text" name="username" v-model="signIn.username" autocomplete="off"/>
            <span class="placeholder" :class="{fixed: signIn.username != '' && signIn.username != null}">用户名</span>
          </div>
          <div class="signIn-password signIn-input">
            <input type="password" name="password" v-model="signIn.password" autocomplete="off"/>
            <span class="placeholder" :class="{fixed: signIn.password != '' && signIn.password != null}">密码</span>
          </div>
          <div class="signIn-captcha signIn-input">
            <input type="text" name="code" v-model="signIn.code" @keyup.enter="doSignIn" autocomplete="off"/>
            <span class="placeholder" :class="{fixed: signIn.code != '' && signIn.code != null}">验证码</span>
            <img :src="captcha" class="captcha" @click="reloadCaptcha" />
          </div>
          <div class="buttonDiv">
            <Button :loading="loading" block color="primary" size="l" @click="doSignIn">登录</Button>
          </div>
        </div>
      </div>

    </div>
  </div>
</template>


<script>
const MD5 = require('md5.js')

export default {
  data() {
    return {
      signIn: {
        username: '',
        password: '',
        code: '',
        key: ''
      },
      loading: false,
      captcha: '',
      signed: null != window.localStorage.getItem('token'),
      headerFixed: false,
      siderFixed: false,
      siderCollapsed: false,
      menu: [],
      datas: [
        { icon: 'h-icon-home' },
        { title: 'Component', icon: 'h-icon-complete', route: { name: 'Component' } },
        { title: 'Breadcrumb', icon: 'h-icon-star' }
      ]
    };
  },
  watch: {
    headerFixed() {
      if (!this.headerFixed) {
        this.siderFixed = false;
      }
    }
  },
  methods: {
    doSignOut() {
      window.localStorage.removeItem('token')
      window.localStorage.removeItem('APIs')
      this.signed = false;
    },
    doSignIn() {
      let username = this.signIn.username;
      let password = this.signIn.password;
      let code = this.signIn.code;
      if('' == username) {
        this.$Message.error('请输入用户名');
        return;
      }
      if('' == password) {
        this.$Message.error('请输入密码');
        return;
      }
      if('' == code) {
        this.$Message.error('请输入验证码');
        return;
      }
      let password2 = new MD5().update(password).digest('hex')
      password2 = new MD5().update(password2).digest('hex')
      
      let data = {username:username,password:password2,code:code,key:this.signIn.key};

      Api.post('/sys/user/signIn', data).then(res=>{
        if (1 != res.code) {
          this.reloadCaptcha();
          this.$Message.error(res.msg);
          return;
        }
        this.signIn.password = '';
        window.localStorage.setItem('token', res.data.token);
        this.signed = true;
        this.initMenu();
      })
    },
    reloadCaptcha() {
      if (this.signed) {
        return;
      }
      Api.get('/captcha').then(res=>{
        console.log(res)
        this.captcha = res.data.imageData;
        this.signIn.key = res.data.key;
        this.signIn.code = '';
      })
    },
    onMenu(data) {
      console.log(data);
      if (data.children.length == 0)
        this.$router.push(data.key);
    },
    initMenu() {
      Api.get('/sys/user/menu').then(res=>{
        if (1 != res.code) {
          return;
        }
        let list = res.data;
        list = null == list ? [] : list;

        function findChildren(item, list) {
          item.children = [];
          for (let i in list) {
            let item2 = list[i];
            if (item.id == item2.parentId) {
              item.children.push(item2);
              findChildren(item2, list)
            }
          }
        }

        let menu = [];
        for (let i in list) {
          let item = list[i];
          if (null == item.parentId) {
            findChildren(item, list);
            menu.push(item);
          }
        }

        this.menu = menu;
      });
    }
  },
  computed: {
    
  },
  created() {
    this.reloadCaptcha();
    if (this.signed) {
      this.initMenu();
      Api.get('/sys/user/api').then(res=>{
        window.localStorage.setItem('APIs', res.data);
      })
    }
    let _this = this;
    setInterval(() => {
      _this.signed = null != window.localStorage.getItem('token')
    }, 1);
  },
};
</script>


<style lang="less">
.h-layout {
  background: #f0f2f5;
}
.layout-logo {
  width: 120px;
  height: 31px;
  background: rgba(255, 255, 255, 0.2);
  margin: 16px 24px 16px 0;
  float: left;
}

.h-layout-header {
  padding: 0 50px;
}

.h-layout-footer {
  padding: 24px 50px;
  color: rgba(0, 0, 0, 0.65);
  font-size: 14px;
}
@gradient-color: #3788ee;
@bg-color: #f7f8fa;
@title-color:#3a3a3a;
@text-color: #7e7e7e;
@placeholder-color: #7e7e7e;
.signIn-container {
  text-align: center;
  position: absolute;
  top: 0;
  bottom: 0;
  right: 0;
  left: 0;
  background: @bg-color;
  .signIn-content {
    width: 320px;
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    letter-spacing: 2px;
    background: #FFF;
    padding: 70px 30px 20px;
    box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.06);
    border-radius: 3px;
    box-sizing: border-box;
    >div {
      margin: 30px 0;
      &.signIn-input {
        position: relative;
        .placeholder {
          position: absolute;
          color: @placeholder-color;
          top: 6px;
          font-size: 16px;
          transition: all .2s;
          left: 0;
          pointer-events: none;
        }
        input {
          font-size: 16px;
          padding: 8px 0;
          height: 40px;
          width: 100%;
          border: none;
          border-radius: 0;
          border-bottom: 1px solid #d3d3d3;
          box-shadow: inset 0 0 0 1000px #fff;
          outline: none;
          box-sizing: border-box;
          transition: .3s;
          font-weight: 200;
          &:focus {
            border-bottom-color: @gradient-color;
            box-shadow: inset 0 0 0 1000px #fff;
          }
        }
        input:focus + .placeholder,input:-webkit-autofill + .placeholder, .placeholder.fixed{
          font-size: 13px;
          top: -16px;
        }
      }
      &.signIn-title {
        font-size: 30px;
        color: @title-color;
        line-height: 1;
        margin: -16px 0px 40px;
        font-weight: 200;
      }

    }
    > .buttonDiv {
      margin-top: 45px;
      .h-btn {
        padding: 12px 0;
        font-size: 18px;
        opacity: .8;
        border-radius: 3px;
        background: @gradient-color;
        border-color: @gradient-color;
        &:hover {
          opacity: 1;
          background: @gradient-color;
          border-color: @gradient-color;
        }
      }
    }
  }
  .copyright {
    letter-spacing: 1px;
    margin-top: 30px;
    color: @text-color;
    a {
      color: @text-color;
    }
  }

  .captcha {
    float: right;
    margin-top: -41px;
    position: relative;
    z-index: 10;
    background: #eee;
    width: 100px;
    height: 40px;
  }
}

</style>