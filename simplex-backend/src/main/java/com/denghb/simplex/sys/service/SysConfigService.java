package com.denghb.simplex.sys.service;

import com.denghb.simplex.model.PageRes;
import com.denghb.simplex.service.Eservice;
import com.denghb.simplex.sys.domain.SysConfig;
import com.denghb.simplex.sys.model.res.SysConfigRes;

public interface SysConfigService extends Eservice<SysConfig> {

    PageRes<SysConfigRes> list();

}
