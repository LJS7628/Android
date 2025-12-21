package com.example.memosample2;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.memosample2.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;
    MemoAdapter memoAdapter;

    int editPosition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);

        memoAdapter = new MemoAdapter();
        binding.lvBoard.setAdapter(memoAdapter);

        binding.btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        EditActivity.class);
                intent.putExtra("mode", "add");
                startActivityForResult(intent, 1001);
            }
        });


        binding.lvBoard.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MemoItem item = (MemoItem)
                        parent.getItemAtPosition(position);

                Intent intent = new Intent(
                        MainActivity.this,
                        EditActivity.class
                );
                editPosition = position;
                intent.putExtra("title", item.getTitle());
                intent.putExtra("content", item.getContent());
                intent.putExtra("mode","edit");
                startActivityForResult(intent, 1100);
            }
        });

        binding.lvBoard.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {


                // 다이얼러그 생성하기 - Builder 패턴
                AlertDialog.Builder dialog =
                        new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("삭제하기");
                dialog.setMessage("정말로 삭제하시겠습니까?");
                dialog.setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        memoAdapter.removeMemoItemData(position);
                        memoAdapter.notifyDataSetChanged();
                    }
                });
                dialog.setNegativeButton("취소", null);
                dialog.show();

                return true;
            }
        });




    }


    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1001){
            if(resultCode == 2001){
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                memoAdapter.addMemoItemData(title, content);
                memoAdapter.notifyDataSetChanged();
            }
        }else if(requestCode == 1100){
            if(resultCode == 2100){
                String title = data.getStringExtra("title");
                String content = data.getStringExtra("content");
                memoAdapter.editMemoItemData(editPosition, title, content);
                memoAdapter.notifyDataSetChanged();
            }
        }
    }
}