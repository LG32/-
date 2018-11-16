package com.example.administrator.newfridge.food;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.administrator.newfridge.R;

import java.util.List;


/**
 * @author RollingZ
 * @date
 */

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.FoodViewHolder> {

    private Context mCotext;
    private List<FoodModel> mList;
    private OnFoodClickListener onFoodClickListener;
    private boolean mIsStaggered = false;

    public interface OnFoodClickListener {
        void onClick(int position, FoodModel foodModel);
    }


    public FoodAdapter(Context mCotext, List<FoodModel> mList) {
        this.mCotext = mCotext;
        this.mList = mList;
    }

    @Override
    public FoodViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCotext).inflate(R.layout.item_foodfragment, parent, false);
        return new FoodViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FoodViewHolder holder, final int position) {
        final FoodModel foodModel = mList.get(position);
            //holder.tv_author.setText(foodModel.);
            holder.tv_title.setText(foodModel.getTitle());
            holder.tv_nature.setText(foodModel.getNature());
            holder.tv_time.setText(foodModel.getTime());
            holder.imageView.setBackgroundResource(foodModel.getImageID());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != onFoodClickListener) {
                    onFoodClickListener.onClick(position, foodModel);
                }
            }
        });
    }

    public void setOnFoodClickListener(OnFoodClickListener onFoodClickListener) {
        this.onFoodClickListener = onFoodClickListener;
    }

    public void setmIsStaggered(boolean mIsStaggered) {
        this.mIsStaggered = mIsStaggered;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setmList(List<FoodModel> mList) {
        this.mList = mList;
    }

    class FoodViewHolder extends RecyclerView.ViewHolder {
        LinearLayout linearLayout;
        ImageView imageView;
        TextView tv_title;
        TextView tv_nature;
        TextView tv_time;
        View view;

        public FoodViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            linearLayout = itemView.findViewById(R.id.shiyan);
            imageView = itemView.findViewById(R.id.iv_cover);
            tv_title =  itemView.findViewById(R.id.tv_title);
            tv_nature = itemView.findViewById(R.id.tv_nature);
            tv_time = itemView.findViewById(R.id.tv_time);
        }
    }
}
