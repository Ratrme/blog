package com.lxy.utils;

import java.util.ArrayList;
import java.util.List;

public class StingToList {
    public static List<Long> convertToLong(String ids) {
        List<Long> list = new ArrayList<>();
        if (!"".equals(ids) && ids != null) {
            String[] split = ids.split(",");
            for (String s : split) {
                list.add(new Long(s));
            }
        }
        return list;
    }
}
