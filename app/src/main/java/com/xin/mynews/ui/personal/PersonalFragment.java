package com.xin.mynews.ui.personal;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.xin.mynews.R;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.ui.base.BaseFragment;

import butterknife.OnClick;

/**
 * author: zxj
 * date: 18/7/2
 */
public class PersonalFragment extends BaseFragment{

    public static PersonalFragment newInstance(){
        Bundle args = new Bundle();
        PersonalFragment fragment = new PersonalFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_personal;
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
