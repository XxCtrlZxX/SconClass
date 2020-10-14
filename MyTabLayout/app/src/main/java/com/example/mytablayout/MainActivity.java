package com.example.mytablayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Toolbar toolbar;
    TabLayout tabLayout;
    ViewPager pager;

    ArrayList<Fragment> frag_list = new ArrayList<>();
    ArrayList<String> title_list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 액션바 대신 툴바 사용
        toolbar = findViewById(R.id.toobar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.tabs);
        pager = findViewById(R.id.pager);

        // 프래그먼트 객체, 탭title 만들어서 ArrayList에 담기
        for (int i = 0; i < 10; i++) {
            BlankFragment sub = new BlankFragment();
            sub.contents = (i + 1) + "번째 프래그먼트";

            frag_list.add(sub);
            title_list.add("TAB " + (i + 1));
        }

        // 뷰페이저 설정
        FragmentManager manager = getSupportFragmentManager();
        PagerAdapter adapter = new PagerAdapter(manager);
        pager.setAdapter(adapter);
        tabLayout.setupWithViewPager(pager);

        tabLayout.setTabTextColors(Color.WHITE, R.color.purple_500);   // (선택 안 된 탭, 선택된 탭)
    }

    // 뷰페이저 구성을 위해 어댑터 생성
    class PagerAdapter extends FragmentPagerAdapter {

        public PagerAdapter(FragmentManager fm) { super(fm); }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return frag_list.get(position);
        }

        @Override
        public int getCount() {
            return frag_list.size();
        }

        // 탭에 표시될 문자열 반환
            // (Tab 버튼의 문자열로 사용됨)
        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return title_list.get(position);
        }
    }
}