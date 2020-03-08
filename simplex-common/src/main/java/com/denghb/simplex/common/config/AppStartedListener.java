package com.denghb.simplex.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.embedded.EmbeddedServletContainerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;

@Slf4j
@Component
@Order(Integer.MAX_VALUE)
public class AppStartedListener implements ApplicationListener<EmbeddedServletContainerInitializedEvent> {

    @Override
    public void onApplicationEvent(EmbeddedServletContainerInitializedEvent event) {
        int port = event.getEmbeddedServletContainer().getPort();

        log.info(String.format("start:http://%s:%d", getHost(), port));
    }

    private static String getHost() {
        try {
            InetAddress ipv4 = Inet4Address.getLocalHost();
            return ipv4.getHostAddress();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }
}