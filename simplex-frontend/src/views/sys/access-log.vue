<template>
  <div class="frame-page h-panel">
    <div class="h-panel-bar">
      <span class="h-panel-title">{{$route.meta.title}}</span>
      <div class="h-panel-right">
      </div>
    </div>

    <div class="h-panel-body">
      <Table :datas="list" stripe >
        <TableItem title="No." :width="50" :tooltip="true"><template slot-scope="{index}">{{(search.page - 1) * search.pageSize + index + 1}}</template></TableItem>
        <TableItem title="链接" prop="url" ></TableItem>
        <TableItem title="方法" prop="method"></TableItem>
        <TableItem title="IP" prop="ip"></TableItem>
        <TableItem title="开始时间" prop="startTime"></TableItem>
        <TableItem title="结束时间" prop="endTime"></TableItem>
        <TableItem title="Token" prop="accessToken"></TableItem>
        <template slot="expand" slot-scope="{index, data}">
          {{data.userAgent}}
        </template>
      </Table>

      <p>
        <Pagination :cur="search.page" :size="search.pageSize" :total="total" @change="onPageChange"></Pagination>
      </p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'access-log',
  data() {
    return {
      search: {
        page: 1,
        pageSize: 20
      },
      total: 0,
      list: []
    };
  },
  created() {
    this.initData();
  },
  methods: {
    initData() {
      Api.post('/sys/access/log/list',this.search).then(res=>{
        if (1 != res.code) {
          this.$Message(res.msg);
          return;
        }
        let list = res.data.list;
        for (let i in list) {
          list[i]._expand = true;
        }
        this.list = list;
        this.total = res.data.total;
      })
    },
    onPageChange(e) {
      this.search.page = e.cur;
      this.search.pageSize = e.size;
      this.initData();
    }
  }
};
</script>