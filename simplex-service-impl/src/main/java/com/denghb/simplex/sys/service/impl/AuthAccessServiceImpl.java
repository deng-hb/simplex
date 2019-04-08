package com.denghb.simplex.sys.service.impl;

import com.denghb.simplex.base.Credential;
import com.denghb.simplex.sys.service.AuthAccessService;
import org.springframework.stereotype.Service;

@Service
public class AuthAccessServiceImpl implements AuthAccessService {

    @Override
    public boolean isOpened(String method, String uri) {
        return false;
    }

    @Override
    public Credential validate(String accessToken, String ip, String userAgent) {
        return null;
    }
}
