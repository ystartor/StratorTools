package com.ystartor.map;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @desc 把map的key转换成大小写
 */
public class LowerUpperUpdate {

    public static void main(String[] args) {

        Map<String, Object> m3 = new HashMap<String, Object>();
        m3.put("a", "abc");
        m3.put("b", "123");
        m3.put("C", "123");
        m3.put("dD", "123");

        Map<String, Object> m4 = new HashMap<String, Object>();
        m4.put("b", "123");
        m4.put("As_Dd_KO", "abc");
        m4.put("cD_s", "123");
        m4.put("d", "123");

        System.out.println("将m4数据的key全部转换为大写===" + transformUpperCase(m4));
        System.out.println("将m3数据的key全部转换为小写===" + transformLowerCase(m4));

    }


    public static Map<String, Object> transformUpperCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toUpperCase();
            resultMap.put(newKey, orgMap.get(key));
        }
        return resultMap;
    }

    // 将map值全部转换为小写
    public static Map<String, Object> transformLowerCase(Map<String, Object> orgMap) {
        Map<String, Object> resultMap = new HashMap<>();
        if (orgMap == null || orgMap.isEmpty()) {
            return resultMap;
        }
        Set<String> keySet = orgMap.keySet();
        for (String key : keySet) {
            String newKey = key.toLowerCase();
			newKey = newKey.replace("_", "");
            resultMap.put(newKey, orgMap.get(key));
        }
        return resultMap;
    }

}
