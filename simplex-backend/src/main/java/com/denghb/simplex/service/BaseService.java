package com.denghb.simplex.service;

import com.denghb.eorm.Eorm;
import com.denghb.simplex.base.BizException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author denghb
 * @since 2019/4/13 15:52
 */
public class BaseService {

    @Autowired
    protected Eorm db;

    protected void assertChangeOne(int res) {
        if (1 != res) {
            throw new BizException();
        }
    }
}
