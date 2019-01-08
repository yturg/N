package com.xin.mynews.module;

import com.xin.mynews.QiYueApplication;
import com.xin.mynews.net.ApiConstants;
import com.xin.mynews.net.JianDanApi;
import com.xin.mynews.net.JianDanApiService;
import com.xin.mynews.net.NewsApi;
import com.xin.mynews.net.NewsApiService;
import com.xin.mynews.net.RetrofitConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * author: zxj
 * date: 18/7/2
 */
@Module
public class HttpModule {

    @Provides
    OkHttpClient.Builder provideOkhttpClient(){

        Cache cache = new Cache(new File(QiYueApplication.getContext().getCacheDir(),"NewHttpCache"),1024 * 1024 * 100);
        return new OkHttpClient.Builder().cache(cache)
                .retryOnConnectionFailure(true)
                .addInterceptor(RetrofitConfig.sLoggingInterceptor)
                .addInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .addNetworkInterceptor(RetrofitConfig.sRewriteCacheControlInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS);
    }

    @Provides
    NewsApi provideNewApi(OkHttpClient.Builder builder){

        builder.addInterceptor(RetrofitConfig.sQueryParameterInterceptor);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return NewsApi.getInstance(retrofitBuilder
                        .baseUrl(ApiConstants.fengApi)
                        .build().create(NewsApiService.class));
    }

    @Provides
    JianDanApi provideJianDanApi(OkHttpClient.Builder builder){

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(builder.build());

        return JianDanApi.getInstance(retrofitBuilder.baseUrl(ApiConstants.sJanDanApi)
                .build().create(JianDanApiService.class)
        );
    }

}
