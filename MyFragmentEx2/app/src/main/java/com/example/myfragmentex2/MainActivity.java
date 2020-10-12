package com.example.myfragmentex2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.renderscript.ScriptGroup;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setFragment("input");
    }

    // 프래그먼트를 관리하는 메소드
    public void setFragment(String name) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction tran = manager.beginTransaction();

        switch (name) {
            case "input":
                tran.replace(R.id.container, InputFragment.newInstance());
                // tran.addToBackStack(null);
                break;
            case "result":
                tran.replace(R.id.container, ResultFragment.newInstance());
                tran.addToBackStack(null);
                break;
        }
        tran.commit();
    }
}