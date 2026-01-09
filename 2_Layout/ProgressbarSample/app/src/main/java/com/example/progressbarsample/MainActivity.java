package com.example.progressbarsample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ProgressBar pgbStatus;
    TextView tvStatus;
    int value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pgbStatus = findViewById(R.id.pgbStatus);
        tvStatus = findViewById(R.id.tvStatus);

        // 프로그래스바가 얼마나 차있는지에 대한 정보 세팅

//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i=1;i<=100;i++){
//                    int finalI = i;
//                    runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            pgbStatus.setProgress(finalI);
//                            tvStatus.setText("진행률 : "+ finalI +"%");
//                        }
//                    });
//
//                    try {
//                        Thread.sleep(500);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//
//        thread.start();

        ProgressbarTask task =
                new ProgressbarTask(MainActivity.this);
        task.execute(0);


    }



    // 이너클래스
    public class ProgressbarTask
            extends Task<Integer, Integer, Integer>{
            //    서브스레드 작업을 위해 Task클래스를 상속 받음
            //    <Params, Progress, Result> 의 제네릭 타입에
            //    정의될 데이터타입을 결정
            //    Params : 서브스레딩이 진행될때 필요한 데이터의 타입을 정의
            //    Progress : ui 갱신작업을 진행할때 필요한 데이터타입을 정의
            //    Result : 서브스레딩이 완료되었을때 반환되는 데이터타입을 정의

        // 생성자 : 객체 초기화
        public ProgressbarTask(Activity activityContext) {
            super(activityContext);
        }
        // 서브스레딩 작업을 하기 전에 진행할 작업들
        @Override
        public void onPreExecute() {
            // 프로그래스바의 최대 길이
            pgbStatus.setMax(100);
            value = 0;
            pgbStatus.setProgress(value);
        }
        // 서브스레딩 작업을 진행하는 메서드 run()메서드와 동일
        @Override
        public Integer doInBackground(Integer param) {
            for(int i =1; i <=100;i++){
                value = i;
                // ui 갱신
                publishProgress(value);
                // sleep
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            return value;
        }

        // 서브스레딩 중에 필요한 ui 접근 코드를 실행할 수 있는
        // 메서드
        @Override
        public void onProgressUpdate(Integer integer) {
            pgbStatus.setProgress(integer);
            tvStatus.setText("진행률 : "+integer+"%");
        }
        // 서브스레딩이 완료된 후 필요한 후처리 작업을 진행하는 메서드
        @Override
        public void onPostExecute(Integer integer) {
            tvStatus.setText("완료되었습니다!");
        }

    }



}