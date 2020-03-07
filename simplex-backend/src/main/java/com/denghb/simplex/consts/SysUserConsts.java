package com.denghb.simplex.consts;

import com.denghb.simplex.base.ConstTag;

/**
 * @author denghb
 * @since 2019/4/13 17:00
 */
public interface SysUserConsts {

    interface Status {

        @ConstTag("正常")
        int NORMAL = 1;

        @ConstTag("登录密码错误过多锁住")
        int LOCK_SIGN_ERR = 2;

        @ConstTag("禁用")
        int DISABLED = 3;
    }
}
