package com.example.loginsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    CheckBox chbAgree;
    EditText edtInputID;
    EditText edtInputPW;
    EditText edtInputRepeatPW;
    Button btnJoinIn;
    
    SharedPreferences sf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        chbAgree = findViewById(R.id.chbAgree);
        edtInputID = findViewById(R.id.edtInputID);
        edtInputPW = findViewById(R.id.edtInputPW);
        edtInputRepeatPW = findViewById(R.id.edtInputRepeatPW);
        btnJoinIn = findViewById(R.id.btnJoinIn);

        sf = getSharedPreferences("member", MODE_PRIVATE);
        
        // 체크박스 체크여부 확인
         //true : 체크됨 // false : 체크안됨됨
        btnJoinIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = edtInputID.getText().toString();
                String pw = edtInputPW.getText().toString();
                String rpw = edtInputRepeatPW.getText().toString();
                
                // 비어있는지부터 확인
                if(id.equals("")||pw.equals("")||rpw.equals("")){
                    Toast.makeText(JoinActivity.this, 
                            "모두 입력하세요", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                // 비밀번호 두개가 일치하는가?
                else if(!pw.equals(rpw)){
                    Toast.makeText(JoinActivity.this,
                            "비밀번호가 일치하지 않습니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                else if(!chbAgree.isChecked()){
                    Toast.makeText(JoinActivity.this,
                            "동의하세요", Toast.LENGTH_SHORT).show();
                    return;
                }

                SharedPreferences.Editor editor = sf.edit();
                editor.putString("id", id);
                editor.putString("pw", pw);
                editor.commit();
                finish();
            }
        });
    }
}