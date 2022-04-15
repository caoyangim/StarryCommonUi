package com.cy.starrycommonui.util;

import android.util.Log;

/**
 * @author Duckbb
 */
public class LogUtil {
    private static final String TAG = "Common Log :";

    private LogUtil() {
    }

    public static void d(String msg) {
        Log.d(TAG, msg);
    }
}
