package com.xin.mynews.component;


import com.xin.mynews.QiYueApplication;
import com.xin.mynews.module.ApplicationModule;
import com.xin.mynews.module.HttpModule;
import com.xin.mynews.net.JianDanApi;
import com.xin.mynews.net.NewsApi;

import dagger.Component;

/**
 * author: zxj
 * date: 18/7/2
 */
@Component(modules = {ApplicationModule.class, HttpModule.class})
public interface ApplicationComponent {

    QiYueApplication getApplication();

    NewsApi getNewsApi();

    JianDanApi getJianDanApi();
}
