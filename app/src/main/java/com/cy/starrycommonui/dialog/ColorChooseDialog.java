package com.cy.starrycommonui.dialog;

import static com.cy.starrycommonui.base.DpConst.DP_5;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewGroup;

import androidx.annotation.ColorRes;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialog;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.cy.starrycommonui.R;
import com.cy.starrycommonui.base.BaseDefViewHolder;
import com.cy.starrycommonui.base.DpConst;
import com.cy.strarryui.utils.DesUtils;
import com.cy.strarryui.utils.ResUtils;
import com.cy.strarryui.widget.layout.StarryFrameLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Duckbb
 */
public class ColorChooseDialog extends AppCompatDialog {
    private static final int[] COLOR_RES_LIST = {
            R.color.aliceblue, R.color.seaShell, R.color.cadetblue, R.color.saddlebrown, R.color.slateblue,
            R.color.aqua, R.color.darkolivegreen, R.color.darkkhaki, R.color.mediumaquamarine, R.color.fuchsia
    };
    private RecyclerView mColorRv;

    private int mSelectPos = -1;

    public ColorChooseDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.dialog_choose_color);
        mColorRv = findViewById(R.id.rc_choose_color);
        mColorRv.setAdapter(mColorAdapter);
        mColorRv.setLayoutManager(new GridLayoutManager(getContext(), 5));
        initData();
        mColorAdapter.setOnItemClickListener((adapter, view, position) -> {
            if (mOnColorListener != null) {
                mOnColorListener.colorChoose(position, COLOR_RES_LIST[position]);
            }
            notifyPosition(position);
            dismiss();
        });
    }

    private void initData() {
        final List<Integer> colors = new ArrayList<>(COLOR_RES_LIST.length);
        for (@ColorRes int colorRes : COLOR_RES_LIST) {
            colors.add(ResUtils.getColor(colorRes));
        }
        mColorAdapter.setNewData(colors);
    }

    public void setSelectPos(int pos) {
        if (mSelectPos == pos) {
            return;
        }
        notifyPosition(pos);
    }

    /**
     * 更新某位置 和 当前位置
     */
    private void notifyPosition(int position) {
        int prePos = mSelectPos;
        mSelectPos = position;
        mColorAdapter.notifyItemChanged(prePos);
        mColorAdapter.notifyItemChanged(position);
    }

    private final BaseQuickAdapter<Integer, BaseViewHolder> mColorAdapter =
            new BaseQuickAdapter<Integer, BaseViewHolder>(null) {

                @NonNull
                @Override
                protected BaseViewHolder onCreateDefViewHolder(@NonNull ViewGroup parent, int viewType) {
                    final StarryFrameLayout itemView = new StarryFrameLayout(parent.getContext());
                    itemView.setBorderColor(ResUtils.getColor(R.color.black));
                    itemView.setBorderWidth(DesUtils.dpToPx(2));
                    itemView.setLayoutColor(ResUtils.getColor(R.color.blue));
                    final ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(DpConst.DP_30, DpConst.DP_30);
                    params.setMargins(DpConst.DP_20, DpConst.DP_20, DpConst.DP_20, DpConst.DP_20);
                    itemView.setLayoutParams(params);
                    return new BaseDefViewHolder(itemView);
                }

                @Override
                protected void convert(@NonNull BaseViewHolder vh, Integer color) {
                    StarryFrameLayout itemView = (StarryFrameLayout) vh.itemView;
                    itemView.setLayoutColor(color);
                    if (mSelectPos == vh.getLayoutPosition()) {
                        final AppCompatImageView imageView = new AppCompatImageView(itemView.getContext());
                        itemView.addView(imageView, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                        imageView.setImageResource(R.drawable.ic_baseline_check_24);
                        imageView.setPadding(DP_5, DP_5, DP_5, DP_5);
                    }
                }

            };

    private OnColorChooseListener mOnColorListener;

    public void setOnColorChooseListener(OnColorChooseListener listener) {
        this.mOnColorListener = listener;
    }

    public interface OnColorChooseListener {
        void colorChoose(int pos, @ColorRes int color);
    }
}
