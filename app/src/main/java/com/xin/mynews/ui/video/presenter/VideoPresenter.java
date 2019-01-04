package com.xin.mynews.ui.video.presenter;

import com.xin.mynews.bean.VideoChannelBean;
import com.xin.mynews.bean.VideoDetailBean;
import com.xin.mynews.net.NewsApi;
import com.xin.mynews.net.RxSchedulers;
import com.xin.mynews.ui.base.BasePresenter;
import com.xin.mynews.ui.video.contract.VideoContract;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * author: zxj
 * date: 18/8/12
 */
public class VideoPresenter extends BasePresenter<VideoContract.View> implements VideoContract.Presenter {

    NewsApi mNewsApi;

    @Inject
    public VideoPresenter(NewsApi newsApi){

        this.mNewsApi = newsApi;
    }
    @Override
    public void getVideoChannel() {
        mNewsApi.getVideoChannel()
                .compose(RxSchedulers.<List<VideoChannelBean>>applySchedulers())
                .compose(mView.<List<VideoChannelBean>>bindToLife())
                .subscribe(new Observer<List<VideoChannelBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<VideoChannelBean> videoChannelBeans) {
                            mView.loadVideoChannel(videoChannelBeans);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void getVideoDetails(final int page, String listType, String typeId) {
        mNewsApi.getVideoDetails(page,listType,typeId)
                .compose(RxSchedulers.<List<VideoDetailBean>>applySchedulers())
                .compose(mView.<List<VideoDetailBean>>bindToLife())
                .subscribe(new Observer<List<VideoDetailBean>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<VideoDetailBean> videoDetailBeans) {
                        if(page > 1){
                            mView.loadMoreVideoDetails(videoDetailBeans);
                        }else {
                            mView.loadVideoDetails(videoDetailBeans);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
