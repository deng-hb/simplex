package com.denghb.simplex.consts;

import com.denghb.simplex.base.ConstTag;

/**
 * @author denghb
 * @since 2019/4/13 17:00
 */
public class SysUserConsts {

    public static class Status {

        @ConstTag("正常")
        public static int NORMAL = 1;

        @ConstTag("登录密码错误过多锁住")
        public static int LOCK_SIGN_ERR = 2;

        @ConstTag("禁用")
        public static int DISABLED = 3;
    }
}
