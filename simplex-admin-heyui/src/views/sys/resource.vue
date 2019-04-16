<template>
  <div>
    <p>
      <Button @click="onCreate(0)" size="xs">新增顶级资源</Button>
      <Button :disabled="null == current" @click="onCreate(1)" size="xs">新增子资源</Button>
      <Button :disabled="null == current" @click="onUpdate" size="xs">编辑</Button>
      <Button :disabled="null == current" @click="onDelete" size="xs">删除</Button>
    </p>
    <Tree :option="param" ref="myTree" @select="select" :toggleOnSelect="false"></Tree>


    <Modal v-model="resourceModal.opened" :closeOnMask="false" :hasCloseIcon="true" :hasDivider="true">
      <div slot="header">{{'' == resourceModal.data.id?'编辑':'新建'}}资源</div>
      <div >
        <Form ref="form" :labelWidth="110" :rules="resourceModal.rules" :model="resourceModal.data" >
          <FormItem v-show="null != resourceModal.data.parentId" label="上级" prop="parentId">
            <input type="text" :readonly='true' v-model="resourceModal.data.parentName">
          </FormItem>
          <FormItem label="名称" prop="title">
            <input type="text" v-model="resourceModal.data.title">
          </FormItem>
          <FormItem label="路径" prop="path">
            <input type="text" v-model="resourceModal.data.path">
          </FormItem>
          <FormItem label="类型" prop="type">
            <Select :disabled="'' != resourceModal.data.id" v-model="resourceModal.data.type" :nullOption="false" keyName="value" titleName="label" :datas="types"></Select>
          </FormItem>
          <FormItem v-show="resourceModal.data.type == 'MENU'" label="图标" prop="icon">
            <input type="text" v-model="resourceModal.data.icon">
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
        <button class="h-btn" @click="resourceModal.opened=false">取消</button>
        <button class="h-btn " @click="doSave">确认</button>
      </div>
    </Modal>

  </div>
</template>
<script>
export default {
  data() {
    return {
      current: null,
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
              item.showTitle = item.title + (1 == item.opened?'公开':'') + ' ---- ' + item.seq;
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
      },
      types: [],
      resourceModal: {
        opened: false,
        data: {
          title:'',
          path:'',
          type:'',
          opened:'',
        },
        rules: {
          required: ['title', 'path', 'type']
        }
      }
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData(){
      req.get('/sys/resource/type').then(res=>{
        this.types = res.data;
      })
    },
    select(data) {
      this.current = data;
      console.log(data);
    },
    onCreate(e) {
      this.resourceModal.data.id = ''
      this.resourceModal.data.title = ''
      this.resourceModal.data.path = ''
      this.resourceModal.data.type = ''
      this.resourceModal.data.icon = ''
      this.resourceModal.data.opened = '0'
      this.resourceModal.data.seq = '0'
      if (0 == e) {
        this.resourceModal.data.parentId = null;
        this.resourceModal.data.parentName = null;
      } else {
        this.resourceModal.data.parentId = this.current.id;
        this.resourceModal.data.parentName = this.current.title;
      }
      this.resourceModal.opened = true;
    },
    onUpdate() {
      this.resourceModal.data.id = this.current.id;
      this.resourceModal.data.title = this.current.title;
      this.resourceModal.data.path = this.current.path;
      this.resourceModal.data.type = this.current.type;
      this.resourceModal.data.icon = this.current.icon;
      this.resourceModal.data.opened = this.current.opened;
      this.resourceModal.data.seq = this.current.seq;
      this.resourceModal.data.parentId = null;
      this.resourceModal.data.parentName = null;
      this.resourceModal.opened = true;
    },
    doSave() {

      req.post('/sys/resource/save',this.resourceModal.data).then(res=>{
        this.$Message(res.msg);
        if (1 == res.code) {
          this.resourceModal.opened = false;
          this.$refs.myTree.refresh();
        }
      })
    },
    onDelete() {
      this.$Confirm('确定删除？', '删除后无法恢复').then(() => {
        req.post('/sys/resource/del', {id:this.current.id}).then(res=>{
          this.$Message(res.msg);
          if (1 == res.code) {
            this.$refs.myTree.refresh();
          }
        })
      }).catch(() => {
      });
    }
  }
};
</script>