package com.abdallah.bakingapp.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import com.abdallah.bakingapp.R;
import com.abdallah.bakingapp.fragments.RecipeFragment;
import com.abdallah.bakingapp.fragments.StepsListFragment;
import butterknife.BindBool;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by abdoh.
 */

public class RecipeStepsActivity extends AppCompatActivity {

    private String sRecipe;
    private static final String EXTRA_RECIPE_NAME = "current_recipe";
    private static final String EXTRA_RECIPE_ID = "id";
    private static final int DEFAULT_RECIPE_ID = 1;


    @BindBool(R.bool.two_pane_mode)
    boolean twoPaneMode;
    Unbinder unbinder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_steps);
        unbinder = ButterKnife.bind(this);
        if (getIntent() != null) {
            int id = getIntent().getIntExtra(EXTRA_RECIPE_ID,DEFAULT_RECIPE_ID);
            sRecipe = getIntent().getExtras().getString("recipe");
            String title = getIntent().getExtras().getString(EXTRA_RECIPE_NAME);
            setTitle(title);
        }

        if (twoPaneMode) {
            init(savedInstanceState, sRecipe);
        } else {
            init(savedInstanceState, sRecipe);
        }
    }

    private void init(Bundle savedInstanceState, String recipe) {
        if (savedInstanceState == null) {
            StepsListFragment stepsListFragment = StepsListFragment.newInstance(recipe, twoPaneMode);
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, stepsListFragment, RecipeFragment.class.getSimpleName());
            fragmentTransaction.commit();
        }
    }

}
