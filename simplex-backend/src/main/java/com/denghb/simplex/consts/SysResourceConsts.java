package com.denghb.simplex.consts;

import com.denghb.simplex.base.ConstTag;

/**
 * @author denghb
 * @since 2019/4/16 20:40
 */
public interface SysResourceConsts {

    interface Type {

        @ConstTag("菜单")
        String MENU = "MENU";

        @ConstTag("接口")
        String API = "API";

        @ConstTag("页面")
        String PAGE = "PAGE";

    }
}
