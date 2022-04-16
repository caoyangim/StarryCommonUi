package com.cy.starrycommonui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;

import com.cy.starrycommonui.R;
import com.cy.starrycommonui.dialog.ColorChooseDialog;
import com.cy.strarryui.utils.ResUtils;
import com.cy.strarryui.widget.layout.IStarryLayout;
import com.cy.strarryui.widget.layout.StarryButton;
import com.cy.strarryui.widget.layout.StarryLinearLayout;

/**
 * @author Duckbb
 */
public class StarryLayoutFragment extends Fragment {

    private StarryLinearLayout mLayoutStarry;
    private AppCompatSeekBar mSbAlpha, mSbElevation, mSbRadius;
    private TextView mTvAlpha, mTvElevation, mTvRadius;
    private StarryButton mBtnStartColor, mBtnEndColor;
    private FrameLayout mFlStarColor, mFlEndColor;
    private RadioGroup mHideRadiusGroup;

    private int mRadius;
    private float mShadowAlpha;
    private int mShadowElevation;
    private @ColorRes
    int mStartColor = R.color.white, mEndColor = R.color.white;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_starry_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        mLayoutStarry = view.findViewById(R.id.layout_starry);
        mSbAlpha = view.findViewById(R.id.seekbar_alpha);
        mSbElevation = view.findViewById(R.id.seekbar_elevation);
        mSbRadius = view.findViewById(R.id.seekbar_radius);
        mTvRadius = view.findViewById(R.id.tv_starry_radius);
        mTvAlpha = view.findViewById(R.id.tv_starry_alpha);
        mTvElevation = view.findViewById(R.id.tv_starry_elevation);
        mBtnStartColor = view.findViewById(R.id.btn_choose_start_color);
        mBtnEndColor = view.findViewById(R.id.btn_choose_end_color);
        mFlStarColor = view.findViewById(R.id.fl_choose_start_color_value);
        mFlEndColor = view.findViewById(R.id.fl_choose_end_color_value);
        mHideRadiusGroup = view.findViewById(R.id.hide_radius_group);
        setListener();

        mLayoutStarry.post(() -> {
            final int half = Math.min(mLayoutStarry.getWidth(), mLayoutStarry.getHeight()) / 2;
            mSbRadius.setMax(half);
        });
    }


    private void setListener() {
        mSbAlpha.setOnSeekBarChangeListener(new SeekBarChangeAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mShadowAlpha = progress * 1f / 100;
                updateView();
            }
        });
        mSbElevation.setOnSeekBarChangeListener(new SeekBarChangeAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mShadowElevation = progress;
                updateView();
            }
        });
        mSbRadius.setOnSeekBarChangeListener(new SeekBarChangeAdapter() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mRadius = progress;
                updateView();
            }
        });
        mBtnStartColor.setOnClickListener(mColorChooseListener);
        mBtnEndColor.setOnClickListener(mColorChooseListener);

        mHideRadiusGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.hide_radius_none) {
                mLayoutStarry.setRadius(mRadius, IStarryLayout.HIDE_RADIUS_SIDE_NONE);
            } else if (checkedId == R.id.hide_radius_left) {
                mLayoutStarry.setRadius(mRadius, IStarryLayout.HIDE_RADIUS_SIDE_LEFT);
            } else if (checkedId == R.id.hide_radius_top) {
                mLayoutStarry.setRadius(mRadius, IStarryLayout.HIDE_RADIUS_SIDE_TOP);
            } else if (checkedId == R.id.hide_radius_bottom) {
                mLayoutStarry.setRadius(mRadius, IStarryLayout.HIDE_RADIUS_SIDE_BOTTOM);
            } else if (checkedId == R.id.hide_radius_right) {
                mLayoutStarry.setRadius(mRadius, IStarryLayout.HIDE_RADIUS_SIDE_RIGHT);
            }
        });
    }

    private void updateView() {
        mLayoutStarry.setLayoutColor(ResUtils.getColor(mStartColor));
        mLayoutStarry.setLayoutColorEnd(ResUtils.getColor(mEndColor));
        mLayoutStarry.setRadiusAndShadow(mRadius, mShadowElevation, mShadowAlpha);

        mTvRadius.setText(getString(R.string.layout_text_starry_seekbar_radius, mRadius));
        mTvElevation.setText(getString(R.string.layout_text_starry_seekbar_elevation, mShadowElevation));
        mTvAlpha.setText(getString(R.string.layout_text_starry_seekbar_alpha, String.valueOf(mShadowAlpha)));

        mFlStarColor.setBackgroundColor(ResUtils.getColor(mStartColor));
        mFlEndColor.setBackgroundColor(ResUtils.getColor(mEndColor));
    }

    private final View.OnClickListener mColorChooseListener = v -> {
        if (v == mBtnStartColor) {
            showChooseColorDialog(true);
        } else if (v == mBtnEndColor) {
            showChooseColorDialog(false);
        }
    };
    private ColorChooseDialog mColorDialog;

    private int mStartPosition = -1;
    private int mEndPosition = -1;

    private void showChooseColorDialog(boolean start) {
        if (mColorDialog == null) {
            mColorDialog = new ColorChooseDialog(getContext());
        }
        mColorDialog.setSelectPos(start ? mStartPosition : mEndPosition);
        mColorDialog.setOnColorChooseListener((pos, color) -> {
            if (start) {
                mStartPosition = pos;
                mStartColor = color;
            } else {
                mEndPosition = pos;
                mEndColor = color;
            }
            updateView();
        });
        mColorDialog.show();
    }

    private static class SeekBarChangeAdapter implements SeekBar.OnSeekBarChangeListener {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    }
}
