package com.example.administrator.recyclerviewdemo;

import android.content.Context;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.bumptech.glide.Glide;
import com.example.swiperefreshrecyclerview.HHBaseRecyclerViewAdapter;
import com.example.swiperefreshrecyclerview.HHBaseViewHolder;

import java.util.List;
import java.util.Random;

/**
 * Created by Administrator on 2017/6/22 0022.
 */

public class MyAdapterDemo extends HHBaseRecyclerViewAdapter<DataModel> {
    private int width;
    private Context context;

    public MyAdapterDemo(Context context, List<DataModel> list) {
        super(context, list);
        this.context = context;
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        width = wm.getDefaultDisplay().getWidth();
    }

    @Override
    protected int getViewHolderLaoutId() {
        return R.layout.item_image;
    }

    @Override
    protected void bindViewHolderData(HHBaseViewHolder holder, int position) {
        //随机30至100的数，
        Random random = new Random();
        int i = random.nextInt(100 - 30) + 30;
        ImageView imageView = holder.getImageView(R.id.iv_imageview);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, width / 2 + i);
        imageView.setLayoutParams(layoutParams);
        Glide.with(context).load(getListData().get(position).getUrl()).crossFade().into(imageView);
    }
}
