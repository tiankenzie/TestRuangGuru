package com.android.ruangguru.feature.category.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andexert.library.RippleView;
import com.android.ruangguru.R;
import com.android.ruangguru.base.helper.Screen;
import com.android.ruangguru.feature.category.model.CategoryModel;
import com.android.ruangguru.feature.games.view.GameActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.Target;

import java.util.List;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private Context context;
    private List<CategoryModel> categoryModelList;

    public CategoryAdapter(Context context, List<CategoryModel> categoryModelList) {
        this.categoryModelList = categoryModelList;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_activity, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final CategoryModel categoryModel = categoryModelList.get(position);

        ((CategoryViewHolder)holder).icon.setImageResource(categoryModel.getIcon());
        ((CategoryViewHolder)holder).title.setText(categoryModel.getTitle());
        ((CategoryViewHolder)holder).rippleView
                .setOnRippleCompleteListener(new RippleView.OnRippleCompleteListener() {
                    @Override
                    public void onComplete(RippleView rippleView) {
                        Intent intent = GameActivity.getCallingIntent((AppCompatActivity) context,
                                categoryModel.getCategory(),
                                categoryModel.getTitle());
                        context.startActivity(intent);
                    }
                });

        int width = Screen.getWidth(context)/2;
        Glide.with(context)
                .load(categoryModel.getBackground())
                .fitCenter()
                .override(width, Target.SIZE_ORIGINAL)
                .into(((CategoryViewHolder)holder).background);
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public static class CategoryViewHolder extends RecyclerView.ViewHolder {

        public ImageView background;
        public ImageView icon;
        public TextView title;
        public RippleView rippleView;

        public CategoryViewHolder(View view) {
            super(view);

            rippleView = view.findViewById(R.id.rippleView);
            background = view.findViewById(R.id.ivBackground);
            icon = view.findViewById(R.id.ivIcon);
            title = view.findViewById(R.id.tvtitle);
        }
    }
}
