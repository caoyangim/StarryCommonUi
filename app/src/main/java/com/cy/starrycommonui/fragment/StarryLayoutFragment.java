package com.cy.starrycommonui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatSeekBar;
import androidx.fragment.app.Fragment;

import com.cy.starrycommonui.R;
import com.cy.strarryui.widget.layout.StarryLinearLayout;

/**
 * @author Duckbb
 */
public class StarryLayoutFragment extends Fragment {

    private StarryLinearLayout mLayoutStarry;
    private AppCompatSeekBar mSbAlpha, mSbElevation, mSbRadius;
    private TextView mTvAlpha, mTvElevation, mTvRadius;

    private int mRadius;
    private float mShadowAlpha;
    private int mShadowElevation;

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
    }

    private void updateView() {
        mLayoutStarry.setRadiusAndShadow(mRadius, mShadowElevation, mShadowAlpha);
        mTvRadius.setText(getString(R.string.layout_text_starry_seekbar_radius, mRadius));
        mTvElevation.setText(getString(R.string.layout_text_starry_seekbar_elevation, mShadowElevation));
        mTvAlpha.setText(getString(R.string.layout_text_starry_seekbar_alpha, String.valueOf(mShadowAlpha)));
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
