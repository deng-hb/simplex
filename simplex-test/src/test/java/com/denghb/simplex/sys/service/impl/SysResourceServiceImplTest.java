package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.sys.service.SysResourceService;
import com.denghb.simplex.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 23:37
 */
public class SysResourceServiceImplTest extends BaseTest {

    @Autowired
    private SysResourceService sysResourceService;

    @Test
    public void list() {
        sysResourceService.list();
    }
}