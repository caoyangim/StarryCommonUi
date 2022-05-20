package com.cy.strarryui.widget.layout;

import static com.cy.strarryui.widget.layout.StarryLayoutHelper.COLOR_ORIENTATION_HORIZONTAL;
import static com.cy.strarryui.widget.layout.StarryLayoutHelper.COLOR_ORIENTATION_TR_BL;

import android.view.View;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.IntRange;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author Duckbb
 */
public interface IStarryLayout {
    int HIDE_RADIUS_SIDE_NONE = 0x0;
    int HIDE_RADIUS_SIDE_TOP = 0x0030;
    int HIDE_RADIUS_SIDE_RIGHT = 0x0005;
    int HIDE_RADIUS_SIDE_BOTTOM = 0x0050;
    int HIDE_RADIUS_SIDE_LEFT = 0x0003;
    int MASK_HORIZONTAL = 0x000F;
    int MASK_VERTICAL = 0x00F0;

    @IntDef(value = {
            HIDE_RADIUS_SIDE_NONE,
            HIDE_RADIUS_SIDE_TOP,
            HIDE_RADIUS_SIDE_RIGHT,
            HIDE_RADIUS_SIDE_BOTTOM,
            HIDE_RADIUS_SIDE_LEFT}, flag = true)
    @Retention(RetentionPolicy.SOURCE)
    @interface HideRadiusSide {
    }


    int VIEW_STATE_NORMAL = 0;
    int VIEW_STATE_DISABLED = 1;
    int VIEW_STATE_PRESSED = 2;
    int VIEW_STATE_SELECT = 3;

    @IntDef({VIEW_STATE_NORMAL, VIEW_STATE_DISABLED, VIEW_STATE_PRESSED, VIEW_STATE_SELECT})
    @Retention(RetentionPolicy.SOURCE)
    @interface ViewState {
    }

    /**
     * limit the width of a layout
     *
     * @param widthLimit
     * @return
     */
    boolean setWidthLimit(int widthLimit);

    /**
     * limit the height of a layout
     *
     * @param heightLimit
     * @return
     */
    boolean setHeightLimit(int heightLimit);

    /**
     * determine if the outline contain the padding area, usually false
     *
     * @param outlineExcludePadding
     */
    void setOutlineExcludePadding(boolean outlineExcludePadding);

    /**
     * See {@link View#setElevation(float)}
     *
     * @param elevation
     */
    void setShadowElevation(int elevation);

    /**
     * See {@link View#getElevation()}
     *
     * @return
     */
    int getShadowElevation();

    /**
     * set the outline alpha, which will change the shadow
     *
     * @param shadowAlpha
     */
    void setShadowAlpha(float shadowAlpha);

    /**
     * get the outline alpha we set
     *
     * @return
     */
    float getShadowAlpha();

    /**
     * @param shadowColor opaque color
     * @return
     */
    void setShadowColor(int shadowColor);

    /**
     * @return opaque color
     */
    int getShadowColor();

    /**
     * set the layout radius
     *
     * @param radius
     */
    void setRadius(int radius);

    /**
     * set the layout radius with one or none side been hidden
     *
     * @param radius
     * @param hideRadiusSide
     */
    void setRadius(int radius, @HideRadiusSide int hideRadiusSide);

    /**
     * get the layout radius
     *
     * @return
     */
    int getRadius();

    /**
     * half radius
     */
    void setHalfRadius(boolean halfRadius);

    /**
     * inset the outline if needed
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    void setOutlineInset(int left, int top, int right, int bottom);

    /**
     * the shadow elevation only work after L, so we provide a downgrading compatible solutions for android 4.x
     * usually we use border, but the border may be redundant for android L+. so will not show border default,
     * if your designer like the border exists with shadow, you can call setShowBorderOnlyBeforeL(false)
     *
     * @param showBorderOnlyBeforeL
     */
    void setShowBorderOnlyBeforeL(boolean showBorderOnlyBeforeL);

    /**
     * in some case, we maybe hope the layout only have radius in one side.
     * but there is no convenient way to write the code like canvas.drawPath,
     * so we take another way that hide one radius side
     *
     * @param hideRadiusSide
     */
    void setHideRadiusSide(@HideRadiusSide int hideRadiusSide);

    /**
     * get the side that we have hidden the radius
     *
     * @return
     */
    int getHideRadiusSide();

    /**
     * this method will determine the radius and shadow.
     *
     * @param radius
     * @param shadowElevation
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, int shadowElevation, float shadowAlpha);

    /**
     * this method will determine the radius and shadow with one or none side be hidden
     *
     * @param radius
     * @param hideRadiusSide
     * @param shadowElevation
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, @HideRadiusSide int hideRadiusSide, int shadowElevation, float shadowAlpha);


    /**
     * this method will determine the radius and shadow (support shadowColor if after android 9)with one or none side be hidden
     *
     * @param radius
     * @param hideRadiusSide
     * @param shadowElevation
     * @param shadowColor
     * @param shadowAlpha
     */
    void setRadiusAndShadow(int radius, @HideRadiusSide int hideRadiusSide, int shadowElevation, int shadowColor, float shadowAlpha);

    /**
     * border color, if you don not set it, the layout will not draw the border
     *
     * @param borderColor
     */
    void setBorderColor(@ColorInt int borderColor);

    /**
     * press border color, if you don not set it, the layout will not draw the border
     *
     * @param borderColor
     */
    void setPressBorderColor(@ColorInt int borderColor);

    /**
     * press border color, if you don not set it, the layout will not draw the border
     *
     * @param borderColor
     */
    void setSelectBorderColor(@ColorInt int borderColor);

    /**
     * press border color, if you don not set it, the layout will not draw the border
     *
     * @param borderColor
     */
    void setDisableBorderColor(@ColorInt int borderColor);

    /**
     * border width, default is 1px, usually no need to set
     *
     * @param borderWidth
     */
    void setBorderWidth(int borderWidth);

    /**
     * config the top divider
     *
     * @param topInsetLeft
     * @param topInsetRight
     * @param topDividerHeight
     * @param topDividerColor
     */
    void updateTopDivider(int topInsetLeft, int topInsetRight, int topDividerHeight, int topDividerColor);

    /**
     * config the bottom divider
     *
     * @param bottomInsetLeft
     * @param bottomInsetRight
     * @param bottomDividerHeight
     * @param bottomDividerColor
     */
    void updateBottomDivider(int bottomInsetLeft, int bottomInsetRight, int bottomDividerHeight, int bottomDividerColor);

    /**
     * config the left divider
     *
     * @param leftInsetTop
     * @param leftInsetBottom
     * @param leftDividerWidth
     * @param leftDividerColor
     */
    void updateLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor);

    /**
     * config the right divider
     *
     * @param rightInsetTop
     * @param rightInsetBottom
     * @param rightDividerWidth
     * @param rightDividerColor
     */
    void updateRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor);

    /**
     * show top divider, and hide others
     *
     * @param topInsetLeft
     * @param topInsetRight
     * @param topDividerHeight
     * @param topDividerColor
     */
    void onlyShowTopDivider(int topInsetLeft, int topInsetRight, int topDividerHeight, int topDividerColor);

    /**
     * show bottom divider, and hide others
     *
     * @param bottomInsetLeft
     * @param bottomInsetRight
     * @param bottomDividerHeight
     * @param bottomDividerColor
     */
    void onlyShowBottomDivider(int bottomInsetLeft, int bottomInsetRight, int bottomDividerHeight, int bottomDividerColor);

    /**
     * show left divider, and hide others
     *
     * @param leftInsetTop
     * @param leftInsetBottom
     * @param leftDividerWidth
     * @param leftDividerColor
     */
    void onlyShowLeftDivider(int leftInsetTop, int leftInsetBottom, int leftDividerWidth, int leftDividerColor);

    /**
     * show right divider, and hide others
     *
     * @param rightInsetTop
     * @param rightInsetBottom
     * @param rightDividerWidth
     * @param rightDividerColor
     */
    void onlyShowRightDivider(int rightInsetTop, int rightInsetBottom, int rightDividerWidth, int rightDividerColor);

    /**
     * after config the border, sometimes we need change the alpha of divider with animation,
     * so we provide a method to individually change the alpha
     *
     * @param dividerAlpha [0, 255]
     */
    void setTopDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0, 255]
     */
    void setBottomDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0, 255]
     */
    void setLeftDividerAlpha(int dividerAlpha);

    /**
     * @param dividerAlpha [0, 255]
     */
    void setRightDividerAlpha(int dividerAlpha);

    /**
     * only available before android L
     *
     * @param color
     */
    void setOuterNormalColor(int color);

    /**
     * 设置背景色，不可与setBackground混用
     *
     * @param color colorInt
     */
    void setLayoutColor(@ColorInt int color);

    /**
     * 设置按压状态颜色
     *
     * @param pressColor colorInt
     */
    void setPressLayoutColor(@ColorInt int pressColor);

    /**
     * 设置各状态背景色
     */
    void setLayoutColor(@ColorInt int normalColor, @ColorInt int pressedColor,
                        @ColorInt int disabledColor, @ColorInt int selectedColor);

    /**
     * 设置背景色，渐变
     *
     * @param endColor colorInt
     */
    void setLayoutColorEnd(@ColorInt int endColor);

    /**
     * 设置按压状态颜色，渐变
     *
     * @param pressEndColor colorInt
     */
    void setPressLayoutColorEnd(@ColorInt int pressEndColor);

    /**
     * 设置各状态背景色,渐变
     */
    void setLayoutColorEnd(@ColorInt int normalColor, @ColorInt int pressedColor,
                           @ColorInt int disabledColor, @ColorInt int selectedColor);

    /**
     * 设置渐变方向
     *
     * @param orientation see{@link StarryLayoutHelper#COLOR_ORIENTATION_HORIZONTAL}
     */
    public void setColorOrientation(@IntRange(from = COLOR_ORIENTATION_HORIZONTAL, to = COLOR_ORIENTATION_TR_BL) int orientation);
}
