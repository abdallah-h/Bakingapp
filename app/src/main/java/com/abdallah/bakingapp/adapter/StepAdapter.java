package com.abdallah.bakingapp.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import com.abdallah.bakingapp.fragments.RecipeStepSinglePageFragment;
import com.abdallah.bakingapp.models.Step;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by abdoh.
 */

public class StepAdapter extends FragmentStatePagerAdapter {

    private Context context;
    private List<Step> mSteps;


    public StepAdapter(FragmentManager fm, List<Step> steps, Context context) {
        super(fm);
        this.context = context;
        this.mSteps = new ArrayList<>();
        if (steps != null) {
            Log.d("PagerAdapter", "steps is not null" );
            this.mSteps.addAll(steps);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Step step = mSteps.get(position);
        String videoURL = step.getmVideoURL();
        String description = step.getmDescription();
        String imageUrl = step.getmThumbnailURL();
        Log.d("PagerAdapter", "" + description);
        return RecipeStepSinglePageFragment.newInstance(videoURL, description, imageUrl);
    }

    @Override
    public int getCount() {
        return mSteps.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        Step step = mSteps.get(position);
        return "Step: " + step.getId();
    }
}
