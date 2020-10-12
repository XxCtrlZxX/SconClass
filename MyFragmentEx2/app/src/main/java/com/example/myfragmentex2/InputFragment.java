package com.example.myfragmentex2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class InputFragment extends Fragment {

    public static InputFragment newInstance() {
        return new InputFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // 프래그먼트가 관리할 뷰를 생성
        View view = inflater.inflate(R.layout.fragment_input, container, false);
        // 생성한 뷰 내부에 있는 객체의 주소값 추출
        Button button = view.findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // ResultFragment 가 나타날 수 있도록 액티비티에 요청
                MainActivity activity = (MainActivity) getActivity();
                activity.setFragment("result");
            }
        });
        return view;
    }
}