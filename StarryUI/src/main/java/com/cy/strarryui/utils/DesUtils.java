package com.cy.strarryui.utils;

import android.content.Context;
import android.util.TypedValue;

/**
 * Density Util
 */
public class DesUtils {

    /**
     * dp转px
     */
    public static int dpToPx(final Context context, final float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, context.getResources().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int spToPx(final Context context, final float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, context.getResources().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float pxToDp(final Context context, final float pxVal) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float pxToSp(final Context context, final float pxVal) {
        return (pxVal / context.getResources().getDisplayMetrics().scaledDensity);
    }

}