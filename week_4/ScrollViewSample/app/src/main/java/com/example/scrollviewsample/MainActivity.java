package com.example.scrollviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.scrollviewsample.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                MainActivity.this,
                R.layout.activity_main);

        // 자바에서 View 객체를 생성하는 방법

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data = binding.edtInpuData.getText().toString();

                // 자바에서 텍스트뷰 만들기
                TextView textView = new TextView(MainActivity.this);
                textView.setText(data);
                textView.setTextSize(30);
                binding.linBoard.addView(textView);
            }
        });


    }
}