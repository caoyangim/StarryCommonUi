package com.cy.strarryui.widget.alpha;

import android.view.View;

/**
 * @author Duckbb
 */
public interface IAlphaViewHelper {
    /**
     * 处理setPressed
     *
     * @param current 当前view
     * @param pressed 是否按压
     */
    void onPressedChanged(View current, boolean pressed);

    /**
     * 处理setEnabled
     *
     * @param current 当前view
     * @param enabled 是否可用
     */
    void onEnabledChanged(View current, boolean enabled);

    /**
     * 设置是否要在 press 时改变透明度
     *
     * @param changeAlphaWhenPress 是否要在 press 时改变透明度
     */
    void setChangeAlphaWhenPress(boolean changeAlphaWhenPress);

    /**
     * 设置是否要在 disabled 时改变透明度
     *
     * @param changeAlphaWhenDisable 是否要在 disabled 时改变透明度
     */
    void setChangeAlphaWhenDisable(boolean changeAlphaWhenDisable);
}
