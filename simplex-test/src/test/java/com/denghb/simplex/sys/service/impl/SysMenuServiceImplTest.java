package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.sys.service.SysMenuService;
import com.denghb.simplex.test.BaseTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Auther: denghb
 * @Date: 2019/4/15 23:37
 */
public class SysMenuServiceImplTest extends BaseTest {

    @Autowired
    private SysMenuService sysMenuService;

    @Test
    public void list() {
        sysMenuService.list();
    }
}