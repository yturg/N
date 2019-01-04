package com.xin.mynews.ui.news;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;

import com.xin.mynews.R;
import com.xin.mynews.bean.Channel;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.component.DaggerHttpComponent;
import com.xin.mynews.database.ChannelDao;
import com.xin.mynews.event.NewChannelEvent;
import com.xin.mynews.event.SelectChannelEvent;
import com.xin.mynews.ui.adapter.ChannelPagerAdapter;
import com.xin.mynews.ui.base.BaseFragment;
import com.xin.mynews.ui.news.Presenter.NewsPresenter;
import com.xin.mynews.ui.news.contract.NewsContract;
import com.xin.mynews.widget.CustomViewPager;

import org.simple.eventbus.EventBus;
import org.simple.eventbus.Subscriber;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: zxj
 * date: 18/7/2
 */
public class NewsFragment extends BaseFragment<NewsPresenter> implements NewsContract.View {

    @BindView(R.id.viewpager)
    CustomViewPager mViewpager;
    @BindView(R.id.iv_edit)
    ImageView mIvEdit;
    @BindView(R.id.SlidingTabLayout)
    com.flyco.tablayout.SlidingTabLayout mSlidingTabLayout;

    private List<Channel> mSelectDatas;
    private List<Channel> mUnSelectDatas;

    private int mSelectIndex;
    private String mSelectChannelName;

    private ChannelPagerAdapter mChannelPagerAdapter;

    public static NewsFragment newInstance() {
        Bundle args = new Bundle();
        NewsFragment fragment = new NewsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_news_new;
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
        mUnSelectDatas = new ArrayList<>();
        mPresenter.getChannel();
    }

    @Override
    public void loadData(List<Channel> channels, List<Channel> unChannels) {
        if(channels != null){
            mSelectDatas.clear();
            mSelectDatas.addAll(channels);
            mUnSelectDatas.clear();
            mUnSelectDatas.addAll(unChannels);
            mChannelPagerAdapter = new ChannelPagerAdapter(getChildFragmentManager(),channels);
            mViewpager.setAdapter(mChannelPagerAdapter);
            mViewpager.setOffscreenPageLimit(2);
            mViewpager.setCurrentItem(0,false);
            mSlidingTabLayout.setViewPager(mViewpager);

        }else {
            Toast("数据异常");
        }

    }

    @Subscriber
    private void updateChannel(NewChannelEvent event){
        if(event == null) return;
        if(event.selecteDatas != null && event.unSelecteDatas != null) {
            mSelectDatas = event.selecteDatas;
            mUnSelectDatas = event.unSelecteDatas;
            mChannelPagerAdapter.updateChannel(mSelectDatas);
            mSlidingTabLayout.notifyDataSetChanged();
            ChannelDao.saveChannels(event.allChannels);

            List<String> integers = new ArrayList<>();
            for (Channel channel : mSelectDatas) {
                integers.add(channel.getChannelName());
            }
            if (TextUtils.isEmpty(event.firstChannelName)) {
                if (!integers.contains(mSelectChannelName)) {
                    mSelectChannelName = mSelectDatas.get(mSelectIndex).getChannelName();
                    mViewpager.setCurrentItem(mSelectIndex, false);
                } else {

                    setViewPagerPosition(integers, mSelectChannelName);
                }
            } else {
                setViewPagerPosition(integers, event.firstChannelName);
            }
        }
    }

    @Subscriber
    private void selectChannelEvent(SelectChannelEvent selectChannelEvent){
        if(selectChannelEvent == null) return;
        List<String> integers = new ArrayList<>();
        for(Channel channel : mSelectDatas){
            integers.add(channel.getChannelName());
        }

        setViewPagerPosition(integers,selectChannelEvent.channelName);
    }

    /**
     * 设置当前选中页
     * @param integers
     * @param channelName
     */
    private void setViewPagerPosition(List<String> integers, String channelName) {

        if(TextUtils.isEmpty(channelName) || integers == null) return;
        for(int i = 0; i< integers.size(); i++){
            if(integers.get(i).equals(channelName)){

                mSelectChannelName = integers.get(i);
                mSelectIndex = i;
                break;

            }
        }
        mViewpager.postDelayed(new Runnable() {
            @Override
            public void run() {
                mViewpager.setCurrentItem(mSelectIndex,false);
            }
        },100);
    }

    @Override
    public void onDestroy() {
        EventBus.getDefault().unregister(this);
        super.onDestroy();
    }
}
