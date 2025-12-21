package com.example.dutchpaysample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
/*
1. 숫자만 입력 가능하도록 xml 코드 수정 (글자크기는 28sp)
2. java에서 객체 참조
3. 더치페이 버튼을 눌렀을때 결과값이 잘 나올 수 있도록 작성

예시)
총 금액 : 10000원
총 인원 : 2명
''각각 5000원 내야 합니다''

예시)
총 금액 : 10000원
총 인원 : 3명
-> 나눠떨어지지 않을때에는 10000을 3으로 나눈 값에서 10의자리까지 버림
    (10000/3 -> 1666 ->(10의자리까지 버림) -> 1600
''각각 내야할 금액은 1600원''
''마지막 한명이 내야할 금액은 1800원''

 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}