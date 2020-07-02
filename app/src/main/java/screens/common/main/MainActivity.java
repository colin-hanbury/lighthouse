package screens.common.main;

import android.os.Bundle;
import android.view.Window;

import androidx.viewpager2.widget.ViewPager2;

import hanbury.colin.networking.R;
import screens.common.controllers.BaseActivity;
import screens.common.navigation.PageAdapter;

public class MainActivity extends BaseActivity {

    private ViewPager2 mViewPager;
    private PageAdapter mPageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.pager);
        mPageAdapter = new PageAdapter(this);
        mViewPager.setAdapter(mPageAdapter);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        iBottomNavigationBar.registerListener(this);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
//        createBottomNavView();
    }
}