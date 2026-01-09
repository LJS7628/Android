package com.example.memosample2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.memosample2.databinding.ActivityEditBinding;

public class EditActivity extends AppCompatActivity {
    private ActivityEditBinding binding;
    String mode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(EditActivity.this, R.layout.activity_edit);


        Intent prevIntent = getIntent();
        mode = prevIntent.getStringExtra("mode");

        if(mode.equals("edit")){
            binding.edtInputTitle.setText(
                    prevIntent.getStringExtra("title"));
            binding.edtInputContent.setText(
                    prevIntent.getStringExtra("content"));
            binding.btnEdit.setText("수정하기");
        }



        binding.btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = binding.edtInputTitle.getText().toString();
                String content = binding.edtInputContent.getText().toString();

                Intent intent = new Intent();
                if(mode.equals("add")){
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    setResult(2001, intent);
                    finish();
                }else if(mode.equals("edit")){
                    intent.putExtra("title", title);
                    intent.putExtra("content", content);
                    setResult(2100, intent);
                    finish();
                }

            }
        });

    }
}