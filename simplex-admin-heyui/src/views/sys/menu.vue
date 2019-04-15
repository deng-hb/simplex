<template>
  <Row :space-x="19" :space-y="5">
    <Cell width="6" >
      <Menu class="h-menu-white" :datas="list" @click="onMenu" :option="{keyName:'path'}" ></Menu>
    </Cell>
    <Cell v-if="'' != menuId" width="7" >
      <h3>已分配</h3>
      <Table v-if="myApiList.length > 0" @select="onMyApiListSelect" ref="myApiList" :datas="myApiList" stripe checkbox>
        <TableItem title="方法" prop="method"></TableItem>
        <TableItem title="路径" prop="uri" ></TableItem>
        <TableItem title="描述" prop="description" ></TableItem>
      </Table>
      <p v-else>暂无</p>
    </Cell>
    <Cell v-if="'' != menuId" width="4" >
      <div style="text-align:center;">
        <Button :disabled="0 == apiListSelect.length" @click="doSave"> &lt; 分配 </Button>
        <Button :disabled="0 == myApiListSelect.length" @click="doDel"> 移除 > </Button>
      </div>
    </Cell>
    <Cell v-if="'' != menuId" width="7" >
      <h3>全部接口</h3>
      <Table v-show="apiList.length > 0" @select="onApiListSelect" ref="apiList" :datas="apiList" stripe checkbox>
        <TableItem title="方法" prop="method"></TableItem>
        <TableItem title="路径" prop="uri" ></TableItem>
        <TableItem title="描述" prop="description" ></TableItem>
      </Table>
    </Cell>
  </Row>
  </div>
  
</template>

<script>

// import req from '@/req'

export default {
  name: 'about',
  data() {
    return {
      menuId: '',
      list: [],
      myApiList: [],
      myApiListSelect: [],
      apiList: [],
      apiListSelect: []
    }
  },
  created() {
    this.initData();
  },
  methods: {
    initData(){
      req.get('/sys/menu/list').then(res=>{
        this.list = res.data;
      })
      req.get('/sys/api/list').then(res=>{
        let data = res.data;
        let list = [];
        for (let i in data) {
          if (0 === data[i].opened) {
            list.push(data[i]);
          }
        }
        this.apiList = list;
      })
    },
    onMenu(data) {
      console.log(data);
      if (0 == data.children.length) {
        this.menuId = data.value.id;
        this.loadMyApiList();
      } else {
        this.menuId = '';
      }
    },
    loadMyApiList() {
      this.myApiListSelect = [];
      req.get('/sys/menu/api/list?menuId='+ this.menuId).then(res=>{
        this.myApiList = res.data;
      })
    },
    onMyApiListSelect (data) {
      this.myApiListSelect = data;
    },
    onApiListSelect (data) {
      this.apiListSelect = data;
    },
    doSave() {
      req.post('/sys/menu/api/save?menuId='+this.menuId,this.apiListSelect).then(res=>{
        this.$Message(res.msg);
        this.loadMyApiList();   
      })
    },
    doDel() {
      req.post('/sys/menu/api/del?menuId='+this.menuId,this.myApiListSelect).then(res=>{
        this.$Message(res.msg);
        this.loadMyApiList();
      })
    }
  },
}
</script>