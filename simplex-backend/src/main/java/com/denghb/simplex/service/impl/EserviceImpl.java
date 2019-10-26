package com.denghb.simplex.service.impl;


import com.denghb.eorm.Eorm;
import com.denghb.eorm.EormException;
import com.denghb.eorm.support.EormSupport;
import com.denghb.eorm.support.EormTraceSupport;
import com.denghb.eorm.support.model.Table;
import com.denghb.eorm.utils.ReflectUtils;
import com.denghb.simplex.base.BizException;
import com.denghb.simplex.holder.Credential;
import com.denghb.simplex.holder.CredentialContextHolder;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.service.Eservice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.text.MessageFormat;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author denghb 2019-06-24 23:59
 */
@Slf4j
public class EserviceImpl<T> extends EPageServiceImpl implements Eservice<T> {

    @Autowired
    public Eorm db;

    // 固定SQL缓存
    private final static Map<String, String> SQL_CACHE = new ConcurrentHashMap<String, String>();

    private Class<T> getDomainClass() {
        // get domain class
        Type genType = getClass().getGenericSuperclass();
        if (genType instanceof ParameterizedType) {
            Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
            return (Class<T>) params[0];
        }
        throw new RuntimeException("Type error");
    }

    private String getOperator() {
        Credential credential = CredentialContextHolder.get();
        return credential.getUsername();
    }

    @Override
    public void doAdd(Object domain) {
        EormTraceSupport.start();
        Object o = BeanUtils.instantiate(getDomainClass());
        BeanUtils.copyProperties(domain, o);
        // 设置操作人
        ReflectUtils.setValue(o, "operator", getOperator());
        db.insert(o);
    }

    @Override
    public void doEdit(Object domain) {
        EormTraceSupport.start();
        Object o = BeanUtils.instantiate(getDomainClass());
        BeanUtils.copyProperties(domain, o);
        // 设置操作人
        ReflectUtils.setValue(o, "operator", getOperator());
        db.update(o);
    }

    @Override
    public void doDel(int id) {
        EormTraceSupport.start();
        Table table = EormSupport.load(getDomainClass());
        String sql = MessageFormat.format("update {0} set deleted = 1,operator = ? where id = ? and deleted = 0", table.getName());
        int res = db.execute(sql, getOperator(), id);
        if (1 != res) {
            throw new BizException();
        }
    }

    @Override
    public T query(int id) {
        EormTraceSupport.start();
        Table table = EormSupport.load(getDomainClass());
        String sql = MessageFormat.format("select * from {0} where deleted = 0 ", table.getName());
        return db.selectOne(getDomainClass(), sql, id);
    }


}
