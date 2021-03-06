package com.Tata.video.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.Tata.video.R;
import com.Tata.video.bean.SystemMsgBean;
import com.Tata.video.custom.RefreshAdapter;

/**
 * Created by cxf on 2018/7/27.
 */

public class SystemMsgAdapter extends RefreshAdapter<SystemMsgBean> {

    private View.OnClickListener mOnClickListener;

    public SystemMsgAdapter(Context context) {
        super(context);
        mOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SystemMsgBean bean = (SystemMsgBean) v.getTag();
                if (mOnItemClickListener != null) {
                    mOnItemClickListener.onItemClick(bean, 0);
                }
            }
        };
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Vh(mInflater.inflate(R.layout.item_list_admin_msg_2, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder vh, int position) {
        ((Vh) vh).setData(mList.get(position), position);
    }

    class Vh extends RecyclerView.ViewHolder {
        TextView mTitle;
        TextView mDesc;
        TextView mTime;
        View mLine;

        public Vh(View itemView) {
            super(itemView);
            mTitle = (TextView) itemView.findViewById(R.id.title);
            mDesc = (TextView) itemView.findViewById(R.id.desc);
            mTime = (TextView) itemView.findViewById(R.id.time);
            mLine = itemView.findViewById(R.id.line);
            itemView.setOnClickListener(mOnClickListener);
        }

        void setData(SystemMsgBean bean, int position) {
            itemView.setTag(bean);
            mTitle.setText(bean.getTitle());
            mDesc.setText(bean.getContent());
            mTime.setText(bean.getAddtime());
            if (position == mList.size() - 1) {
                if (mLine.getVisibility() == View.VISIBLE) {
                    mLine.setVisibility(View.INVISIBLE);
                }
            } else {
                if (mLine.getVisibility() != View.VISIBLE) {
                    mLine.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
