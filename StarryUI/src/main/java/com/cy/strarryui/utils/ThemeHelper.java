package com.cy.strarryui.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.ArrayRes;
import androidx.annotation.AttrRes;
import androidx.annotation.ColorInt;
import androidx.annotation.ColorRes;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.content.ContextCompat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Duckbb
 */
public class ThemeHelper {
    @ColorInt
    public static int getDisabledColor(final Context context) {
        final int primaryColor = resolveColor(context, android.R.attr.textColorPrimary);
        final int disabledColor = isColorDark(primaryColor) ? Color.BLACK : Color.WHITE;
        return adjustAlpha(disabledColor, 0.3f);
    }

    @ColorInt
    public static int adjustAlpha(
            @ColorInt final int color, @SuppressWarnings("SameParameterValue") final float factor) {
        final int alpha = Math.round(Color.alpha(color) * factor);
        final int red = Color.red(color);
        final int green = Color.green(color);
        final int blue = Color.blue(color);
        return Color.argb(alpha, red, green, blue);
    }

    @ColorInt
    public static int resolveColor(final Context context, @AttrRes final int attr) {
        return resolveColor(context, attr, 0);
    }

    @ColorInt
    public static int resolveColor(final Context context, @AttrRes final int attr, final int fallback) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getColor(0, fallback);
        } finally {
            a.recycle();
        }
    }

    public static int getColorFromAttrRes(final int attrRes, final int defaultValue, final Context context) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{attrRes});
        try {
            return a.getColor(0, defaultValue);
        } finally {
            a.recycle();
        }
    }

    public static float resolveFloat(final Context context, final int attrRes) {
        final TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(attrRes, typedValue, true);
        return typedValue.getFloat();
    }

    public static int resolveInt(final Context context, final int attrRes) {
        return resolveInt(context, attrRes, 0);
    }

    public static int resolveInt(final Context context, final int attrRes, final int defaultValue) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{attrRes});
        try {
            return a.getInt(0, defaultValue);
        } finally {
            a.recycle();
        }
    }

    public static float resolveFloat(final Context context, final int attrRes, final float defaultValue) {
        final TypedArray a = context.obtainStyledAttributes(new int[]{attrRes});
        try {
            return a.getFloat(0, defaultValue);
        } finally {
            a.recycle();
        }
    }

    // Try to resolve the colorAttr attribute.
    public static ColorStateList resolveActionTextColorStateList(
            final Context context, @AttrRes final int colorAttr, final ColorStateList fallback) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{colorAttr});
        try {
            final TypedValue value = a.peekValue(0);
            if (value == null) {
                return fallback;
            }
            if (value.type >= TypedValue.TYPE_FIRST_COLOR_INT
                    && value.type <= TypedValue.TYPE_LAST_COLOR_INT) {
                return getActionTextStateList(context, value.data);
            } else {
                final ColorStateList stateList = a.getColorStateList(0);
                if (stateList != null) {
                    return stateList;
                } else {
                    return fallback;
                }
            }
        } finally {
            a.recycle();
        }
    }

    // Get the specified color resource, creating a ColorStateList if the resource
    // points to a color value.
    public static ColorStateList getActionTextColorStateList(final Context context, @ColorRes final int colorId) {
        final TypedValue value = new TypedValue();
        context.getResources().getValue(colorId, value, true);
        if (value.type >= TypedValue.TYPE_FIRST_COLOR_INT
                && value.type <= TypedValue.TYPE_LAST_COLOR_INT) {
            return getActionTextStateList(context, value.data);
        } else {

            if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.LOLLIPOP_MR1) {
                //noinspection deprecation
                return context.getResources().getColorStateList(colorId);
            } else {
                return context.getColorStateList(colorId);
            }
        }
    }

    /**
     * Returns a color associated with a particular resource ID
     *
     * <p>Starting in {@link android.os.Build.VERSION_CODES#M}, the returned color will be styled for
     * the specified Context's theme.
     *
     * @param colorId The desired resource identifier, as generated by the aapt tool. This integer
     *                encodes the package, type, and resource entry. The value 0 is an invalid identifier.
     * @return A single color value in the form 0xAARRGGBB.
     */
    @ColorInt
    public static int getColor(final Context context, @ColorRes final int colorId) {
        return ContextCompat.getColor(context, colorId);
    }

    public static String resolveString(final Context context, @AttrRes final int attr) {
        final TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(attr, v, true);
        return (String) v.string;
    }

    public static String resolveString(final Context context, @AttrRes final int attr, final String defaultValue) {
        final TypedValue v = new TypedValue();
        context.getTheme().resolveAttribute(attr, v, true);
        final String value = (String) v.string;
        return TextUtils.isEmpty(value) ? defaultValue : value;
    }

    public static String resolveString(final Resources.Theme theme, @AttrRes final int attr) {
        final TypedValue v = new TypedValue();
        theme.resolveAttribute(attr, v, true);
        return (String) v.string;
    }


    public static Drawable resolveDrawable(final Context context, @AttrRes final int attr) {
        return resolveDrawable(context, attr, null);
    }

    private static Drawable resolveDrawable(
            final Context context,
            @AttrRes final int attr,
            @SuppressWarnings("SameParameterValue") final Drawable fallback) {
        final TypedArray array = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            Drawable drawable = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                drawable = array.getDrawable(0);
            } else {
                final int id = array.getResourceId(0, -1);
                if (id != -1) {
                    drawable = AppCompatResources.getDrawable(context, id);
                }
            }
            if (drawable == null && fallback != null) {
                drawable = fallback;
            }
            return drawable;
        } finally {
            array.recycle();
        }
    }

    public static int resolveDimension(final Context context, @AttrRes final int attr) {
        return resolveDimension(context, attr, -1);
    }

    public static int resolveDimension(final Context context, @AttrRes final int attr, final int fallback) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getDimensionPixelSize(0, fallback);
        } finally {
            a.recycle();
        }
    }

    public static boolean resolveBoolean(final Context context, @AttrRes final int attr, final boolean fallback) {
        final TypedArray a = context.getTheme().obtainStyledAttributes(new int[]{attr});
        try {
            return a.getBoolean(0, fallback);
        } finally {
            a.recycle();
        }
    }

    public static boolean resolveBoolean(final Context context, @AttrRes final int attr) {
        return resolveBoolean(context, attr, false);
    }

    public static boolean isColorDark(@ColorInt final int color) {
        final double darkness =
                1
                        - (0.299 * Color.red(color) + 0.587 * Color.green(color) + 0.114 * Color.blue(color))
                        / 255;
        return darkness >= 0.5;
    }

    public static void setBackgroundCompat(final View view, final Drawable d) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN) {
            //noinspection deprecation
            view.setBackgroundDrawable(d);
        } else {
            view.setBackground(d);
        }
    }


    public static ColorStateList getActionTextStateList(final Context context, int newPrimaryColor) {
        final int fallBackButtonColor =
                ThemeHelper.resolveColor(context, android.R.attr.textColorPrimary);
        if (newPrimaryColor == 0) {
            newPrimaryColor = fallBackButtonColor;
        }
        final int[][] states =
                new int[][]{
                        new int[]{-android.R.attr.state_enabled}, // disabled
                        new int[]{} // enabled
                };
        final int[] colors = new int[]{ThemeHelper.adjustAlpha(newPrimaryColor, 0.4f), newPrimaryColor};
        return new ColorStateList(states, colors);
    }

    public static int[] getColorArray(@NonNull final Context context, @ArrayRes final int array) {
        if (array == 0) {
            return null;
        }
        final TypedArray ta = context.getResources().obtainTypedArray(array);
        final int[] colors = new int[ta.length()];
        for (int i = 0; i < ta.length(); i++) {
            colors[i] = ta.getColor(i, 0);
        }
        ta.recycle();
        return colors;
    }

    public static <T> boolean isIn(@NonNull final T find, @Nullable final T[] ary) {
        if (ary == null || ary.length == 0) {
            return false;
        }
        for (final T item : ary) {
            if (item.equals(find)) {
                return true;
            }
        }
        return false;
    }

    //========================深色模式==============================//
    /**
     * 系统默认模式
     */
    public static final int DEFAULT_MODE = 0;
    /**
     * 浅色模式
     */
    public static final int LIGHT_MODE = 1;
    /**
     * 深色模式
     */
    public static final int DARK_MODE = 2;

    @IntDef({DEFAULT_MODE, LIGHT_MODE, DARK_MODE})
    @Retention(RetentionPolicy.SOURCE)
    public @interface Theme {
    }

    /**
     * 当前是否是处于深色模式
     *
     * @return 是否是深色模式
     */
    public static boolean isNightMode() {
        final int mode = ResUtils.getResources().getConfiguration().uiMode & Configuration.UI_MODE_NIGHT_MASK;
        return mode == Configuration.UI_MODE_NIGHT_YES;
    }

    /**
     * 设置应用的主题（深色模式）
     *
     * @param theme 主题类型
     */
    @SuppressLint("WrongConstant")
    public static void applyTheme(@Theme final int theme) {
        switch (theme) {
            case LIGHT_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                break;
            case DARK_MODE:
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                break;
            case DEFAULT_MODE:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
                }
                break;
            default:
                break;
        }
    }

}
