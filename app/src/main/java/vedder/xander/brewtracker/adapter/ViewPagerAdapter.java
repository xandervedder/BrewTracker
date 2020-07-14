package vedder.xander.brewtracker.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vedder.xander.brewtracker.ui.fragment.BrewFragment;
import vedder.xander.brewtracker.ui.fragment.HomeFragment;
import vedder.xander.brewtracker.ui.fragment.RecipeFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {

    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: return new HomeFragment();
            case 1: return new RecipeFragment();
            case 2: return new BrewFragment();
            default: return new Fragment();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
