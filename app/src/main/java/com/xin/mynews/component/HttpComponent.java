package com.xin.mynews.component;

import com.xin.mynews.ui.message.MessageDetailFragment;
import com.xin.mynews.ui.main.ArticleReadActivity;
import com.xin.mynews.ui.main.DetailFragment;
import com.xin.mynews.ui.main.HomeFragment;
import com.xin.mynews.ui.main.ImageBrowseActivity;
import com.xin.mynews.ui.main.MainFragment;
import com.xin.mynews.ui.video.DetailsFragment;
import com.xin.mynews.ui.video.VideosFragment;

import dagger.Component;

/**
 * author: zxj
 * date: 18/7/2
 */
@Component(dependencies = ApplicationComponent.class)
public interface HttpComponent {

    void inject(MainFragment mainFragment);

    void inject(HomeFragment homeFragment);

    void inject(DetailFragment detailFragment);

    void inject(ArticleReadActivity articleReadActivity);

    void inject(ImageBrowseActivity imageBrowseActivity);

    void inject(VideosFragment videosFragment);

    void inject(DetailsFragment detailsFragment);

    void inject(MessageDetailFragment messageDetailFragment);
}
