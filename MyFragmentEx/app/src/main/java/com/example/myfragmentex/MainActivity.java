package com.example.myfragmentex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    FirstFragment firstFragment = new FirstFragment();
    SecondFragment secondFragment = new SecondFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btn1Method(View v) {
        // 프래그먼트를 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();
        // 프래그먼트 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, firstFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    public void btn2Method(View v) {
        // 프래그먼트를 관리하는 객체 추출
        FragmentManager manager = getSupportFragmentManager();
        // 프래그먼트 변경을 관리하는 객체 추출
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, secondFragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}