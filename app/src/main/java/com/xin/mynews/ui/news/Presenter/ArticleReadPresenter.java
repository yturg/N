package com.xin.mynews.ui.news.Presenter;


import com.xin.mynews.bean.NewsArticleBean;
import com.xin.mynews.net.NewsApi;
import com.xin.mynews.net.RxSchedulers;
import com.xin.mynews.ui.base.BasePresenter;
import com.xin.mynews.ui.news.contract.ArticleReadContract;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: zxj
 * date: 18/8/4
 */
public class ArticleReadPresenter extends BasePresenter<ArticleReadContract.View> implements ArticleReadContract.Presenter {

    NewsApi mNewsApi;

    @Inject
    public ArticleReadPresenter(NewsApi newsApi){
        this.mNewsApi = newsApi;
    }

    @Override
    public void getData(String aid) {

        mNewsApi.getNewsArticle(aid)
                .compose(RxSchedulers.<NewsArticleBean>applySchedulers())
                .compose(mView.<NewsArticleBean>bindToLife())
                .subscribe(new Observer<NewsArticleBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(NewsArticleBean articleBean) {
                        mView.loadData(articleBean);
                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showFaild();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
