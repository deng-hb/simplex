// 是否有这个API，没有的隐藏
export default {
  install (Vue) {
    Vue.directive('hasApi', {
      bind (el, binding, vnode) {
        let apis = window.localStorage.getItem('APIs');
        if (null == apis) {
          return;
        }
        let api = binding.value;
        let flag = apis.split(',').indexOf(api) > -1
        if (flag) {
          el.style.display = 'none'
        }
      }
    })
  }
}