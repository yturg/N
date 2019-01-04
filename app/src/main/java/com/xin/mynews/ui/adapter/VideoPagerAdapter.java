package com.xin.mynews.ui.adapter;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.xin.mynews.bean.VideoChannelBean;
import com.xin.mynews.ui.base.BaseFragment;
import com.xin.mynews.ui.video.DetailsFragment;


/**
 * author: zxj
 * date: 18/8/12
 */
public class VideoPagerAdapter extends FragmentStatePagerAdapter {

    private VideoChannelBean mVideoChannelBean;

    public VideoPagerAdapter(FragmentManager fm, VideoChannelBean videoChannelBean) {
        super(fm);
        this.mVideoChannelBean = videoChannelBean;
    }

    @Override
    public BaseFragment getItem(int position) {
        return DetailsFragment.newInstance("clientvideo_" + mVideoChannelBean.getTypes().get(position).getId());
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mVideoChannelBean.getTypes().get(position).getName();
    }

    @Override
    public int getCount() {
        return mVideoChannelBean != null ? mVideoChannelBean.getTypes().size() : 0;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
