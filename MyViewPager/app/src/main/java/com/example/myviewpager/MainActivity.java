package com.example.myviewpager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pager = findViewById(R.id.pager);

        Fragment1 fragment1 = new Fragment1();
        Fragment2 fragment2 = new Fragment2();
        Fragment3 fragment3 = new Fragment3();

        MyPagerAdapter adapter = new MyPagerAdapter(getSupportFragmentManager());
        adapter.addItem(fragment1);
        adapter.addItem(fragment2);
        adapter.addItem(fragment3);

        pager.setAdapter(adapter);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(v -> {
            pager.setCurrentItem(1);
        });
    }

    // 페이저 구성을 위해 사용할 Adapter 만들기
    class MyPagerAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> items = new ArrayList<Fragment>();

        // 프래그먼트를 ArrayList에 담기 위한 함수
        public void addItem(Fragment item) { items.add(item); }

        // 생성자
        public MyPagerAdapter(FragmentManager manager) { super(manager); }

        // ViewPager를 통해 보여줄 프래그먼트 객체를 반환
        @NonNull
        @Override
        public Fragment getItem(int position) {
            return items.get(position);
        }

        // ViewPager를 통해 보여줄 항목의 개수
        @Override
        public int getCount() {
            return items.size();
        }
    }
}