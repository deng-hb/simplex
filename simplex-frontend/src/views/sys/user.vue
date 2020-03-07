<template>
  <div>
    <p><Button v-hasApi="'POST/sys/user/save'" color="blue" icon="h-icon-plus" @click="showAdd()">新建</Button></p>

    <Table :datas="list" stripe checkbox>
      <TableItem title="No." :width="50" :tooltip="true"><template slot-scope="{index}">{{(search.page - 1) * search.pageSize + index + 1}}</template></TableItem>
      <TableItem title="姓名" prop="name" ></TableItem>
      <TableItem title="用户名" prop="username"></TableItem>
      <TableItem title="邮箱" prop="email"></TableItem>
      <TableItem title="生日" prop="birthday"></TableItem>
      <TableItem title="操作" align="center">
        <template slot-scope="{data}">
          <Button size="s" v-hasApi="'POST/sys/user/save'" @click="showEdit(data)">编辑</Button>
          <Button size="s" v-hasApi="'POST/sys/user/del'" @click="showDel(data.id)">删除</Button>
          <Button size="s" v-hasApi="'POST/sys/user/unlockSignError'" v-if="data.status == 2" @click="showUnlockSignError(data.id)">解锁</Button>
          <Button size="s" v-hasApi="'POST/sys/user/enabled'" v-if="data.status == 3" @click="showEnabled(data.id)">启用</Button>
          <Button size="s" v-hasApi="'POST/sys/user/disabled'" v-if="data.status != 3" @click="showDisabled(data.id)">禁用</Button>
        </template>
      </TableItem>
    </Table>

    <p>
      <Pagination :cur="search.page" :size="search.pageSize" :total="total" @change="onPageChange"></Pagination>
    </p>

    <Modal v-model="userModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{null == userModal.data.id?'编辑':'新建'}}用户</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="userModal.rules" :model="userModal.data" >
          <FormItem label="姓名" prop="name">
            <input type="text" v-model="userModal.data.name">
          </FormItem>
          <FormItem label="用户名" prop="username">
            <input :disabled="null != userModal.data.id" type="text" v-model="userModal.data.username">
          </FormItem>
          <FormItem label="邮箱" prop="email">
            <input type="text" v-model="userModal.data.email">
          </FormItem>
          <FormItem label="生日" prop="birthday">
            <DatePicker v-model="userModal.data.birthday"></DatePicker>
          </FormItem>
          <FormItem label="角色" prop="sysRoleId">
            <Select v-model="userModal.data.sysRoleId" keyName="id" titleName="name" :datas="sysRoles" ></Select>
          </FormItem>
          
        </Form>
      </div>
      <div slot="footer">
        <button class="h-btn" @click="userModal.opened=false">取消</button>
        <button class="h-btn " @click="doSave">确认</button>
      </div>
    </Modal>

  </div>
</template>

<script>
export default {
  name: 'user',
  data() {
    return {
      search: {
        page: 1,
        pageSize: 20
      },
      total: 0,
      list: [],
      userModal: {
        opened: false,
        data: {
          name: '',
          username: '',
          email: '',
          birthday: '',
          sysRoleId: ''
        },
        rules: {
          required: ['name', 'username', 'email', 'birthday', 'sysRoleId']
        }
      },
      sysRoles: []
    };
  },
  created() {
    this.initData();

    Api.get('/sys/role/list').then(res=>{
      this.sysRoles = res.data;
    })
  },
  methods: {
    initData() {
      Api.post('/sys/user/list',this.search).then(res=>{
        if (1 != res.code) {
          this.$Message(res.msg);
          return;
        }
        this.list = res.data.list;
        this.total = res.data.total;
      })
    },
    showAdd() {
      this.userModal.data.id = null;
      this.userModal.data.name = '';
      this.userModal.data.username = '';
      this.userModal.data.email = '';
      this.userModal.data.birthday = '';
      this.userModal.data.sysRoleId = '';
      this.userModal.opened = true;
    },
    showEdit(item) {
      this.userModal.data.id = item.id;
      this.userModal.data.name = item.name;
      this.userModal.data.username = item.username;
      this.userModal.data.email = item.email;
      this.userModal.data.birthday = item.birthday;
      this.userModal.data.sysRoleId = item.sysRoleId;
      this.userModal.opened = true;
    },
    doSave() {
      let validResult = this.$refs.form.valid();
      if (!validResult.result) {
        return;
      }
      Api.post('/sys/user/save',this.userModal.data).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.initData();
          this.userModal.opened = false;
        }
      });
    },
    onPageChange(e) {
      this.search.page = e.cur;
      this.search.pageSize = e.size;
      this.initData();
    },
    showDel(id) {
      this.$Confirm('确定删除？', '删除后无法恢复').then(() => {
        Api.post('/sys/user/del', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.initData();
          }
        })
      }).catch(() => {
      });
    },
    showDisabled(id) {
      this.$Confirm('确定禁用用户？', '').then(() => {
        Api.post('/sys/user/disabled', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.initData();
          }
        })
      }).catch(() => {
      });
    },
    showEnabled(id) {
      this.$Confirm('确定启用用户？', '').then(() => {
        Api.post('/sys/user/enabled', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.initData();
          }
        })
      }).catch(() => {
      });
    },
    showUnlockSignError(id) {
      this.$Confirm('确定解锁用户？', '').then(() => {
        Api.post('/sys/user/unlockSignError', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.initData();
          }
        })
      }).catch(() => {
      });
    }
  }
};
</script>