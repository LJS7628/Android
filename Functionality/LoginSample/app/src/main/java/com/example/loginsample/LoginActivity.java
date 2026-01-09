package com.example.loginsample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edtInputID;
    EditText edtInputPW;
    Button btnLogin;
    Button btnJoin;

    SharedPreferences sf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtInputID = findViewById(R.id.edtInputID);
        edtInputPW = findViewById(R.id.edtInputPW);
        btnJoin = findViewById(R.id.btnJoin);
        btnLogin = findViewById(R.id.btnLogin);

        sf = getSharedPreferences("member", MODE_PRIVATE);

        if(sf.getBoolean("status",false)){
            Intent intent = new Intent(LoginActivity.this,
                    MainActivity.class);
            startActivity(intent);
            finish();
        }



        btnJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,
                        JoinActivity.class);
                startActivity(intent);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EditText에 있는 값 가져오기
                String inputID = edtInputID.getText().toString();
                String inputPW = edtInputPW.getText().toString();

                if(inputID.equals(sf.getString("id",""))&&
                      inputPW.equals(sf.getString("pw",""))){

                    SharedPreferences.Editor editor = sf.edit();
                    editor.putBoolean("status",true);
                    editor.commit();

                    Intent intent = new Intent(LoginActivity.this,
                            MainActivity.class);
                    startActivity(intent);
                    finish();
                }else{
                    Toast.makeText(LoginActivity.this,
                            "로그인 정보가 일치하지 않습니다",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    protected void onResume() {
        super.onResume();
        edtInputID.setText(sf.getString("id",""));
    }
}