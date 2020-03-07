<template>
  <div id="app">
    <Layout class="app-frame" v-if="signed" :siderCollapsed="siderCollapsed" :siderFixed="layoutConfig.siderFixed">
      <Sider :theme="layoutConfig.siderTheme">
        <MyMenu :theme="layoutConfig.siderTheme"></MyMenu>
      </Sider>
      <Layout :headerFixed="layoutConfig.headerFixed">
        <HHeader theme="white">
          <MyHeader @openSetting="openSetting=true" :layoutConfig="layoutConfig"></MyHeader>
        </HHeader>
        <MyTabs homePage="home"></MyTabs>
        <Content>
          <div class="app-frame-content">
            <keep-alive :include="cachePage">
              <router-view/>
            </keep-alive>
          </div>
          <HFooter>
            <MyFooter></MyFooter>
          </HFooter>
        </Content>
      </Layout>
    </Layout>
    <div v-else>
      <!-- 登录页 -->
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
            <img :src="captcha" v-tooltip content="点击刷新" class="captcha" @click="reloadCaptcha" />
          </div>
          <div class="buttonDiv">
            <Button :loading="loading" block color="primary" size="l" @click="doSignIn">登录</Button>
            su/P2V49fAp
          </div>
        </div>
      </div>

    </div>

    <Modal v-model="openSetting" type="drawer-right">
      <MySettings :layoutConfig="layoutConfig"></MySettings>
    </Modal>
  </div>
</template>


<script>
import MyHeader from '@/components/header';
import MyTabs from '@/components/tabs'
import MyMenu from '@/components/menu';
import MyFooter from '@/components/footer';
import MySettings from '@/components/settings';

const MD5 = require('md5.js');

export default {
  components: {
    MyHeader,
    MyTabs,
    MyMenu,
    MyFooter,
    MySettings
  },
  data() {
    return {
      signIn: {
        username: '',
        password: '',
        code: '',
        key: ''
      },
      openSetting: false,
      layoutConfig: {
        siderTheme: 'white',
        showSystab: false,
        headerFixed: true,
        siderFixed: true
      },
      loading: false,
      captcha: '',
      datas: [
        { icon: 'h-icon-home' },
        { title: 'Component', icon: 'h-icon-complete', route: { name: 'Component' } },
        { title: 'Breadcrumb', icon: 'h-icon-star' }
      ]
    };
  },
  computed: {
    signed() {
      return this.$store.state.signed;
    },
    cachePage() {
      return this.$store.state.cachePage;
    },
    siderCollapsed() {
      return this.$store.state.siderCollapsed;
    },
  },
  watch: {
  },
  methods: {
    doSignOut() {
      this.$Confirm('确认要退出登录？', '提示').then(() => {
        window.localStorage.removeItem('token');
        window.localStorage.removeItem('APIs')
        window.localStorage.removeItem('SYS_TABS')
        this.$store.commit("setSigned", false);
        this.reloadCaptcha();
      }).catch(() => {
        // this.$Message.error('取消');
      });
      
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
      
      let data = {
        username: username,
        password: password2,
        code: code,
        key: this.signIn.key
      };

      Api.post('/sys/user/signIn', data).then(res=>{
        if (1 != res.code) {
          this.reloadCaptcha();
          this.$Message.error(res.msg);
          return;
        }
        this.signIn.password = '';
        window.localStorage.setItem('token', res.data.token);
        this.$store.commit("setSigned", true);
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
  },
  created() {
    if (this.signed) {
      Api.get('/sys/user/api').then(res=>{
        window.localStorage.setItem('APIs', res.data);
      })
    } else {
      this.reloadCaptcha();
    }
  },
};
</script>


<style lang="less">
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