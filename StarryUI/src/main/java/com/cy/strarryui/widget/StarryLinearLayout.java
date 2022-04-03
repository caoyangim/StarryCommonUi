package com.cy.strarryui.widget;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.cy.strarryui.widget.alpha.AlphaLinearLayout;

/**
 * DuckBb
 *
 * @author Duckbb
 */
public class StarryLinearLayout extends AlphaLinearLayout {

    public StarryLinearLayout(@NonNull final Context context) {
        this(context, null);
    }

    public StarryLinearLayout(@NonNull final Context context, @Nullable final AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StarryLinearLayout(@NonNull final Context context, @Nullable final AttributeSet attrs, final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

}
