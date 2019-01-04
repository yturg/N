package com.xin.mynews.ui.video;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xin.mynews.R;
import com.xin.mynews.bean.VideoChannelBean;
import com.xin.mynews.bean.VideoDetailBean;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.component.DaggerHttpComponent;
import com.xin.mynews.ui.adapter.VideoPagerAdapter;
import com.xin.mynews.ui.base.BaseFragment;
import com.xin.mynews.ui.video.contract.VideoContract;
import com.xin.mynews.ui.video.presenter.VideoPresenter;

import java.util.List;

import butterknife.BindView;

/**
 * author: zxj
 * date: 18/7/2
 */
public class VideosFragment extends BaseFragment<VideoPresenter> implements VideoContract.View {

    @BindView(R.id.tablayout)
    TabLayout mTabLayout;
    @BindView(R.id.viewpager)
    ViewPager mViewPager;

    private VideoPagerAdapter mVideoPagerAdapter;

    public static VideosFragment newInstance() {
        Bundle args = new Bundle();
        VideosFragment fragment = new VideosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_video;
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {
        DaggerHttpComponent.builder()
                .applicationComponent(applicationComponent)
                .build()
                .inject(this);
    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        mPresenter.getVideoChannel();
    }

    @Override
    public void loadVideoChannel(List<VideoChannelBean> videoChannelBeans) {
        mVideoPagerAdapter = new VideoPagerAdapter(getChildFragmentManager(),videoChannelBeans.get(0));
        mViewPager.setAdapter(mVideoPagerAdapter);
        mViewPager.setOffscreenPageLimit(1);
        mViewPager.setCurrentItem(0,false);
        mTabLayout.setupWithViewPager(mViewPager,true);
    }

    @Override
    public void loadVideoDetails(List<VideoDetailBean> videoDetailBeans) {

    }

    @Override
    public void loadMoreVideoDetails(List<VideoDetailBean> videoDetailBeans) {

    }

}
