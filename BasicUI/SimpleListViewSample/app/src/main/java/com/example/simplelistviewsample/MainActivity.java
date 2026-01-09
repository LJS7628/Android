package com.example.simplelistviewsample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.simplelistviewsample.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    ArrayList<String> dataset;
    ArrayAdapter<String> arrayAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(
                MainActivity.this,
                R.layout.activity_main
        );
        dataset = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1,
                dataset);

        binding.lvBoard.setAdapter(arrayAdapter);

        // ArrayList 데이터 추가
        dataset.add("hello");
        dataset.add("goodbye");
        dataset.add("good afternoon");
        // ArrayList 데이터 삭제
        dataset.remove(0);
        dataset.remove("goodbye");
        // ArrayList 데이터 검색
        dataset.get(0);
        // ArrayList 데이터 전체 삭제
        dataset.clear();


        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에서 값 가져오기
                String data = binding.edtInputMemo.getText().toString();
                if(!data.equals("")){
                    // ArrayList에 데이터 추가
                    dataset.add(data);
                    // ListView 갱신
                    arrayAdapter.notifyDataSetChanged();
                    binding.edtInputMemo.setText("");
                }


            }
        });
        
        binding.lvBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, // ListView 그자체
                                    View view, // 클릭된 뷰
                                    int position, // 클릭된 뷰의 위치값 (인덱스번호)
                                    long id) { // 아이디 값

                String data =
                        (String)parent.getItemAtPosition(position);

                Toast.makeText(MainActivity.this,
                        data,
                        Toast.LENGTH_SHORT).show();
            }
        });

        binding.btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dataset.clear();
                arrayAdapter.notifyDataSetChanged();
            }
        });

        binding.lvBoard.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                dataset.remove(position);
                arrayAdapter.notifyDataSetChanged();

                return true;
            }
        });


    }
}