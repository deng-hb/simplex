<template>
  <div>
    <p><Button color="blue" icon="h-icon-plus" @click="showAdd()">新建</Button></p>


    <Table :datas="list" stripe checkbox>
      <TableItem title="No." :tooltip="true"><template slot-scope="{index}">{{(search.page - 1) * search.pageSize + index + 1}}</template></TableItem>
      <TableItem title="姓名" prop="name" sort="auto"></TableItem>
      <TableItem title="用户名" prop="username"></TableItem>
      <TableItem title="操作" align="center">
        <template slot-scope="{data}">
          <Button @click="showEdit(data)">编辑</Button>
          <Button @click="showDel(data.id)">删除</Button>
          <Button @click="showRole(data)">设置角色</Button>
        </template>
      </TableItem>
    </Table>

    <Pagination  :cur="search.page" :total="total" @change="onPageChange"></Pagination>

    <Modal v-model="userModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{'' == userModal.data.id?'编辑':'新建'}}用户</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="userModal.rules" :model="userModal.data" >
          <FormItem label="名称" prop="name">
            <input type="text" v-model="userModal.data.name">
          </FormItem>
          <FormItem label="描述" prop="description">
            <input type="text" v-model="userModal.data.description">
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
  data() {
    return {
      search: {
        page: 1,
        pageSize: 10
      },
      total: 0,
      list: [],
      userModal: {
        opened: false,
        data: {
          name:'',
          description:''
        },
        rules: {
          required: ['name', 'description']
        }
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      req.post('/sys/user/list',this.search).then(res=>{
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
      this.userModal.data.description = '';
      this.userModal.opened = true;
    },
    showEdit(item) {
      this.userModal.data.id = item.id;
      this.userModal.data.name = item.name;
      this.userModal.data.description = item.description;
      this.userModal.opened = true;
    },
    doSave() {
      req.post('/sys/user/save',this.userModal.data).then(res=>{
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
        req.post('/sys/user/del', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.$refs.myTree.refresh();
          }
        })
      }).catch(() => {
      });
    },
    showRole(id) {

    }
  }
};
</script>