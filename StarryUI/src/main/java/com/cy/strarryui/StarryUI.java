package com.cy.strarryui;

import android.app.Application;
import android.content.Context;

/**
 * @author Duckbb
 */
public class StarryUI {
    private static Application sContext;

    //=======================初始化设置===========================//

    /**
     * 初始化
     *
     * @param context 上下文
     */
    public static void init(final Application context) {
        sContext = context;
    }

    public static Context getContext() {
        testInitialize();
        return sContext;
    }

    private static void testInitialize() {
        if (sContext == null) {
            throw new ExceptionInInitializerError("请先在全局Application中调用 StarryUI.init() 初始化！");
        }
    }
}
