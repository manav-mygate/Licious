package com.mygate.licious.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mygate.licious.AppController;
import com.mygate.licious.R;
import com.mygate.licious.model.DataToList;

import java.util.List;

public class GridAdapter extends RecyclerView.Adapter<GridAdapter.MyViewHolder> {
    List<DataToList> dataToLists;
    Context context;

    public GridAdapter(Context context, List<DataToList> data){
        this.dataToLists=data;
        this.context=context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.item_food, parent, false);
        return (new MyViewHolder(v));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
          DataToList dataToList= dataToLists.get(position);
          holder.itemName.setText(dataToList.getItemName());
          holder.itemPrice.setText("\u20B9"+dataToList.getPrice());
          holder.itemWeight.setText(dataToList.getWeigth());
          Glide.with(AppController.getContext()).load(dataToList.getImageUrl()).into(holder.itemImageUrl);


    }

    @Override
    public int getItemCount() {
        return dataToLists.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder
    {
        public TextView itemName;
        public TextView itemWeight;
        public TextView itemPrice;
        public ImageView itemImageUrl;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemName=itemView.findViewById(R.id.tvItemName);
            itemWeight=itemView.findViewById(R.id.tvItemWeight);
            itemPrice= itemView.findViewById(R.id.tvPrice);
            itemImageUrl=itemView.findViewById(R.id.imvItem);
        }
    }

}
