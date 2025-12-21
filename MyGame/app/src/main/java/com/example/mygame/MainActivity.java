package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private ImageButton[] buttons = new ImageButton[12];
    private ArrayList<Integer> imageList;
    private ArrayList<MemoryCard> cards;
    private TextView resultText;
    private Button resetBtn;
    private Button menu;

    int preCardPosition = -1;  //이전 카드 위치



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText =findViewById(R.id.result_text);
        init();
        resetBtn = findViewById(R.id.btn_reset);

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                init();
            }
        });

        menu = findViewById(R.id.menu);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent intent = new Intent(MainActivity.this,Menu.class);
                startActivity(intent);



            }
        });


    }

    public void init() {

        imageList = new ArrayList<>();
        imageList.add(R.drawable.king_h);
        imageList.add(R.drawable.king_h);
        imageList.add(R.drawable.king_c);
        imageList.add(R.drawable.king_c);
        imageList.add(R.drawable.queen_d);
        imageList.add(R.drawable.queen_d);
        imageList.add(R.drawable.queen_s);
        imageList.add(R.drawable.queen_s);
        imageList.add(R.drawable.jack_c);
        imageList.add(R.drawable.jack_c);
        imageList.add(R.drawable.jack_s);
        imageList.add(R.drawable.jack_s);


        Collections.shuffle(imageList);
        cards = new ArrayList<>();
        for(int i=0;i<buttons.length;i++){
            String buttonID = "btn"+i;
            int resourceID = getResources().getIdentifier(buttonID,"id",getPackageName());
            buttons[i] = findViewById(resourceID);

            buttons[i].setOnClickListener(this);
            cards.add(new MemoryCard(imageList.get(i), false,false));
            buttons[i].setImageResource(R.drawable.card_back);

            buttons[i].setAlpha(1.0f);
        }
          resultText.setText("GAME START");
    }

    @Override
    public void onClick(View view){
        int id = view.getId();
        int position = 0;

        if(id == R.id.btn0)
            position = 0;
        else if(id == R.id.btn1)
            position = 1;
        else if(id == R.id.btn2)
            position = 2;
        else if(id == R.id.btn3)
            position = 3;
        else if(id == R.id.btn4)
            position = 4;
        else if(id == R.id.btn5)
            position = 5;
        else if(id == R.id.btn6)
            position = 6;
        else if(id == R.id.btn7)
            position = 7;
        else if(id == R.id.btn8)
            position = 8;
        else if(id == R.id.btn9)
            position = 9;
        else if(id == R.id.btn10)
            position = 10;
        else if(id == R.id.btn11)
            position = 11;

        updateModel(position);
        updateView(position);

    }

    public void onToggleClicked(View view){
        boolean on = ((ToggleButton)view).isChecked();
        if(on){
            startService(new Intent(this,MyService_bgm.class));
        }else {
            stopService(new Intent(this,MyService_bgm.class));
        }
    }

    private void updateModel(int position){
        MemoryCard card =cards.get(position);

        cards.get(position).setFaceUp(card.isFaceUp());

        if(preCardPosition == -1){
            restoreCard();
            preCardPosition = position;

        }else{
            checkForMatch(preCardPosition, position);
            preCardPosition = -1;
        }
    }

    private void updateView(int position){

        MemoryCard card = cards.get(position);

        if(!card.isFaceUp()){
            buttons[position].setImageResource(card.getImageId());
        }
        else{
            buttons[position].setImageResource(R.drawable.card_back);
        }
    }

    private void restoreCard() {
        for(int i= 0;i<cards.size();i++){
            if(!cards.get(i).isMatched()){
                buttons[i].setImageResource(R.drawable.card_back);
                cards.get(i).setFaceUp(false);
            }
        }
    }

    private void checkForMatch(int prePosition, int position){
        if(cards.get(prePosition).getImageId() == cards.get(position).getImageId()){

            resultText.setText("매치 성공");

            cards.get(prePosition).setMatched(true);
            cards.get(position).setMatched(true);

            buttons[prePosition].setAlpha(0.1f);
            buttons[position].setAlpha(0.1f);

            checkCompletion();
        }else{
            resultText.setText("매치 실패");
        }
    }

    private void checkCompletion() {

        int count = 0;

        for(int i = 0; i < cards.size();i++){
            if(cards.get(i).isMatched()){
                count++;
            }
        }

        if(count == cards.size()){
            resultText.setText("THE END");
        }
    }
}
