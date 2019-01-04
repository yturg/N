package com.xin.mynews.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.xin.mynews.R;
import com.xin.mynews.bean.JdDetailBean;
import com.xin.mynews.utils.DateUtil;
import com.xin.mynews.utils.ShareUtils;

import java.util.List;

/**
 * author: zxj
 * date: 18/8/25
 */
public class JokesAdapter extends BaseQuickAdapter<JdDetailBean.CommentsBean,BaseViewHolder> {
    public JokesAdapter(@Nullable List<JdDetailBean.CommentsBean> data) {
        super(R.layout.item_joke, data);
    }

    @Override
    protected void convert(BaseViewHolder viewHolder, final JdDetailBean.CommentsBean itemBean) {

        viewHolder.setText(R.id.tv_author, itemBean.getComment_author())
                .setText(R.id.tv_time, DateUtil.getTimestampString(DateUtil.string2Date(itemBean.getComment_date(), "yyyy-MM-dd HH:mm:ss")))
                .setText(R.id.tv_content, itemBean.getText_content())
                .setText(R.id.tv_like, itemBean.getVote_negative())
                .setText(R.id.tv_unlike, itemBean.getVote_positive())
                .setText(R.id.tv_comment_count, itemBean.getSub_comment_count());

        viewHolder.getView(R.id.img_share).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShareUtils.shareText(mContext, itemBean.getText_content());
            }
        });
    }
}
