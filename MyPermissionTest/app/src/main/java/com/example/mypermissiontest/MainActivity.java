package com.example.mypermissiontest;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    String[] permission_list = {
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    String dir_path;
    Uri contentUri;
    ImageView image1;
    Button cameraBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 안드로이드 버전이 마시멜로우 이상일 때
            requestPermissions(permission_list, 0);
        } else {
            init();
        }

        image1 = findViewById(R.id.imageView);
        cameraBtn = findViewById(R.id.button);
        cameraBtn.setOnClickListener(v -> {
            StartCamera();
        });
    }


    // 사용자가 권한을 수락했는지, 거부했는지 체크하는 메소드
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        // 여러 개의 권한 수락여부 확인
        for (int a : grantResults) {
            if (a == PackageManager.PERMISSION_DENIED) return;   // 하나라도 거절을 했다면 종료
        }

        // 모두 수락이라면 init 함수 호출
        init();
    }

    public void StartCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        String file_name = "/temp" + System.currentTimeMillis() + ".jpg";
        String pic_path = dir_path + file_name;

        File file = new File(pic_path);

        contentUri = FileProvider.getUriForFile(this, "com.example.mypermissiontest.file_provider", file);

        intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);

        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            Bitmap bitmap = BitmapFactory.decodeFile(contentUri.getPath());
            image1.setImageBitmap(bitmap);
        }
    }

    public void init() {
        String temp_path = Environment.getExternalStorageDirectory().getAbsolutePath();

        dir_path = temp_path + "/Android/data/" + getPackageName();

        File file = new File(dir_path);
        if (!file.exists())
            file.mkdir();

        // 완료 후 내파일-내장메모리-Android-data에서 패키지 이름의 디렉토리 생성되어 있음
    }
}