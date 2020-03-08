package com.denghb.simplex.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 需要执行的逻辑代码，当spring容器初始化完成后就会执行该方法。
 */
@Slf4j
@Component
public class SimpleXApplicationListener implements ApplicationListener<ContextRefreshedEvent> {

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        log.info("init");
    }
}