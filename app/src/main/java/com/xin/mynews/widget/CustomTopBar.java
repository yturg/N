package com.xin.mynews.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.xin.mynews.R;

public class CustomTopBar extends RelativeLayout {

    private OnActionClickListener mActionClickListener;
    private String mTitle;
    private String mBackText;
    private String mMenuText;
    private int mTextColor;

    public void setActionClickListener(OnActionClickListener actionClickListener) {
        this.mActionClickListener = actionClickListener;
    }

    public CustomTopBar(Context context) {
        this(context, null);
    }

    public CustomTopBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CustomTopBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.costom_top_bar_layout, this, true);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CustomTopBar);
        mTitle = typedArray.getString(R.styleable.CustomTopBar_action_title);
        mBackText = typedArray.getString(R.styleable.CustomTopBar_action_back_text);
        mMenuText = typedArray.getString(R.styleable.CustomTopBar_action_menu);
        mTextColor = typedArray.getInt(R.styleable.CustomTopBar_action_textColor, 0);
        initView(view);
    }

    private void initView(View contentView) {
        final TextView mActionBackView = contentView.findViewById(R.id.action_bar_back);
        mActionBackView.setText(mBackText);
        TextView mActionTitleView = contentView.findViewById(R.id.action_bar_title);
        mActionTitleView.setText(mTitle);
        TextView mActionMenuView = contentView.findViewById(R.id.action_bar_menu);
        mActionMenuView.setText(mMenuText);

        mActionBackView.setTextColor(mTextColor);
        mActionTitleView.setTextColor(mTextColor);
        mActionMenuView.setTextColor(mTextColor);

        mActionBackView.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mActionClickListener != null) {
                            mActionClickListener.onBackClick();
                        }
                    }
                }
        );
        mActionMenuView.setOnClickListener(
                new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (mActionClickListener != null) {
                            mActionClickListener.onMenuClicker();
                        }
                    }
                }
        );
    }

    public interface OnActionClickListener {
        void onBackClick();

        void onMenuClicker();
    }
}
