package com.urangcoding.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.smarteist.autoimageslider.SliderViewAdapter;
import com.urangcoding.shop.R;
import com.urangcoding.shop.model.SliderImageModel;

import java.util.List;

public class SliderImageAdapter extends SliderViewAdapter<SliderImageAdapter.ViewRecHolder> {

    private List<SliderImageModel> listdata;
    private Context context;

    public SliderImageAdapter(List<SliderImageModel> listdata, Context context) {
        this.listdata = listdata;
        this.context = context;
    }

    @Override
    public ViewRecHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_image_slide, parent, false);
        return new SliderImageAdapter.ViewRecHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewRecHolder viewHolder, int position) {
        final SliderImageModel sliderImageModel = listdata.get(position);
        viewHolder.tv_auto_image_slider.setText(sliderImageModel.getTv_auto_image_slider());

        Glide.with(viewHolder.itemView)
                .load(sliderImageModel.getIv_auto_image_slider())
                .fitCenter()
                .into(viewHolder.iv_auto_image_slider);
    }

    @Override
    public int getCount() {
        return listdata.size();
    }

    public class ViewRecHolder extends ViewHolder {

        public ImageView iv_auto_image_slider;
        public TextView tv_auto_image_slider;

        public ViewRecHolder(View itemView) {
            super(itemView);

            iv_auto_image_slider = itemView.findViewById(R.id.iv_auto_image_slider);
            tv_auto_image_slider = itemView.findViewById(R.id.tv_auto_image_slider);
        }
    }
}
