package com.xin.mynews.net;


import com.xin.mynews.bean.FreshNewsArticleBean;
import com.xin.mynews.bean.FreshNewsBean;
import com.xin.mynews.bean.JdDetailBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.Url;

/**
 * author: zxj
 * date: 18/8/15
 */
public interface JianDanApiService {

    @GET
    Observable<FreshNewsBean> getFreshNews(@Url String url,
                                           @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                           @Query("include") String include,
                                           @Query("page") int page,
                                           @Query("custom_fields") String custom_fields,
                                           @Query("dev") String dev
    );

    @GET
    Observable<FreshNewsArticleBean> getFreshNewsArticle(@Url String url,
                                                         @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                                         @Query("include") String include,
                                                         @Query("id") int id
    );

    @GET
    Observable<JdDetailBean> getJianDanDetails(@Url String url,
                                               @Query("oxwlxojflwblxbsapi") String oxwlxojflwblxbsapi,
                                               @Query("page") int page
    );
}
