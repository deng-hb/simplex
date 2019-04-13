package com.denghb.simplex.sys.service;

import com.denghb.eorm.Eorm;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: denghb
 * @Date: 2019/4/13 15:52
 */
public class BaseService {

    @Autowired
    protected
    Eorm db;
}
