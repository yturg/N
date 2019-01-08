package com.xin.mynews.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.xin.mynews.R;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.ui.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class SearchActivity extends BaseActivity {
    @BindView(R.id.search_action_back)
    ImageView mActionBack;
    @BindView(R.id.search_edit_view)
    EditText mSearchEditView;
    @BindView(R.id.tv_search)
    TextView mSearchTV;

    @OnClick(R.id.search_action_back)
    public void backClick() {
        finish();
    }


    @Override
    public void onRetry() {

    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_search_layout;
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {

    }
}
