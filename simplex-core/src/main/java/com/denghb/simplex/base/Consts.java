package com.denghb.simplex.base;


import com.denghb.simplex.consts.ConstsTest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Consts {


    private static final Map<String, List<Map<String, String>>> _cache = new ConcurrentHashMap<>();

    public static final String TOKEN = "X-Token";

    public static final String _label = "label";
    public static final String _value = "value";

    /**
     * 翻译类型
     */
    public static String get(Class clazz, Object value) {
        List<Map<String, String>> list = list(clazz);

        for (Map<String, String> map : list) {
            String v = map.get(_value);
            if (v.equals(String.valueOf(value))) {
                return map.get(_label);
            }
        }

        return null;

    }

    /**
     * 获取类型列表
     */
    public static List<Map<String, String>> list(Class clazz) {
        String key = clazz.getName();
        List<Map<String, String>> list = _cache.get(key);
        if (null != list) {
            return list;
        }
        list = new ArrayList<>();
        _cache.put(key, list);

        Field[] fields = clazz.getDeclaredFields();

        Map<String, String> map = null;
        for (Field field : fields) {
            ConstTag tag = field.getAnnotation(ConstTag.class);
            if (null == tag) {
                continue;
            }

            try {
                field.setAccessible(false);
                Object object = field.get(clazz);
                map = new HashMap<>();
                map.put(_value, String.valueOf(object));
                map.put(_label, tag.value());

                list.add(map);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }

        return list;

    }

    public static void main(String[] args) {

        List<Map<String, String>> list = list(ConstsTest.class);
        System.out.println(list);

        String tag = get(ConstsTest.class, ConstsTest.b);
        System.out.println(tag);
    }
}
