package com.xin.mynews.ui.message.contract;

import com.xin.mynews.bean.FreshNewsBean;
import com.xin.mynews.bean.JdDetailBean;
import com.xin.mynews.ui.base.BaseContract;

/**
 * author: zxj
 * date: 18/8/15
 */
public interface MessageContract {

    interface View extends BaseContract.BaseView{

        void loadFreshNews(FreshNewsBean freshNewsBean);

        void loadMoreFreshNews(FreshNewsBean freshNewsBean);

        void loadDetailData(String type, JdDetailBean jdDetailBean);

        void loadMoreDetailData(String type, JdDetailBean jdDetailBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getData(String type, int page);

        void getFreshNews(int page);

        void getDetailData(String type, int page);
    }
}
