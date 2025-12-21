package com.example.tmapviasample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tmapviasample.databinding.ActivityMainBinding;
import com.skt.Tmap.TMapData;
import com.skt.Tmap.TMapMarkerItem;
import com.skt.Tmap.TMapPOIItem;
import com.skt.Tmap.TMapPoint;
import com.skt.Tmap.TMapPolyLine;
import com.skt.Tmap.TMapView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding binding;
    TMapView tMapView;
    TMapData tMapData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(MainActivity.this,
                R.layout.activity_main);
        // 메서드 바인딩 시 필수로 작성해야 함
        binding.setActivity(MainActivity.this);

        tMapView = new TMapView(MainActivity.this);
        tMapView.setSKTMapApiKey("l7xxbf9afa9b60f64947a7b3ac1428fa067d");
        binding.relMapView.addView(tMapView);
        tMapData = new TMapData();

    }
    // 출발지 검색 이벤트 참조 메서드
    public void btnSearchStart(View view){
        String keyword = binding.edtInputStartKeyword.getText().toString();

        tMapData.findAllPOI(keyword, 1, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {
                if(arrayList.size()<=0){
                    // 검색 결과가 없음

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "검색 결과가 없습니다!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });

                }else{
                    TMapPOIItem item = arrayList.get(0);
                    TMapMarkerItem marker = new TMapMarkerItem();
                    marker.setTMapPoint(item.getPOIPoint());
                    marker.setCanShowCallout(true);
                    marker.setCalloutTitle(item.getPOIName());
                    tMapView.addMarkerItem("start", marker);
                    tMapView.setCenterPoint(item.getPOIPoint().getLongitude(),
                            item.getPOIPoint().getLatitude());

                }
            }
        });
    }

    // 도착지 검색 이벤트 참조 메서드
    public void btnSearchEnd(View view){
        String keyword = binding.edtInputEndKeyword.getText().toString();

        tMapData.findAllPOI(keyword, 1, new TMapData.FindAllPOIListenerCallback() {
            @Override
            public void onFindAllPOI(ArrayList<TMapPOIItem> arrayList) {
                if(arrayList.size()<=0){
                    // 검색 결과가 없음
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(MainActivity.this,
                                    "검색 결과가 없습니다!",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    TMapPOIItem item = arrayList.get(0);
                    TMapMarkerItem marker = new TMapMarkerItem();
                    marker.setTMapPoint(item.getPOIPoint());
                    marker.setCanShowCallout(true);
                    marker.setCalloutTitle(item.getPOIName());
                    tMapView.addMarkerItem("end", marker);
                    tMapView.setCenterPoint(item.getPOIPoint().getLongitude(),
                            item.getPOIPoint().getLatitude());

                }
            }
        });
    }



    // 보행자 경로를 요청하는 바인딩 메서드
    public void requestPedPath(View view){
        //1. 지도 위에 있는 start 마커를 가져온다.
        TMapMarkerItem startMarker =
                tMapView.getMarkerItemFromID("start");
        startMarker.setVisible(TMapMarkerItem.HIDDEN);
        TMapPoint startPoint = startMarker.getTMapPoint();
        TMapMarkerItem endMarker =
                tMapView.getMarkerItemFromID("end");
        TMapPoint endPoint = endMarker.getTMapPoint();
        //2. start 마커 안에 들어있는 point 정보를 추출한다.
        //3. end 도 동일하게 작업
        //4. 경로 요청 메서드를 호출한다.
        tMapData.findPathDataWithType(
                TMapData.TMapPathType.PEDESTRIAN_PATH,
                startPoint,
                endPoint,
                new TMapData.FindPathDataListenerCallback() {
                    @Override
                    public void onFindPathData(TMapPolyLine tMapPolyLine) {
                        tMapView.addTMapPath(tMapPolyLine);
                    }
                });

    }








}