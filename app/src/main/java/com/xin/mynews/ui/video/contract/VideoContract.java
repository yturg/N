package com.xin.mynews.ui.video.contract;

import com.xin.mynews.bean.VideoChannelBean;
import com.xin.mynews.bean.VideoDetailBean;
import com.xin.mynews.ui.base.BaseContract;

import java.util.List;

/**
 * author: zxj
 * date: 18/8/12
 */
public interface VideoContract {

    interface View extends BaseContract.BaseView{

        void loadVideoChannel(List<VideoChannelBean> videoChannelBeans);

        void loadVideoDetails(List<VideoDetailBean> videoDetailBeans);

        void loadMoreVideoDetails(List<VideoDetailBean> videoDetailBeans);
    }

    interface Presenter extends BaseContract.BasePresenter<View>{
        /**
         * 获取视频频道列表
          */
        void getVideoChannel();

        /**
         * 获取视频列表
         *
         * @param page      页码
         * @param listType  默认list
         * @param typeId    频道id
         */
        void getVideoDetails(int page, String listType, String typeId);

    }
}
