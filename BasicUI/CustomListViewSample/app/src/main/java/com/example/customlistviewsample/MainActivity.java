package com.example.customlistviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

/*
1. 리스트뷰의 아이템이 어떤 정보를 갖고 있을지 고민
2. 아이템에 맞는 사용자 정의 데이터타입(클래스타입)을 구현
3. 아이템이 어떻게 보여질지에 대한 layout(xml)을 구성
4. 아이템에 맞는 Adapter(extends BaseAdapter)를 구현
5. 생성된 아이템들을 ListView에 집어넣는다. (setAdapter)

 */
public class MainActivity extends AppCompatActivity {
    ListView lvBoard;
    ProfileAdapter profileAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBoard = findViewById(R.id.lvBoard);
        profileAdapter = new ProfileAdapter();

        profileAdapter.addProfileItemData(
                ContextCompat.getDrawable(MainActivity.this,
                        R.mipmap.ic_launcher_round),
                "sunghyun",
                "kg itbank android class"
        );
        profileAdapter.addProfileItemData(
                ContextCompat.getDrawable(MainActivity.this,
                        R.mipmap.ic_launcher_round),
                "sunghyun",
                "kg itbank android class"
        );
        profileAdapter.addProfileItemData(
                ContextCompat.getDrawable(MainActivity.this,
                        R.mipmap.ic_launcher_round),
                "sunghyun",
                "kg itbank android class"
        );
        lvBoard.setAdapter(profileAdapter);




    }
}