<template>
  <div>
    <p><Button color="blue" icon="h-icon-plus" @click="showAdd()">新建</Button></p>


    <Table :datas="list" stripe checkbox>
      <TableItem title="No." :tooltip="true"><template slot-scope="{index}">{{(search.page - 1) * search.pageSize + index + 1}}</template></TableItem>
      <TableItem title="名称" prop="name" sort="auto"></TableItem>
      <TableItem title="描述" prop="description"></TableItem>
      <TableItem title="操作" align="center">
        <template slot-scope="{data}">
          <Button @click="showEdit(data)">编辑</Button>
          <Button @click="showDel(data.id)">删除</Button>
          <Button @click="showResource(data)">分配菜单资源</Button>
        </template>
      </TableItem>
    </Table>

    <Pagination  v-model="search" @change="onPageChange"></Pagination>

    <Modal v-model="roleModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{'' == roleModal.data.id?'编辑':'新建'}}角色</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="roleModal.rules" :model="roleModal.data" >
          <FormItem label="名称" prop="name">
            <input type="text" v-model="roleModal.data.name">
          </FormItem>
          <FormItem label="描述" prop="description">
            <input type="text" v-model="roleModal.data.description">
          </FormItem>
          
        </Form>
      </div>
      <div slot="footer">
        <button class="h-btn" @click="roleModal.opened=false">取消</button>
        <button class="h-btn " @click="doSave">确认</button>
      </div>
    </Modal>

    <Modal v-model="roleResourceModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">为[{{roleResourceModal.roleName}}]角色分配</div>
      <div >
        <Tree :option="roleResourceModal.param" ref="myTree" v-model="roleResourceModal.resourceIds" :multiple="true" choose-mode="some"></Tree>
      </div>
      <div slot="footer">
        <button class="h-btn" @click="roleResourceModal.opened=false">取消</button>
        <button class="h-btn " @click="doSetResource()">确认</button>
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
        total: 0,
        pageSize: 10
      },
      list: [],
      roleModal: {
        opened: false,
        data: {
          name:'',
          description:''
        },
        rules: {
          required: ['name', 'description']
        }
      },
      roleResourceModal: {
        opened: false,
        resourceIds: [],
        param: {
          keyName: 'id',
          parentName: 'parent',
          titleName: 'showTitle',
          dataMode: 'list',
          getTotalDatas: (resolve) => {
            req.get('/sys/resource/list').then(res=>{
              if (1 != res.code) {
                return;
              }
              let list = res.data;
              list = null == list ? [] : list;

              function showTitle(item) {
                item.treeIcon = item.icon
                item.showTitle = (1 == item.opened?'【已公开】':'') + item.title + ' ---- ' + item.seq;
                return item;
              }

              function findChildren(item, list) {
                item.children = [];
                for (let i in list) {
                  let item2 = list[i];
                  if (item.id == item2.parentId) {
                    item.children.push(showTitle(item2));
                    findChildren(item2, list)
                  }
                }
              }

              let list2 = [];
              for (let i in list) {
                let item = list[i];
                if (null == item.parentId) {
                  findChildren(item, list);
                  list2.push(showTitle(item));
                }
              }

              resolve(list2);

              this.$refs.myTree.expandAll();
            })
          }
        }
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      req.post('/sys/role/list',this.search).then(res=>{
        if (1 != res.code) {
          this.$Message(res.msg);
          return;
        }
        this.list = res.data.list;
        this.search.total = res.data.total;
      })
    },
    showAdd() {
      this.roleModal.data.id = null;
      this.roleModal.data.name = '';
      this.roleModal.data.description = '';
      this.roleModal.opened = true;
    },
    showEdit(item) {
      this.roleModal.data.id = item.id;
      this.roleModal.data.name = item.name;
      this.roleModal.data.description = item.description;
      this.roleModal.opened = true;
    },
    doSave() {
      req.post('/sys/role/save',this.roleModal.data).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.initData();
          this.roleModal.opened = false;
        }
      });
    },
    onPageChange(e) {
      console.log(e);
      this.search.page = 2;
    },
    showDel(id) {
      this.$Confirm('确定删除？', '删除后无法恢复').then(() => {
        req.post('/sys/role/del', {id:id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.$refs.myTree.refresh();
          }
        })
      }).catch(() => {
      });
    },
    showResource(item) {
      this.roleResourceModal.opened = true;
      this.roleResourceModal.roleName = item.name;
      this.roleResourceModal.roleId = item.id;
      this.roleResourceModal.resourceIds = [];

      req.get(`/sys/role/listSysResourceId?roleId=${item.id}`).then(res=>{
        this.roleResourceModal.resourceIds = res.data;
      })
    },
    doSetResource() {
      req.post(`/sys/role/setSysResourceIds?roleId=${this.roleResourceModal.roleId}`,
        this.roleResourceModal.resourceIds).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.roleResourceModal.opened = true;
        }
      })
      
    }
  }
};
</script>