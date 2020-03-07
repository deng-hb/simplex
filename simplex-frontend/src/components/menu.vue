<style lang="less">

.app-menu{
  .h-menu{
    font-size: 14px;
    .h-menu-li-selected{
      .h-menu-show:after {
        width: 4px;
      }
    }
    > li >.h-menu-show {
      font-size: 15px;
      .h-menu-show-icon {
        font-size: 20px;
      }
      .h-menu-show-desc{
        transition: opacity 0.1s cubic-bezier(0.645, 0.045, 0.355, 1), width 0.1s cubic-bezier(0.645, 0.045, 0.355, 1);
      }
      .h-menu-show:after {
        left: 0;
      }
    }
    > li .h-menu-show:after {
      position: absolute;
      content: '';
      top: 0;
      left: 0;
      bottom: 0;
    }
  }
  .h-menu.h-menu-size-collapse > .h-menu-li > .h-menu-show {
    padding-left: 24px;
    .h-menu-show-icon {
      font-size: 20px;
    }
  }
  .h-menu.h-menu-white {
    color: rgb(49, 58, 70);
  }
}

</style>
<template>
  <div class="app-menu">
    <MyLogo></MyLogo>
    <Menu :datas="menus" :inlineCollapsed="siderCollapsed" @click="trigger" ref='menu' :className="`h-menu-${theme}`" :option="{keyName:'uri'}"></Menu>
    <div class="app-menu-mask" @click="hideMenu"></div>
  </div>
</template>
<script>

import { mapState } from 'vuex';
import MyLogo from './logo';

export default {
  props: {
    theme: String,
  },
  data() {
    return {
      menus: []
    };
  },
  watch: {
    $route() {
      this.menuSelect();
    }
  },
  mounted() {
    this.init();
  },
  computed: {
    ...mapState(['siderCollapsed'])
  },
  methods: {
    init() {
      this.$nextTick(() => {
        this.menuSelect();
      });
      this.initMenu();
    },
    menuSelect() {
      if (this.$route.name) {
        this.$refs.menu.select(this.$route.path);
      }
    },
    trigger(data) {
      if (data.children.length > 0) return;
        this.$router.push({path: data.key, meta: {title: data.title}});
    },
    hideMenu() {
      this.$store.commit('updateSiderCollapse', true);
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
          if (0 == item.parentId) {
            findChildren(item, list);
            menu.push(item);
          }
        }

        this.menus = menu;
      });
    }
  },
  components: {
    MyLogo
  }
};
</script>
