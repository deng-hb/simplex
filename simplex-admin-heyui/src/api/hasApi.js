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
        let hasApi = apis.split(',').indexOf(api) > -1
        if (!hasApi) {
          el.style.display = 'none'
        }
      }
    })
  }
}