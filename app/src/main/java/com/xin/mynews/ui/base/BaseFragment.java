package com.xin.mynews.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.xin.mynews.DavyNewsApplication;
import com.xin.mynews.R;
import com.xin.mynews.ui.inter.IBase;
import com.xin.mynews.utils.Toast;
import com.xin.mynews.widget.MultiStateView;
import com.xin.mynews.widget.SimpleMultiStateView;
import com.trello.rxlifecycle2.LifecycleTransformer;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * author: zxj
 * date: 18/7/4
 */
public abstract class BaseFragment<T1 extends BaseContract.BasePresenter> extends SupportFragment implements IBase,BaseContract.BaseView{

    protected View mRootView;
    Unbinder unbinder;

    @Nullable
    @Inject
    protected T1 mPresenter;

    @Nullable
    @BindView(R.id.SimpleMultiStateView)
    SimpleMultiStateView mSimpleMultiStateView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(mRootView != null){
           ViewGroup parent = (ViewGroup) mRootView.getParent();
           if(parent !=null){

               parent.removeView(mRootView);
           }
        }else{
            mRootView = createView(inflater,container,savedInstanceState);
        }

        return mRootView;
    }

    @Override
    public View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(getContentLayout(),container,false);
        unbinder = ButterKnife.bind(this,view);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initInjector(DavyNewsApplication.getInstance().getApplicationComponent());
        attachView();
        bindView(view,savedInstanceState);
        initStateView();
    }

    @Override
    public void onLazyInitView(@Nullable Bundle savedInstanceState) {
        super.onLazyInitView(savedInstanceState);
        initData();
    }

    @Override
    public void onSupportVisible() {
        super.onSupportVisible();
    }

    @Nullable
    @Override
    public View getView() {
        return mRootView;
    }

    private void attachView() {
        if(mPresenter != null){
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onRetry() {

    }

    private void initStateView() {
        if(mSimpleMultiStateView == null) return;
        mSimpleMultiStateView.setEmptyResource(R.layout.view_empty)
                .setRetryResource(R.layout.view_retry)
                .setLoadingResource(R.layout.view_loading)
                .setNoNetResource(R.layout.view_nonet)
                .build()
                .setonReLoadlistener(new MultiStateView.onReLoadlistener() {
                    @Override
                    public void onReload() {
                        onRetry();
                    }
                });
    }
    @Override
    public void showLoading() {
        if(mSimpleMultiStateView!=null){
            mSimpleMultiStateView.showLoadingView();
        }

    }

    @Override
    public void showSuccess() {
        if(mSimpleMultiStateView!=null){
            mSimpleMultiStateView.showContent();
        }

    }

    @Override
    public void showFaild() {
        if(mSimpleMultiStateView!=null){
            mSimpleMultiStateView.showErrorView();
        }
    }

    @Override
    public void showNoNet() {
        if(mSimpleMultiStateView!=null){
            mSimpleMultiStateView.showNoNetView();
        }
    }

    protected void Toast(String string){
        Toast.showShort(DavyNewsApplication.getContext(),string);
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return this.<T>bindToLifecycle();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        if(mPresenter != null){
            mPresenter.detachView();
        }
    }
}
