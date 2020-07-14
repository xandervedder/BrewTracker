package vedder.xander.brewtracker.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import vedder.xander.brewtracker.R;
import vedder.xander.brewtracker.adapter.ViewPagerAdapter;

public class MainActivity extends AppCompatActivity {

//    private static final int REQUEST_CODE = 1;
//    private ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(), 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), 0));
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_home_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_list_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_local_bar_24);
    }

//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        // Might need to be moved to a fragment instead, or maybe not? :)
//        if (requestCode == REQUEST_CODE) {
//            if (resultCode == RESULT_OK && data != null) {
//                Bundle bundle = data.getExtras();
//                this.recipes.add(new Recipe(
//                        LocalDate.now(),
//                        bundle.get("name").toString(),
//                        bundle.get("type").toString()
//                ));
//
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        updateRecyclerView();
//                    }
//                }, 500);
//            }
//        }
//    }
}
