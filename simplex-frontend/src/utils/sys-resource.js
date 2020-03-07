/**
 * 
 * @param {*} list 
 */
let parseTree = function(list) {
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

  let listTree = [];
  for (let i in list) {
    let item = list[i];
    if (0 == item.parentId) {
      findChildren(item, list);
      listTree.push(showTitle(item));
    }
  }
  return listTree;
}

export {
  parseTree
};