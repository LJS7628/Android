package com.example.layoutinflatorsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout linContainer;
    View sub;
    Button btnClick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        linContainer = findViewById(R.id.linContainer);

        // xml을 View 객체로 만들어주는 객체
        LayoutInflater inflater = (LayoutInflater)
                getSystemService(LAYOUT_INFLATER_SERVICE);

        // View객체를 생성하는 과정
        sub = inflater.inflate(R.layout.layout_sub,linContainer,
                true);
        btnClick = sub.findViewById(R.id.btnClick);
        btnClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,
                        "layout_sub 영역의 버튼", Toast.LENGTH_SHORT).show();
            }
        });
        


    }
}