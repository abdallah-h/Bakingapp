package com.abdallah.bakingapp.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.abdallah.bakingapp.R;
import com.abdallah.bakingapp.widgets.RecipeWidgetUpdateService;
import com.abdallah.bakingapp.models.Recipe;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by abdoh.
 */

public class RecipeListAdapter extends RecyclerView.Adapter<RecipeListAdapter.ViewHolder> {

    private ArrayList<Recipe> mRecipe;
    private int id;
    private int recipeId;
    private String mRecipeName;
    private Context context;
    OnStepClickListener onStepClickListener;

    private int lastSelectedPosition = -1;


    public interface OnStepClickListener {

        void onStepSelected(View v, int position, int id);
    }

    public RecipeListAdapter(ArrayList<Recipe> mRecipe, Context context, OnStepClickListener listener) {
        this.mRecipe = mRecipe;
        this.onStepClickListener = listener;
        this.context = context;
    }

    @Override
    public RecipeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recipe_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeListAdapter.ViewHolder holder, final int position) {
        recipeId = mRecipe.get(position).getId();
        mRecipeName = mRecipe.get(position).getName();
        String imageUrl = mRecipe.get(position).getmImageUrl();

        Glide.with(context)
                .load(imageUrl)
                .apply(new RequestOptions().placeholder(R.drawable.placeholder))
                .into(holder.recipeIcon);
        holder.recipeName.setText(mRecipeName);
        holder.recipeServings.setText(String.valueOf(mRecipe.get(position).getServings()));

        holder.recipeCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                id = mRecipe.get(position).getId();
                onStepClickListener.onStepSelected(view, position, id);
            }
        });

        holder.selectedRecipe.setChecked(lastSelectedPosition == position);
    }

    @Override
    public int getItemCount() {
        return mRecipe.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.recipe_name) TextView recipeName;
        @BindView(R.id.recipe_servings) TextView recipeServings;
        @BindView(R.id.recipe_cardview) CardView recipeCardView;
        @BindView(R.id.radio_button) RadioButton selectedRecipe;
        @BindView(R.id.recipe_icon)
        ImageView recipeIcon;
        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            selectedRecipe.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    lastSelectedPosition = getAdapterPosition();
                    RecipeWidgetUpdateService.startActionUpdateRecipe(context, lastSelectedPosition + 1, mRecipe.get(lastSelectedPosition).getName(), mRecipe);
                    notifyDataSetChanged();
                }
            });
        }
    }
}
