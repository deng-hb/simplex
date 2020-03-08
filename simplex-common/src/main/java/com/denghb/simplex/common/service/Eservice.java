package com.denghb.simplex.common.service;

import com.denghb.simplex.common.model.PageReq;
import com.denghb.simplex.common.model.PageRes;

/**
 * @author denghb
 * @since 2019-05-30 23:50
 */
public interface Eservice<T> {

    void doAdd(Object domain);

    void doEdit(Object domain);

    void doDel(int id);

    T query(int id);

    <R> PageRes<R> selectPage(Class<R> clazz, String sql, PageReq req);
}
