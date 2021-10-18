package com.example.lesson31;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class SecondActivity extends AppCompatActivity {
    private EditText name, password;
    private ImageView avatar;
    private TextView editPhoto;
    private final int GALLERY_KEY = 1;
    private final int CAMERA_KEY = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        name = findViewById(R.id.et_name);
        password = findViewById(R.id.et_pass);
        avatar = findViewById(R.id.iv_photo);
        editPhoto = findViewById(R.id.tv_editPhoto);

        name.setText(getIntent().getStringExtra("key1"));

        password.setText(getIntent().getStringExtra("key2"));

        avatar.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, GALLERY_KEY);
        });

        editPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(intent, CAMERA_KEY);

        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_KEY && resultCode == RESULT_OK && data != null) {
            Glide.with(this).load(data.getData()).circleCrop().into(avatar);
        }

        if (requestCode == CAMERA_KEY && resultCode == RESULT_OK && data != null){
            Bundle extras = data.getExtras();
            Bitmap image = (Bitmap) extras.get("data");
            Glide.with(this).load(image).circleCrop().into(avatar);
        }
    }
}