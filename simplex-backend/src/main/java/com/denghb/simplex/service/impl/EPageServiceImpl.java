package com.denghb.simplex.service.impl;

import com.denghb.eorm.Eorm;
import com.denghb.eorm.EormException;
import com.denghb.eorm.support.EormTraceSupport;
import com.denghb.eorm.utils.ReflectUtils;
import com.denghb.simplex.model.PageReq;
import com.denghb.simplex.model.PageRes;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.MessageFormat;
import java.util.List;
import java.util.Map;

public class EPageServiceImpl {

    @Autowired
    public Eorm db;

    public <R> PageRes<R> selectPage(Class<R> clazz, String sql, PageReq pageReq) {
        EormTraceSupport.start();
        PageRes<R> res = new PageRes<R>();

        Map<String, Object> args = ReflectUtils.objectToMap(pageReq);

        String totalSql = MessageFormat.format("select count(*) from ({0}) temp", sql);
        int total = db.selectOne(int.class, totalSql, args);
        res.setTotal(total);
        if (0 == total) {
            return res;
        }

        sql += buildOrderBy(pageReq);

        if (0 < pageReq.getPage() && 0 < pageReq.getPageSize()) {
            args.put("pageStart", (pageReq.getPage() - 1) * pageReq.getPageSize());
            sql += " limit :pageStart, :pageSize";
        }

        List<R> list = db.select(clazz, sql, args);
        res.setList(list);
        return res;
    }

    private String buildOrderBy(PageReq pageReq) {

        //获取所有预置可排序字段
        List<String> sorts = pageReq.getSorts();
        if (null == sorts || sorts.isEmpty()) {
            return "";
        }

        // asc append
        StringBuilder asb = new StringBuilder();
        List<String> asc = pageReq.getAsc();
        if (null != asc && !asc.isEmpty()) {
            for (String column : asc) {
                if (!sorts.contains(column)) {
                    throw new EormException("column [" + column + "] undefined sort!");
                }
                if (0 < asb.length()) {
                    asb.append(',');
                }
                asb.append(column);
            }
            asb.append(" asc");
        }

        StringBuilder dsb = new StringBuilder();
        List<String> desc = pageReq.getDesc();
        if (null != desc && !desc.isEmpty()) {
            if (0 < asb.length()) {
                asb.append(',');
            }
            for (String column : desc) {
                if (!sorts.contains(column)) {
                    throw new EormException("column [" + column + "] undefined sort!");
                }
                if (0 < dsb.length()) {
                    dsb.append(',');
                }
                dsb.append(column);
            }
            dsb.append(" desc");

        }

        if (asb.length() > 0 || dsb.length() > 0) {
            return " order by " + asb.toString() + dsb.toString();
        }
        return "";
    }
}
