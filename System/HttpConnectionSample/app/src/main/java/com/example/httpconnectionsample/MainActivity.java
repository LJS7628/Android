package com.example.httpconnectionsample;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextClock;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText edtInputData;
    Button btnSearch;
    TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtInputData = findViewById(R.id.edtInputData);
        btnSearch = findViewById(R.id.btnSearch);
        tvResult = findViewById(R.id.tvResult);


        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String keyword = edtInputData.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("version", URL_Container.VERSION);
                contentValues.put("appKey", URL_Container.APP_KEY);
                contentValues.put("searchKeyword", keyword);

                POIRequestTask task =
                        new POIRequestTask(MainActivity.this,
                                URL_Container.POI_REQUEST_URL,
                                contentValues);
                task.execute("");
            }
        });



    }

    public class POIRequestTask extends Task<String,String,String>{

        HttpConnection httpConnection;

        public POIRequestTask(Activity activityContext,
                              String url,
                              ContentValues reqParameter) {
            super(activityContext);

            httpConnection = new HttpConnection(url, reqParameter);
        }

        @Override
        public void onPreExecute() {

        }

        @Override
        public String doInBackground(String param) {
            String result = httpConnection.connect();
            Log.i("result check", result);
            return result;
        }

        @Override
        public void onPostExecute(String s) {
            // Json Parsing을 통해서 원하는 데이터추출
            try {
                JSONObject jsonObject = new JSONObject(s);
                JSONObject jsonObject1 = jsonObject.getJSONObject("searchPoiInfo");
                JSONObject jsonObject2 = jsonObject1.getJSONObject("pois");
                JSONArray jsonArray = jsonObject2.getJSONArray("poi");
                //tvResult.setText(jsonArray.toString());

                String data = "";
                for (int i =0; i<jsonArray.length();i++){
                    JSONObject jsonObject3 = jsonArray.getJSONObject(i);
                    data += jsonObject3.getString("name");
                    data += "\n";
                }

                tvResult.setText(data);


            } catch (JSONException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onProgressUpdate(String s) {

        }
    }
}