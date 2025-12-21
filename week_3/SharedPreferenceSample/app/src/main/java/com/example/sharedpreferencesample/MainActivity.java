package com.example.sharedpreferencesample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    EditText edtInputID;
    EditText edtInputPW;
    Button btnLogin;

    // 소량의 데이터를 저장할 수 있도록 도와주는 객체 생성
    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputID = findViewById(R.id.edtInputID);
        edtInputPW = findViewById(R.id.edtInputPW);
        btnLogin = findViewById(R.id.btnLogin);

        sf = getSharedPreferences("member", MODE_PRIVATE);

        edtInputID.setText(sf.getString("id",""));
        edtInputPW.setText(sf.getString("pw", ""));


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 데이터 가져오기
                String id = edtInputID.getText().toString();
                String pw = edtInputPW.getText().toString();
                // sf에 데이터를 삽입할 수 있도록 환경 구성
                SharedPreferences.Editor editor = sf.edit();
                // sf에 데이터 삽입
                editor.putString("id", id);
                editor.putString("pw", pw);
                // sf의 변경 내용을 저장
                editor.commit();
            }
        });


    }
}