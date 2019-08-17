package com.denghb.simplex.service;

import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;

/**
 * @author denghb
 * @since 2019-05-30 23:50
 */
public interface Eservice<T> {

    void doAdd(Object domain);

    void doEdit(Object domain);

    void doDel(int id);

    T query(int id);

    PageRes selectPage(Class clazz, String sql, PageReq req);
}
