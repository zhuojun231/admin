package com.jingluu.admin.util;

public class StringUtils {
    public static boolean isEmpty(String str){
        return str == null || str.trim().length() == 0;
    }

    public static String join(final Object[] array){
        return join(array,",");
    }

    public static String join(final Object[] array, final String separator){
        return org.apache.commons.lang3.StringUtils.join(array,",");
    }
}
