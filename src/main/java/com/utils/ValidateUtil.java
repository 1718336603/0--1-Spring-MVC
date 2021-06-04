package com.utils;

import lombok.extern.slf4j.Slf4j;

/**
 * @DESC 校检数据用的，保证代码的健壮性
 * @AUTHOR zzf
 * @DATA 2021/5/30 18:13
 */
@Slf4j
public class ValidateUtil {

    public static boolean isNotEmpty(String[] paramValues) {
        return paramValues.length==0;
    }

    public static boolean isEmpty(String value) {
        return null==value;
    }

    public static boolean isNotEmpty(Object paramValues) {
        return null==paramValues;
    }
}
