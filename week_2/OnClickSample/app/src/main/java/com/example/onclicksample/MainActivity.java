package com.example.onclicksample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {// 2단계 implements View.OnClickListener {
    Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClick = findViewById(R.id.btnClick);
        // 1단계
        //EventButtonClick click = new EventButtonClick();
        //btnClick.setOnClickListener(click);

        // 3단계

        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });





        // 모든 뷰들은 이벤트를 감지할 수 있다.
        // 이벤트가 발생되었을때 어떻게 처리하자! 라는 코드는
        //      View.OnclickListener 인터페이스가 가지고 있음
        // 이벤트 처리를 하기 위해서는 그 뷰가 위와 같은
        //      인터페이스를 가지고 있어야 함
    }
}