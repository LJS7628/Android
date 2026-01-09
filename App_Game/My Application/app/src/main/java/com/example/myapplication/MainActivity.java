package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    // 멤버 변수 영역

    // xml에 생성한 뷰들을 모두 객체화
    TextView tvShow;
    EditText edtInputData;
    Button btnClick;

    // 메서드 영역
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰객체 참조
        tvShow = findViewById(R.id.tvShow);
        edtInputData = findViewById(R.id.edtInputData);
        btnClick = findViewById(R.id.btnClick);

        // 버튼이 클릭되었을때에 대한 이벤트 처리 - 익명클래스구조
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 1. EditText에 있는 문자열을 가져옴
                String data = edtInputData.getText().toString();
                // 2. 가져온 문자열을 TextView에 띄워줌
                tvShow.setText(data);
            }
        });

    }
}