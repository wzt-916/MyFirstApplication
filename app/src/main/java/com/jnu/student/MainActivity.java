package com.jnu.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.jnu.student.Fragment.ClockViewFragment;
import com.jnu.student.Fragment.GameViewFragment;
import com.jnu.student.Fragment.TencentMapFragment;
import com.jnu.student.Fragment.BookListFragment;
import com.jnu.student.Fragment.WebViewFragment;
import com.jnu.student.data.Book;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private final String[] tabHeaderStrings = {"图书", "地图", "新闻","时钟","游戏"};
    //BooksAdapter booksAdapter;
    ArrayList<Book> books;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //这是用布局实现
        setContentView(R.layout.activity_main);
        ViewPager2 viewPager = findViewById(R.id.view_pager);
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        FragmentAdapter fragmentAdapter = new FragmentAdapter(getSupportFragmentManager(),getLifecycle());
        viewPager.setAdapter(fragmentAdapter);
        new TabLayoutMediator(tabLayout,viewPager,
                ((tab, position) -> tab.setText(tabHeaderStrings[position]))).attach();

    }

    public static class FragmentAdapter extends FragmentStateAdapter {
        private static final int NUM_TABS = 5;

        public FragmentAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
            super(fragmentManager, lifecycle);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            // 根据位置返回对应的Fragment实例
            switch (position) {
                case 0:
                    return new BookListFragment();
                case 1:
                    return new TencentMapFragment();
                case 2:
                    return new WebViewFragment();
                case 3:
                    return new ClockViewFragment();
                case 4:
                    return new GameViewFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getItemCount() {
            return NUM_TABS;
        }
    }
}