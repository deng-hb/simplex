package com.denghb.simplex.base;


import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Consts {

    private static final Map<String, Map<Object, String>> CACHE_MAP = new ConcurrentHashMap<>();

    public static final String ACCESS_TOKEN = "X-Access-Token";

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

        Map<Object, String> result = map(Test.class);
        System.out.println(result);

        String tag = get(Test.class, Test.TEST1);
        System.out.println(tag);
    }
}
