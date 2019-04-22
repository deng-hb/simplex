package com.denghb.simplex.sys.controller;

import com.denghb.simplex.base.JSONModel;
import com.denghb.simplex.holder.RequestInfoContextHolder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

/**
 * @Auther: denghb
 * @Date: 2019/4/22 22:52
 */
@RestController
@RequestMapping("/system")
public class SystemInfoController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/info")
    public JSONModel<Map<String, Object>> info() throws UnknownHostException {
        Map<String, Object> data = new TreeMap<>();

        data.put("1.本机IP地址:", InetAddress.getLocalHost().getHostAddress());
        data.put("2.运行端口:", port);
        // 虚拟机级内存情况查询
        long vmFree = 0;
        long vmUse = 0;
        long vmTotal = 0;
        long vmMax = 0;
        int byteToMb = 1024 * 1024;
        Runtime rt = Runtime.getRuntime();
        vmTotal = rt.totalMemory() / byteToMb;
        vmFree = rt.freeMemory() / byteToMb;
        vmMax = rt.maxMemory() / byteToMb;
        vmUse = vmTotal - vmFree;
        data.put("3.JVM内存已用的空间为：", vmUse + " MB");
        data.put("4.JVM内存的空闲空间为：", vmFree + " MB");
        data.put("5.JVM总内存空间为：", vmTotal + " MB");
        data.put("6.JVM最大内存空间为：", vmMax + " MB");

        // 获得线程总数
        ThreadGroup parentThread;
        int totalThread = 0;
        for (parentThread = Thread.currentThread().getThreadGroup(); parentThread
                .getParent() != null; parentThread = parentThread.getParent()) {
            totalThread = parentThread.activeCount();
        }
        data.put("7.线程总数:", totalThread);

        Properties props = System.getProperties();
        data.put("8.Java的运行环境版本:", props.getProperty("java.version"));
        data.put("9.Req:", RequestInfoContextHolder.get());

        return JSONModel.buildSuccess("ok", data);
    }
}
