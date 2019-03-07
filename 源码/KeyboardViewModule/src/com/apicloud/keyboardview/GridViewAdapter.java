package com.apicloud.keyboardview;

import java.util.List;

import com.uzmap.pkg.uzcore.UZResourcesIDFinder;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Created by MQ on 2016/11/11.
 */

public class GridViewAdapter extends BaseAdapter {
    private List<DataBean> dataList;
    private int mHeight ;

    public GridViewAdapter(List<DataBean> datas,int height) {
        dataList = datas;
        mHeight = height;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View itemView, ViewGroup viewGroup) {
        ViewHolder mHolder;
        if (itemView == null) {
            mHolder = new ViewHolder();
            itemView = LayoutInflater.from(viewGroup.getContext()).inflate(UZResourcesIDFinder.getResLayoutID("keyboard_item_gridview"), viewGroup, false);
            mHolder.iv_img = (ImageView) itemView.findViewById(UZResourcesIDFinder.getResIdID("iv_img"));
            itemView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) itemView.getTag();
        }
        DataBean bean = dataList.get(position);
        if (bean != null) {
        	itemView.setId(bean.index);
        	mHolder.iv_img.setImageBitmap(bean.bitMap);
        }
        int itemHeight = mHeight;
        if(itemView.getLayoutParams() instanceof AbsListView.LayoutParams){
        	itemView.setLayoutParams(new AbsListView.LayoutParams(LayoutParams.MATCH_PARENT, itemHeight));
        }else{
        	itemView.setLayoutParams(new ViewGroup.LayoutParams(LayoutParams.MATCH_PARENT, itemHeight));
        }
        mHolder.iv_img.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, (int)(itemHeight*2.7/3)));
        return itemView;
    }

    private class ViewHolder {
        private ImageView iv_img;
    }
}
