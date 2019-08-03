package com.denghb.simplex.service;

import com.denghb.simplex.model.CaptchaRes;

public interface CaptchaService {

    CaptchaRes generate();

    boolean validate(String key, String code);
}
