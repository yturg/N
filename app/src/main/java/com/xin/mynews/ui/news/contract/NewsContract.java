package com.xin.mynews.ui.news.contract;

import com.xin.mynews.bean.Channel;
import com.xin.mynews.ui.base.BaseContract;

import java.util.List;

/**
 * author: zxj
 * date: 18/7/4
 */
public interface NewsContract {

    interface View extends BaseContract.BaseView{

        void loadData(List<Channel> channels, List<Channel> otherChannels);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        /**
         * 初始化频道
         */

        void getChannel();
    }
}
