package com.xin.mynews.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.flyco.tablayout.SlidingTabLayout;
import com.xin.mynews.R;
import com.xin.mynews.activity.SearchActivity;
import com.xin.mynews.bean.Channel;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.component.DaggerHttpComponent;
import com.xin.mynews.ui.adapter.ChannelPagerAdapter;
import com.xin.mynews.ui.base.BaseFragment;
import com.xin.mynews.ui.main.Presenter.NewsPresenter;
import com.xin.mynews.ui.main.contract.NewsContract;
import com.xin.mynews.widget.CustomViewPager;

import org.simple.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: zxj 暂时不用
 * date: 18/7/2
 */
@Deprecated
public class HomeFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {
    @BindView(R.id.main_viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.title_main)
    SlidingTabLayout mActionBarMain;
    @BindView(R.id.search_text_view)
    TextView mSearchView;

    private List<Channel> mSelectDatas;
    private List<Channel> mUnSelectDatas;

    private int mSelectIndex;
    private String mSelectChannelName;

    private ChannelPagerAdapter mChannelPagerAdapter;

    public static HomeFragment newInstance() {
        Bundle args = new Bundle();
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_main_news;
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
        EventBus.getDefault().register(this);

        mSearchView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SearchActivity.class);
                startActivity(intent);
            }
        });

        mViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mSelectIndex = position;
                mSelectChannelName = mSelectDatas.get(position).getChannelName();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public void initData() {
        mSelectDatas = new ArrayList<>();
        Channel concern = new Channel();
        concern.setChannelId("concern_1");
        concern.setChannelName("关注");
        mSelectDatas.add(concern);

        Channel discover = new Channel();
        discover.setChannelId("discover_2");
        discover.setChannelName("发现");
        mSelectDatas.add(discover);

        Channel near = new Channel();
        near.setChannelId("discover_2");
        near.setChannelName("发现");
        mSelectDatas.add(near);

        mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(), mSelectDatas);
        mViewpager.setAdapter(mChannelPagerAdapter);
        mViewpager.setOffscreenPageLimit(2);
        mViewpager.setCurrentItem(1, false);
        mActionBarMain.setViewPager(mViewpager);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }

    @Override
    public void loadData(List<Channel> channels, List<Channel> otherChannels) {

    }

    private class HomeViewPager extends FragmentPagerAdapter {

        private List<Fragment> mFragments;

        public HomeViewPager(FragmentManager fm, List<Fragment> fragments) {
            super(fm);
            this.mFragments = fragments;
        }

        @Override
        public Fragment getItem(int i) {
            return mFragments.get(i);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            return super.instantiateItem(container, position);
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            super.destroyItem(container, position, object);
        }
    }
}
