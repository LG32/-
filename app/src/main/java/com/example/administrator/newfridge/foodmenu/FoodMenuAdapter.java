package com.example.administrator.newfridge.foodmenu;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.administrator.newfridge.R;

import java.util.List;


public class FoodMenuAdapter extends RecyclerView.Adapter<FoodMenuAdapter.FoodViewHolder> {

    private Context mContext;
    private List<FoodMenu> mList;
    private FoodMenuAdapter.OnFoodClickListener onFoodClickListener;

    public interface OnFoodClickListener {
        void onClick(int position, FoodMenu FoodMenu);
    }


    public FoodMenuAdapter(Context mContext, List<FoodMenu> mList) {
        this.mContext = mContext;
        this.mList = mList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_foodmenu, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {
        final FoodMenu foodMenu = mList.get(position);
            holder.tv_title.setText(foodMenu.getTitle());
            holder.imageView.setBackgroundResource(foodMenu.getImageView());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onFoodClickListener) {
                    onFoodClickListener.onClick(position, foodMenu);
                }
            }
        });
    }

    public void setOnBaseClickListener(FoodMenuAdapter.OnFoodClickListener onFoodClickListener) {
        this.onFoodClickListener = onFoodClickListener;
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmList(List<FoodMenu> mList) {
        this.mList = mList;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tv_title;
        View view;

        public FoodViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            imageView = (ImageView) itemView.findViewById(R.id.iv_cover1);
            tv_title = (TextView) itemView.findViewById(R.id.tv_title1);
        }
    }
}

