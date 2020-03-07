<template>
  <div>
    <p><Button v-hasApi="'POST/sys/role/save'" color="blue" icon="h-icon-plus" @click="showAdd()">新建</Button></p>

    <Table :datas="list" stripe checkbox>
      <TableItem title="No." :width="50" :tooltip="true"><template slot-scope="{index}">{{(search.page - 1) * search.pageSize + index + 1}}</template></TableItem>
      <TableItem title="名称" prop="name" sort="auto"></TableItem>
      <TableItem title="描述" prop="description"></TableItem>
      <TableItem title="操作" align="center">
        <template slot-scope="{data}">
          <Button size="s" v-hasApi="'POST/sys/role/save'" @click="showEdit(data)">编辑</Button>
          <Button size="s" v-hasApi="'POST/sys/role/del'" @click="showDel(data.id)">删除</Button>
        </template>
      </TableItem>
    </Table>

    <p>
      <Pagination :cur="search.page" :size="search.pageSize" :total="total" @change="onPageChange"></Pagination>
    </p>

    <Modal v-model="roleModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{null == roleModal.data.id?'编辑':'新建'}}角色</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="roleModal.rules" :model="roleModal.data" >
          <FormItem label="名称" prop="name">
            <input type="text" v-model="roleModal.data.name">
          </FormItem>
          <FormItem label="描述" prop="description">
            <input type="text" v-model="roleModal.data.description">
          </FormItem>
          <FormItem label="角色资源" prop="sysResourceIds">
            <TreePicker v-model="roleModal.data.sysResourceIds" ref="treepicker" :option="resourceTree"
             multiple chooseMode="some" :showCount="true" filterable></TreePicker>
          </FormItem>
          
        </Form>
      </div>
      <div slot="footer">
        <button class="h-btn" @click="roleModal.opened=false">取消</button>
        <button class="h-btn " @click="doSave">确认</button>
      </div>
    </Modal>

  </div>
</template>

<script>
import { parseTree } from "@/utils/sys-resource"

export default {
  name: 'role',
  data() {
    return {
      search: {
        page: 1,
        pageSize: 20
      },
      total: 0,
      list: [],
      roleModal: {
        opened: false,
        data: {
          name: '',
          description: '',
          sysResourceIds: []
        },
        rules: {
          required: ['name', 'description']
        }
      },
      resourceTree: {
        keyName: 'id',
        parentName: 'parent',
        titleName: 'title',
        dataMode: 'list'
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      
      Api.post('/sys/resource/list', {}).then(res=>{
        if (1 != res.code) {
          return;
        }
        this.resourceTree.datas = parseTree(res.data);
      })
      this.reloadData();
    },
    reloadData() {
      Api.post('/sys/role/list',this.search).then(res=>{
        if (1 != res.code) {
          this.$Message(res.msg);
          return;
        }
        this.list = res.data.list;
        this.total = res.data.total;
      })
    },
    showAdd() {
      this.roleModal.opened = true;
      this.roleModal.data.id = null;
      this.roleModal.data.name = '';
      this.roleModal.data.description = '';
      this.roleModal.data.sysResourceIds = [];
    },
    showEdit(item) {
      this.roleModal.opened = true;
      this.roleModal.data.id = item.id;
      this.roleModal.data.name = item.name;
      this.roleModal.data.description = item.description;
      this.roleModal.data.sysResourceIds = item.sysResourceIds;
    },
    doSave() {
      let validResult = this.$refs.form.valid();
      if (!validResult.result) {
        return;
      }
      Api.post('/sys/role/save', this.roleModal.data).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.reloadData();
          this.roleModal.opened = false;
        }
      });
    },
    onPageChange(e) {
      this.search.page = e.cur;
      this.search.pageSize = e.size;
      this.reloadData();
    },
    showDel(id) {
      this.$Confirm('确定删除？', '删除后无法恢复').then(() => {
        Api.post('/sys/role/del', {id: id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.reloadData();
          }
        })
      }).catch(() => {
      });
    }
  }
};
</script>