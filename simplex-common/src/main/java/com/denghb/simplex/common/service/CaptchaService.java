package com.denghb.simplex.common.service;


import com.denghb.simplex.common.model.CaptchaRes;

public interface CaptchaService {

    CaptchaRes generate();

    boolean validate(String key, String code);
}
