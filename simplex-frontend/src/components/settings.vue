<style lang="less">
.app-layout-setting-vue {
  width: 300px;
  .h-panel-body {
    padding: 15px;
  }
  .alert-warning {
    border: 1px solid #ffe58f;
    background-color: #fffbe6;
    line-height: 1.5;
    font-size: 12px;
    color: rgba(0,0,0,.65);
    box-sizing: border-box;
    margin: 0;
    list-style: none;
    position: relative;
    padding: 10px 20px;
    border-radius: 4px;
    margin-top: 16px;
  }
  .setting-form {
    margin: 0 0 0 -15px;
    .h-form-item{
      margin-bottom: 10px;
    }
    .h-form-item-wrap {
      float: right;
    }
  }
}
</style>

<template>
  <div class="app-layout-setting-vue">
    <div class="h-modal-header">系统布局配置</div>
    <div class="h-panel">
      <div class="h-panel-body">
        <Form class="setting-form" :labelWidth="120" labelPosition="left" readonly>
          <FormItem label="Sider风格">
            <SwitchList v-model="layoutConfig.siderTheme" small :datas="{'white': '白色', 'dark': '暗色'}"></SwitchList>
          </FormItem>
          <FormItem label="固定 Header">
            <h-switch small v-model="layoutConfig.headerFixed"></h-switch>
          </FormItem>
          <FormItem label="固定侧边菜单">
            <h-switch small v-model="layoutConfig.siderFixed"></h-switch>
          </FormItem>
        </Form>
      </div>
    </div>
  </div>
</template>
<script>

export default {
  props: {
    layoutConfig: Object
  },
  data() {
    return {
    };
  },
  mounted() {
  },
  methods: {
  }
};
</script>
