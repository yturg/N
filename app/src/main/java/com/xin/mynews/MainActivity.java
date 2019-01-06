package com.xin.mynews;

import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;

import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.ui.base.BaseActivity;
import com.xin.mynews.ui.base.SupportFragment;
import com.xin.mynews.ui.jiandan.JanDanFragment;
import com.xin.mynews.ui.news.MainFragment;
import com.xin.mynews.ui.personal.PersonalFragment;
import com.xin.mynews.ui.video.VideosFragment;
import com.xin.mynews.utils.StatusBarUtil;
import com.xin.mynews.widget.BottomBar;
import com.xin.mynews.widget.BottomBarTab;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    @BindView(R.id.contentContainer)
    FrameLayout mContentContainer;
    @BindView(R.id.bottomBar)
    BottomBar mBottomBar;

    private SupportFragment[] mFragments = new SupportFragment[4];

    @Override
    public int getContentLayout() {
        return R.layout.activity_main;
    }

    @Override
    public boolean isSupportSwipeBack() {
        return false;
    }

    @Override
    public void initInjector(ApplicationComponent applicationComponent) {

    }

    @Override
    public void bindView(View view, Bundle savedInstanceState) {
        StatusBarUtil.setTranslucentForImageViewInFragment(MainActivity.this, 0, null);
        if(savedInstanceState == null){
            mFragments[0] = MainFragment.newInstance();
            mFragments[1] = VideosFragment.newInstance();
            mFragments[2] = JanDanFragment.newInstance();
            mFragments[3] = PersonalFragment.newInstance();

            getSupportDelegate().loadMultipleRootFragment(R.id.contentContainer,0,
                    mFragments[0],
                    mFragments[1],
                    mFragments[2],
                    mFragments[3]);
        }else{

            mFragments[0] = findFragment(MainFragment.class);
            mFragments[1] = findFragment(VideosFragment.class);
            mFragments[2] = findFragment(JanDanFragment.class);
            mFragments[3] = findFragment(PersonalFragment.class);
        }

        mBottomBar.addItem(new BottomBarTab(this,R.mipmap.ic_news,"新闻"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_video,"视频"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_jiandan,"煎蛋"))
                .addItem(new BottomBarTab(this,R.mipmap.ic_my,"我的"));

        mBottomBar.setOnTabSelectedListener(new BottomBar.OnTabSelectedListener() {
            @Override
            public void onTabSelected(int position, int prePosition) {
                getSupportDelegate().showHideFragment(mFragments[position],mFragments[prePosition]);
            }

            @Override
            public void onTabUnselected(int position) {

            }

            @Override
            public void onTabReselected(int position) {

            }
        });

    }



    @Override
    public void initData() {

    }

    @Override
    public void onRetry() {

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
