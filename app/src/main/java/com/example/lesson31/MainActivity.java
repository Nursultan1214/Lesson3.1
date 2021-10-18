package com.example.lesson31;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText username, password;
    Button btn_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.et_username);
        password = findViewById(R.id.et_password);
        btn_next = findViewById(R.id.btn_go);
        initListener();

    }
    private void initListener() {
        btn_next.setOnClickListener(v -> {
            if (password.getText().toString().length() > 6 && username.getText().toString().length() > 0) {
                Intent intent = new Intent(this, SecondActivity.class);
                intent.putExtra("key2", password.getText().toString());
                intent.putExtra("key1", username.getText().toString());
                startActivity(intent);
            } else if (password.getText().toString().length() < 6) {
                password.setError("Пароль должен быть больше 6 символов !");
            } else if (username.getText().toString().length() <= 0) {
                username.setError("Заполните пустое пространство !");
            }
        });
    }

}