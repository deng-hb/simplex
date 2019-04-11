package com.denghb.simplex.sys.service.impl;


import com.denghb.simplex.sys.model.SysUserReq;
import com.denghb.simplex.sys.model.SysUserSignInRes;
import com.denghb.simplex.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysUserServiceImpl implements SysUserService {

    @Override
    public void save(SysUserReq req) {

    }

    @Override
    public SysUserSignInRes signIn(String username, String password) {
        return null;
    }
}
