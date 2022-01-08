package com.Tata.video.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.Tata.video.R;
import com.Tata.video.bean.VideoBean;
import com.Tata.video.custom.RefreshAdapter;
import com.Tata.video.glide.ImgLoader;

/**
 * Created by cxf on 2018/6/7.
 */

public class UserWorkAdapter extends RefreshAdapter<VideoBean> {

    public UserWorkAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh(mInflater.inflate(R.layout.item_list_user_work, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        ((Vh) vh).setData(mList.get(position), position);
    }

    public void removeItem(VideoBean videoBean) {
        int position = -1;
        for (int i = 0, size = mList.size(); i < size; i++) {
            if (mList.get(i).getId().equals(videoBean.getId())) {
                position = i;
                break;
            }
        }
        if (position >= 0 && position < mList.size()) {
            mList.remove(position);
            notifyItemRemoved(position);
        }
    }


    class Vh extends RecyclerView.ViewHolder {

        ImageView mThumb;
        TextView mTitle;
        VideoBean mBean;
        int mPosition;

        public Vh(View itemView) {
            super(itemView);
            mThumb = (ImageView) itemView.findViewById(R.id.thumb);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(mBean, mPosition);
                    }
                }
            });
        }

        void setData(VideoBean bean, int position) {
            mBean = bean;
            mPosition = position;
            Log.i("setDataa", " == = " + bean.getThumb());
            ImgLoader.display(bean.getThumb(), mThumb);
            mTitle.setText(bean.getTitle());
        }
    }
}
