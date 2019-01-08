package com.xin.mynews;


import com.xin.mynews.component.ApplicationComponent;
import com.xin.mynews.component.DaggerApplicationComponent;
import com.xin.mynews.module.ApplicationModule;
import com.xin.mynews.module.HttpModule;
import com.xin.mynews.utils.ContextUtils;

import org.litepal.LitePal;
import org.litepal.LitePalApplication;

import cn.bingoogolapple.swipebacklayout.BGASwipeBackManager;

/**
 * author: zxj
 * date: 18/7/2
 */

public class QiYueApplication extends LitePalApplication {

    private ApplicationComponent mApplicationComponent;
    private static QiYueApplication qiYueApplication;

    public static int width = 0;
    public static int height = 0;

    @Override
    public void onCreate() {
        super.onCreate();
        qiYueApplication = this;
        BGASwipeBackManager.getInstance().init(this);

        mApplicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .httpModule(new HttpModule())
                .build();

        LitePal.initialize(this);
        width = ContextUtils.getSreenWidth(QiYueApplication.getContext());
        height = ContextUtils.getSreenHeight(QiYueApplication.getContext());
    }

    public static QiYueApplication getInstance(){
        return qiYueApplication;
    }

    public ApplicationComponent getApplicationComponent() {
        return mApplicationComponent;
    }
}
