package com.example.threadsample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Thread를 만드는 방법
        // 1. Thread 클래스를 상속받는 방법

        Horse horse1 = new Horse("horse01");
        horse1.start();

        Horse horse2 = new Horse("horse02");
        horse2.start();

        Horse horse3 = new Horse("horse03");
        horse3.start();



        // 2. Runnable Interface를 구현하는 방법
        Turtle turtle = new Turtle("turtle01");
        Thread thread = new Thread(turtle);
        thread.start();


        // 3. 일회용 스레드 구현 방법

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();



    }
}