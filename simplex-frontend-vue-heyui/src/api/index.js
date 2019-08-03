import axios from 'axios';
import heyui from 'heyui';
import router from '@/router'

axios.defaults.timeout = 60 * 1000;// 1分钟
axios.defaults.baseURL = '';


//http request 拦截器
axios.interceptors.request.use(
  config => {
    let token = window.localStorage.getItem('token');
    // console.log(config);
    config.headers['X-Access-Token'] = token;
    return config;
  },
  error => {
    return Promise.reject(error);
  }
);


//http response 拦截器
axios.interceptors.response.use(
  response => {
    // console.log(response);
    let res = response.data;
    if (res.code > 2) {
      heyui.$Message.error(res.msg);
      window.localStorage.removeItem('token');
      router.push('/')
    }
    return response;
  },
  error => {
    return Promise.reject(error)
  }
)

export default {
  get(url,params={}) {
    return new Promise((resolve,reject) => {
      axios.get(url,{
        params:params
      })
      .then(response => {
        resolve(response.data);
      })
      .catch(err => {
        reject(err)
      })
    })
  },
  post(url,data = {}){
    return new Promise((resolve,reject) => {
      axios.post(url,data)
          .then(response => {
            resolve(response.data);
          },err => {
            reject(err)
          })
    })
  }
} 
