package com.example.updowngame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView tvStatus;
    TextView tvUpDown;
    EditText edtInputData;
    Button btnChoose;

    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvStatus = findViewById(R.id.tvStatus);
        tvUpDown = findViewById(R.id.tvUpDown);
        edtInputData = findViewById(R.id.edtInputData);
        btnChoose = findViewById(R.id.btnChoose);

        // 난수를 추출하는 클래스
        Random random = new Random();
        // 정수형태의 난수를 추출하는 메서드
        // 30~50 중 3의 배수
        //int number = (random.nextInt(6)+1)*3+30;
        int number = random.nextInt(21)+30;
        OnClickTest test = new OnClickTest();
        btnChoose.setOnClickListener(test);

        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 1. 사용자의 입력값을 받아옴
                int input = Integer.parseInt(edtInputData.getText().toString());

                if(input >number){
                    // 2. 입력값보다 랜덤값이 작으면 DOWN
                    tvUpDown.setText("DOWN!!!");
                }else if(input <number){
                    // 3. 입력값보다 랜덤값이 크면 UP
                    tvUpDown.setText("UP!!!");
                }else{
                    // 4. 같으면 정답입니다!
                    tvUpDown.setText("정답!!!");
                }
                // + 시도횟수는 1씩 증가할 수 있도록 작성성
                count++;
                tvStatus.setText("시도 횟수 : "+count);
           }
        });


    }






}