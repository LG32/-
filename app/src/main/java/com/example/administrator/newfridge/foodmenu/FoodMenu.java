package com.example.administrator.newfridge.foodmenu;

/**
 * Created by Administrator on 2018/4/18.
 */

public class FoodMenu {
    private int ImageView;
    private String title;

    public FoodMenu(int imageView, String title) {
        ImageView = imageView;
        this.title = title;
    }

    public int getImageView() {
        return ImageView;
    }

    public void setImageView(int imageView) {
        ImageView = imageView;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
