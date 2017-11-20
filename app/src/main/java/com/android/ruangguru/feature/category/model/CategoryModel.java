package com.android.ruangguru.feature.category.model;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class CategoryModel {
    private int background;
    private int icon;
    private String title;
    private int category;

    public CategoryModel(int background, int icon, String title, int category) {
        this.background = background;
        this.icon = icon;
        this.title = title;
        this.category = category;
    }

    public int getCategory() {
        return category;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public int getBackground() {
        return background;
    }

    public void setBackground(int background) {
        this.background = background;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
