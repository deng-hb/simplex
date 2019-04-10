<template>
  <div class="about">
    <h1>This is an about page</h1>

    <Button color="primary" @click="show">Main button</Button>

    <img :src="captcha" />
  </div>
  
</template>

<script>

// import req from '@/req'

export default {
  name: 'about',
  data() {
    return {
      captcha:''
    }
  },
  methods: {
    show() {
      this.$Message('这是一个普通的提醒');
      console.log(req);
      req.get('/captcha').then(res=>{
        console.log(res)
        this.captcha = 'data:image/jpeg;base64,' + res.data.imageData;
      })
    }
  },
}
</script>