<template>
  <div class="frame-page h-panel">
    <div class="h-panel-bar">
      <span class="h-panel-title">查询列表</span>
      <div class="h-panel-right">
        <Button v-hasApi="'POST/sys/resource/save'" color="blue" icon="h-icon-plus" @click="showAdd(0)">新建</Button>
      </div>
    </div>

    <div class="h-panel-bar">
      <Form mode="inline" :model="search" :top="0.2">
        <FormItem label="类型">
          <Select v-width="200" v-model="search.type" :datas="SysResourceConsts$Type"></Select>
        </FormItem>
        <FormItem>
          <Button color="primary" @click="doSearch" ><i class="h-icon-search"></i> 查询</Button>
        </FormItem>
        
      </Form>
    </div>

    <div class="h-panel-body">
      <Table ref="table" :datas="listTree" >
        <TableItem title="No." :width="50" ><template slot-scope="{index}">{{index + 1}}</template></TableItem>
        <TableItem title="名称" prop="title" treeOpener></TableItem>
        <TableItem title="类型">
          <template slot-scope="{data}">
            {{SysResourceConsts$Type[data.type]}}
          </template>
        </TableItem>
        <TableItem title="排序" prop="seq"></TableItem>
        <TableItem title="路径" prop="uri"></TableItem>
        <TableItem title="图标">
          <template slot-scope="{data}">
            <span v-if="data.type == 'MENU'">
              <i :class="data.icon" ></i>
            </span>
          </template>
        </TableItem>
        <TableItem title="请求方法">
          <template slot-scope="{data}">
            <span>{{data.type == 'API' ? data.method : ''}}</span>
          </template>
        </TableItem>
        <TableItem title="公开">
          <template slot-scope="{data}">
            <span v-if="data.type == 'API' && data.opened == 1" class="h-tag h-tag-green">是</span>
            <span v-if="data.type == 'API' && data.opened == 0" class="h-tag h-tag-red">否</span>
          </template>
        </TableItem>
        <TableItem title="操作" align="center">
          <template slot-scope="{data}">
            <Button v-hasApi="'POST/sys/resource/save'" @click="showAdd(data.id)" size="s" icon="h-icon-plus">新增</Button>
            <Button v-hasApi="'POST/sys/resource/save'" @click="showEdit(data)" size="s" icon="h-icon-edit">编辑</Button>
            <Button v-hasApi="'POST/sys/resource/del'" @click="showDel(data.id)" size="s" icon="h-icon-trash" text-color="red">删除</Button>
          </template>
        </TableItem>
      </Table>
    </div>

    <Modal v-model="resourceModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{'' == resourceModal.data.id?'新建':'编辑'}}资源</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="resourceModal.rules" :model="resourceModal.data" >
          <FormItem label="上级" prop="parentId">
            <TreePicker v-model="resourceModal.data.parentId" ref="treepicker" :option="resourceTree"
            useConfirm chooseMode="some" filterable></TreePicker>
          </FormItem>
          <FormItem label="名称" prop="title">
            <input type="text" v-model="resourceModal.data.title" />
          </FormItem>
          <FormItem label="路径" prop="uri">
            <input type="text" v-model="resourceModal.data.uri" />
          </FormItem>
          <FormItem label="类型" prop="type">
            <Select v-model="resourceModal.data.type" :nullOption="false" :datas="SysResourceConsts$Type"></Select>
          </FormItem>
          <FormItem v-show="resourceModal.data.type == 'MENU'" label="图标" prop="icon">
            <input type="text" v-model="resourceModal.data.icon" />
          </FormItem>
          <FormItem v-show="resourceModal.data.type == 'API'" label="请求方法" prop="method">
            <input type="text" v-model="resourceModal.data.method" />
          </FormItem>
          <FormItem v-show="resourceModal.data.type == 'API'" label="公开" prop="opened">
            <h-switch v-model="resourceModal.data.opened" trueValue="1" falseValue="0" ></h-switch>
            公开的不需要登录就能访问
          </FormItem>
          <FormItem label="排序" prop="seq">
            <input type="number" v-model="resourceModal.data.seq">
          </FormItem>
          
        </Form>
      </div>
      <div slot="footer">
        <Button @click="resourceModal.opened=false">取消</Button>
        <Button color="primary" @click="doSave">确认</Button>
      </div>
    </Modal>

  </div>
</template>
<script>
import { parseTree } from "@/utils/sys-resource"
import { SysResourceConsts$Type } from "@/consts"

export default {
  name: 'resource',
  data() {
    return {
      search: {
        type: '',
      },
      listTree: [],
      resourceTree: {
        keyName: 'id',
        parentName: 'parent',
        titleName: 'title',
        dataMode: 'list'
      },
      resourceModal: {
        opened: false,
        data: {
          title: '',
          uri:  '',
          type: '',
          opened: '',
        },
        rules: {
          required: ['title', 'uri', 'type']
        }
      },
      SysResourceConsts$Type
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData(){
      this.reloadData();
    },
    reloadData() {

      Api.post('/sys/resource/list', this.search).then(res=>{
        let listTree = parseTree(res.data);
        
        this.listTree = listTree;
        this.resourceTree.datas = listTree;
      })
    },
    doSearch() {
      this.reloadData();
    },
    showAdd(parentId) {
      this.resourceModal.data.id = ''
      this.resourceModal.data.title = ''
      this.resourceModal.data.uri = ''
      this.resourceModal.data.type = ''
      this.resourceModal.data.icon = ''
      this.resourceModal.data.opened = '0'
      this.resourceModal.data.seq = '0'
      this.resourceModal.data.method = '';
      this.resourceModal.data.parentId = parentId;
      this.resourceModal.opened = true;
    },
    showEdit(data) {
      this.resourceModal.data.id = data.id;
      this.resourceModal.data.title = data.title;
      this.resourceModal.data.uri = data.uri;
      this.resourceModal.data.type = data.type;
      this.resourceModal.data.icon = data.icon;
      this.resourceModal.data.opened = data.opened;
      this.resourceModal.data.seq = data.seq;
      this.resourceModal.data.method = data.method;
      this.resourceModal.data.parentId = data.parentId;
      this.resourceModal.opened = true;
    },
    doSave() {

      let validResult = this.$refs.form.valid();
      if (!validResult.result) {
        return;
      }
      Api.post('/sys/resource/save',this.resourceModal.data).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.resourceModal.opened = false;
          this.reloadData();
        }
      })
    },
    showDel(id) {
      this.$Confirm('确定删除？', '删除后无法恢复').then(() => {
        Api.post('/sys/resource/del', {id: id}).then(res=>{
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