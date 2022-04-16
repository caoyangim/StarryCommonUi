package com.cy.starrycommonui.util;

import java.util.List;

/**
 * @author Duckbb
 * @description 常规工具
 * @date 2022年04月16日 15:33
 */
public class Util {
    /**
     * 检查pos位置的数组是否越界
     *
     * @return true 安全；false 越界
     */
    public static <T> boolean isOutBound(List<T> list, int pos) {
        if (list == null || list.isEmpty()) {
            return false;
        }
        return pos >= 0 && pos < list.size();
    }

    public static <T> boolean isOutBound(T[] list, int pos) {
        if (list == null || list.length == 0) {
            return false;
        }
        return pos >= 0 && pos < list.length;
    }

    public static boolean isOutBound(int[] list, int pos) {
        if (list == null || list.length == 0) {
            return false;
        }
        return pos >= 0 && pos < list.length;
    }
}
