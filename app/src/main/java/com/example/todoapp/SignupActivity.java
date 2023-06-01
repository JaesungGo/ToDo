package com.example.todoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.todoapp.Utils.DataBaseHelper;

public class SignupActivity extends AppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;
    private Button btnSignUp;

    private DataBaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        etUsername = findViewById(R.id.et_id);
        etPassword = findViewById(R.id.et_pass);
        btnSignUp = findViewById(R.id.btn_register);

        dbHelper = new DataBaseHelper(this);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                if (validateInputs(username, password)) {
                    boolean success = dbHelper.insertUser(username, password);
                    if (success) {
                        Toast.makeText(SignupActivity.this, "회원 가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                        startActivity(intent);
                        finish();
                        // 회원 가입 성공 시 다음 화면으로 이동하거나 로직을 추가할 수 있습니다.
                    } else {
                        Toast.makeText(SignupActivity.this, "회원 가입 중 오류가 발생했습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean validateInputs(String username, String password) {
        if (username.isEmpty()) {
            etUsername.setError("아이디를 입력하세요.");
            return false;
        }

        if (password.isEmpty()) {
            etPassword.setError("비밀번호를 입력하세요.");
            return false;
        }

        return true;
    }
}