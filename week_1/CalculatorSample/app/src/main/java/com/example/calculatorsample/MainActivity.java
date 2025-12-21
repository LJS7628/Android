package com.example.calculatorsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtInputData1;
    EditText edtInputData2;
    Button btnAdd;
    TextView tvResult;
    Button btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputData1 = findViewById(R.id.edtInputData1);
        edtInputData2 = findViewById(R.id.edtInputData2);
        btnAdd = findViewById(R.id.btnAdd);
        tvResult = findViewById(R.id.tvResult);
        btnDivide = findViewById(R.id.btnDivide);


        btnDivide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtInputData1.getText().toString().equals("")||
                        edtInputData2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,
                            "정수를 모두 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 1. EditText에서 문자열을 가져온 후 정수로 변경한다.
                int num1 = Integer.parseInt(edtInputData1.getText().toString());
                int num2 = Integer.parseInt(edtInputData2.getText().toString());

                if(num2 == 0){
                    Toast.makeText(MainActivity.this,
                            "0으로 나눌 수 없습니다",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 2. 연산을 진행한다.
                double result = (double)num1/num2;

                // 3. 연산 결과를 보여준다.
                tvResult.setText("결과값 : " + result);
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 사용자가 아무것도 입력하지 않았을때 실행이 되지 않도록
                // 조건식을 작성한다.
                if(edtInputData1.getText().toString().equals("")||
                            edtInputData2.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,
                            "정수를 모두 입력하세요",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                // 1. EditText에서 문자열을 가져온 후 정수로 변경한다.
                int num1 = Integer.parseInt(edtInputData1.getText().toString());
                int num2 = Integer.parseInt(edtInputData2.getText().toString());

                // 2. 연산을 진행한다.
                int result = num1+num2;

                // 3. 연산 결과를 보여준다.
                tvResult.setText("결과값 : " + result);
            }
        });
    }
}