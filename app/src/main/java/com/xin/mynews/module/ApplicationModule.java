package com.xin.mynews.module;

import android.content.Context;

import com.xin.mynews.DavyNewsApplication;

import dagger.Module;
import dagger.Provides;

/**
 * author: zxj
 * date: 2018-6-29
 */
@Module
public class ApplicationModule {

    private Context mContext;

    public ApplicationModule(Context context){

        this.mContext = context;
    }

   @Provides
   DavyNewsApplication provideApplication(){

        return (DavyNewsApplication) mContext.getApplicationContext();
   }

   @Provides
   Context provideContext(){

        return mContext;
   }
}
