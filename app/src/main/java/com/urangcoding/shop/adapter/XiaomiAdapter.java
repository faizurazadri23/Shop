package com.urangcoding.shop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.urangcoding.shop.R;
import com.urangcoding.shop.model.ProdukModel;

import java.util.ArrayList;

public class XiaomiAdapter extends RecyclerView.Adapter<XiaomiAdapter.ViewHolderAdapter> {

    private ArrayList<ProdukModel> produkModelList;
    private Context context;

    public XiaomiAdapter(ArrayList<ProdukModel> produkModelList, Context context) {
        this.produkModelList = produkModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item_produk_home, parent, false);
        return new ViewHolderAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderAdapter holder, int position) {
        final ProdukModel produkModel = produkModelList.get(position);
        holder.tvNamaItem.setText(produkModel.getTvNamaItem());
        holder.tvHarga.setText(produkModel.getTvHarga());
        holder.tvSeller.setText(produkModel.getTvSeller());

        Glide.with(context).load(produkModel.getImgItem()).fitCenter().into(holder.imgItem);
    }

    @Override
    public int getItemCount() {
        return produkModelList.size();
    }

    public class ViewHolderAdapter extends RecyclerView.ViewHolder {

        private ImageView imgItem;
        public TextView tvNamaItem, tvHarga, tvSeller;

        public ViewHolderAdapter(@NonNull View itemView) {
            super(itemView);

            imgItem = itemView.findViewById(R.id.imgItem);
            tvNamaItem = itemView.findViewById(R.id.tvNamaItem);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvSeller = itemView.findViewById(R.id.tvSeller);
        }
    }
}
