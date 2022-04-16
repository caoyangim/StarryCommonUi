package com.cy.strarryui.utils;

import android.content.res.Resources;
import android.util.TypedValue;

/**
 * Density Util
 */
public class DesUtils {

    /**
     * dp转px
     */
    public static int dpToPx(final float dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dpVal, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * sp转px
     */
    public static int spToPx(final float spVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                spVal, Resources.getSystem().getDisplayMetrics());
    }

    /**
     * px转dp
     */
    public static float pxToDp(final float pxVal) {
        final float scale = Resources.getSystem().getDisplayMetrics().density;
        return (pxVal / scale);
    }

    /**
     * px转sp
     */
    public static float pxToSp(final float pxVal) {
        return (pxVal / Resources.getSystem().getDisplayMetrics().scaledDensity);
    }

}