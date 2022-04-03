package com.cy.strarryui.widget.alpha;

import android.view.View;

import com.cy.strarryui.R;
import com.cy.strarryui.utils.ResHelper;

import java.lang.ref.WeakReference;

/**
 * @author Duckbb
 */
public class AlphaViewHelper implements IAlphaViewHelper {


    private final WeakReference<View> mTarget;

    /**
     * 设置是否要在 press 时改变透明度
     */
    private boolean mChangeAlphaWhenPress;

    /**
     * 设置是否要在 disabled 时改变透明度
     */
    private boolean mChangeAlphaWhenDisable;

    private final float mNormalAlpha = 1F;
    private final float mPressedAlpha;
    private final float mDisabledAlpha;

    public AlphaViewHelper(final View target) {
        mTarget = new WeakReference<>(target);
        mPressedAlpha = ResHelper.resolveFloat(target.getContext(), R.attr.starry_alpha_pressed);
        mDisabledAlpha = ResHelper.resolveFloat(target.getContext(), R.attr.starry_alpha_disabled);
    }

    public AlphaViewHelper(final View target, final float pressedAlpha, final float disabledAlpha) {
        mTarget = new WeakReference<>(target);
        mPressedAlpha = pressedAlpha;
        mDisabledAlpha = disabledAlpha;
    }

    @Override
    public void onPressedChanged(final View current, final boolean pressed) {
        final View target = mTarget.get();
        if (target == null) {
            return;
        }
        if (current.isEnabled()) {
            target.setAlpha(mChangeAlphaWhenPress && pressed && current.isClickable() ? mPressedAlpha : mNormalAlpha);
        } else {
            if (mChangeAlphaWhenDisable) {
                target.setAlpha(mDisabledAlpha);
            }
        }
    }

    @Override
    public void onEnabledChanged(final View current, final boolean enabled) {
        final View target = mTarget.get();
        if (target == null) {
            return;
        }
        final float alphaForIsEnable;
        if (mChangeAlphaWhenDisable) {
            alphaForIsEnable = enabled ? mNormalAlpha : mDisabledAlpha;
        } else {
            alphaForIsEnable = mNormalAlpha;
        }
        if (current != target && target.isEnabled() != enabled) {
            target.setEnabled(enabled);
        }
        target.setAlpha(alphaForIsEnable);
    }

    @Override
    public void setChangeAlphaWhenPress(final boolean changeAlphaWhenPress) {
        mChangeAlphaWhenPress = changeAlphaWhenPress;
    }

    @Override
    public void setChangeAlphaWhenDisable(final boolean changeAlphaWhenDisable) {
        mChangeAlphaWhenDisable = changeAlphaWhenDisable;
        final View target = mTarget.get();
        if (target != null) {
            onEnabledChanged(target, target.isEnabled());
        }
    }
}
