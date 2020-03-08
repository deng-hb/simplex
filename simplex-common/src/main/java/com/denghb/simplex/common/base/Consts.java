package com.denghb.simplex.common.base;


import com.alibaba.fastjson.JSON;
import com.denghb.simplex.common.consts.SysResourceConsts;

import java.io.File;
import java.io.PrintStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Consts {

    private static final Map<String, Map<Object, String>> CACHE_MAP = new ConcurrentHashMap<>();

    public static final String ACCESS_TOKEN = "X-Access-Token";
    public static final String USER_AGENT = "User-Agent";

    /**
     * 翻译类型
     */
    public static String get(Class clazz, Object value) {
        Map<Object, String> result = map(clazz);

        if (null != result) {
            return result.get(value);
        }
        return null;

    }

    /**
     * 获取类型对象
     */
    public static Map<Object, String> map(Class clazz) {
        String key = clazz.getName();
        Map<Object, String> result = CACHE_MAP.get(key);
        if (null != result) {
            return result;
        }
        result = new HashMap<>();

        Field[] fields = clazz.getDeclaredFields();

        for (Field field : fields) {
            ConstTag tag = field.getAnnotation(ConstTag.class);
            if (null == tag) {
                continue;
            }

            try {
                field.setAccessible(false);
                Object object = field.get(clazz);
                result.put(object, tag.value());
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        CACHE_MAP.put(key, result);

        return result;

    }


    public static class Test {

        @ConstTag("Test1")
        public static int TEST1 = 1;

        @ConstTag("Test2")
        public static int TEST2 = 2;
    }

    public static void main(String[] args) {

        Map<Object, String> result = map(SysResourceConsts.Type.class);
        System.out.println(result);
        String json = JSON.toJSONString(result);
        System.out.println(json);

        String tag = get(Test.class, Test.TEST1);
        System.out.println(tag);

        genJs("com.denghb.simplex.common.consts", "/Users/mac/IdeaProjects/simplex/simplex-frontend/src/consts.js");

    }

    /**
     * 生成js常量文件(覆盖)
     *
     * @param packageName
     * @param targetJs
     * @return
     */
    private static void genJs(String packageName, String targetJs) {
        String splashPath = packageName.replaceAll("\\.", "/");
        URL url = Consts.class.getClassLoader().getResource(splashPath);
        String filePath = url.getPath();
        File file = new File(filePath);
        String[] names = file.list();


        StringBuilder js = new StringBuilder("/*! code generated don't edit */");
        for (String name : names) {
            String className = name.substring(0, name.length() - 6);
            try {
                Map<Object, String> result = map(Class.forName(packageName + '.' + className));
                if (!result.isEmpty()) {
                    String json = JSON.toJSONString(result);

                    js.append(System.lineSeparator());
                    js.append("export const ");
                    js.append(className);
                    js.append(" = ");
                    js.append(json);
                    js.append(";");
                    js.append(System.lineSeparator());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        // System.out.println(js);
        try {

            PrintStream stream = new PrintStream(targetJs);//写入的文件path
            stream.print(js.toString());//写入的字符串
            stream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
