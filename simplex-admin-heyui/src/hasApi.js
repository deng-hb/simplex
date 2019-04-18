// 是否有这个API，没有的隐藏
export const hasApi = {
  install (Vue) {
    Vue.directive('hasApi', {
      bind (el, binding, vnode) {
        console.log('%o,%o,%o',el,binding,vnode);
        let api = binding.value;
        let apis = window.localStorage.getItem('APIs');
        let flag = false;
        if (null != apis) {
          flag = apis.split(',').indexOf(api) > -1
        }
        if (!flag) {
          if (!el.parentNode) {
            el.style.display = 'none'
          } else {
            el.parentNode.removeChild(el)
          }
        }
      }
    })
  }
}