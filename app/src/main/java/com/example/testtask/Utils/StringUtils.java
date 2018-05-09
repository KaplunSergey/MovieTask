package com.example.testtask.Utils;

import java.util.Arrays;
import java.util.List;

public final class StringUtils {

    private static final String LIST_SEPARATOR = ",";

    public static String convertListToString(List<String> list){
        String str = "";
        for (int i = 0; i<list.size(); i++) {
            str = str + list.get(i);

            if(i < list.size() - 1){
                str += LIST_SEPARATOR;
            }
        }
        return str;
    }

    public static List<String> convertStringToList(String str){
        List<String> list = Arrays.asList(str.split(LIST_SEPARATOR));
        return list;
    }
}
