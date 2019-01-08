package com.xin.mynews.ui.message;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.xin.mynews.R;
import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.net.JianDanApi;
import com.xin.mynews.ui.adapter.BroedImageAdapter;
import com.xin.mynews.ui.adapter.FreshNewsAdapter;
import com.xin.mynews.ui.adapter.JokesAdapter;
import com.xin.mynews.ui.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * author: zxj
 * date: 18/7/2
 */
public class MessageFragment extends BaseFragment {

    @BindView(R.id.tablayout)
    TabLayout mTablayout;
    @BindView(R.id.viewpager)
    ViewPager mViewpager;

    private MessagePagerAdapter mMessagePagerAdapter;

    public static MessageFragment newInstance() {
        Bundle args = new Bundle();
        MessageFragment fragment = new MessageFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getContentLayout() {
        return R.layout.fragment_message_layout;
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {

    }

    @Override
    public void initData() {
        List<String> titles = new ArrayList<>();
        titles.add("新鲜事");
        titles.add("无聊图");
        titles.add("妹子图");
        titles.add("搞笑段子");

        mMessagePagerAdapter = new MessagePagerAdapter(getChildFragmentManager(),titles);
        mViewpager.setAdapter(mMessagePagerAdapter);
        mViewpager.setOffscreenPageLimit(1);
        mViewpager.setCurrentItem(0,false);
        mTablayout.setupWithViewPager(mViewpager,true);

    }

    public class MessagePagerAdapter extends FragmentStatePagerAdapter {
        private List<String> mTitle;

        public MessagePagerAdapter(FragmentManager fm, List<String> titles){
            super(fm);
            this.mTitle = titles;
        }

        @Override
        public BaseFragment getItem(int position) {
            switch (position){
                case 0:
                    return MessageDetailFragment.newInstance(JianDanApi.TYPE_FRESH,new FreshNewsAdapter(getActivity(),null));
                case 1:
                    return MessageDetailFragment.newInstance(JianDanApi.TYPE_BORED,new BroedImageAdapter(getActivity(),null));
                case 2:
                    return MessageDetailFragment.newInstance(JianDanApi.TYPE_GIRLS,new BroedImageAdapter(getActivity(),null));
                case 3:
                    return MessageDetailFragment.newInstance(JianDanApi.TYPE_Duan,new JokesAdapter(null));
            }
            return null;
        }

        @Override
        public int getCount() {
            return mTitle.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle.get(position);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }
    }

}
