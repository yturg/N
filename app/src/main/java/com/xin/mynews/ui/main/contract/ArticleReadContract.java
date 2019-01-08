package com.xin.mynews.ui.main.contract;


import com.xin.mynews.bean.NewsArticleBean;
import com.xin.mynews.ui.base.BaseContract;

/**
 * author: zxj
 * date: 18/8/4
 */
public interface ArticleReadContract {

    interface View extends BaseContract.BaseView{

        void loadData(NewsArticleBean articleBean);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{

        void getData(String aid);

    }
}
