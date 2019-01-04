package com.xin.mynews.component;

import com.xin.mynews.ui.jiandan.JianDanDetailFragment;
import com.xin.mynews.ui.news.ArticleReadActivity;
import com.xin.mynews.ui.news.DetailFragment;
import com.xin.mynews.ui.news.ImageBrowseActivity;
import com.xin.mynews.ui.news.NewsFragment;
import com.xin.mynews.ui.video.DetailsFragment;
import com.xin.mynews.ui.video.VideosFragment;

import dagger.Component;

/**
 * author: zxj
 * date: 18/7/2
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(NewsFragment newsFragment);

    void inject(DetailFragment detailFragment);

    void inject(ArticleReadActivity articleReadActivity);

    void inject(ImageBrowseActivity imageBrowseActivity);

    void inject(VideosFragment videosFragment);

    void inject(DetailsFragment detailsFragment);

    void inject(JianDanDetailFragment jianDanDetailFragment);
}
