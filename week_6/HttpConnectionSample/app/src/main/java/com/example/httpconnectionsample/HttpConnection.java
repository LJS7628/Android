package com.example.httpconnectionsample;

/*
1. 서버에 전송할 URL주소 작성 ( URL+ Parameter)
2. 서버에 URL 정보를 request할때 어떠한 방식으로 보낼지 결정
 - POST : 상대적으로 느리지만 보안에 조금 괜찮은 방식,
            파라미터 길이 제한 없음, url 뒤에 파라미터가 보이지 않음
 - GET : 상대적으로 빠르지만 보안에 취약한 방식,
            파라미터의 길이 제한이 존재, 사용자가 요청한 파라미터값들이
            그대로 노출됨
3. HttpURLConnection 이라는 클래스를 객체 생성하여 request/response를
   처리함 -> 자바에서 제공하는 http 통신 라이브러리
4. reponse된 데이터를 Buffer를 통해 읽어들임
 */


import android.content.ContentValues;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class HttpConnection {
    // URL 클래스 : url 정보를 관리하는 클래스
    // 프로토콜, 포트번호, 경로 등이 나타나 있는 값
    URL url = null;
    //http 통신을 진행하는 클래스 객체를 선언
    HttpURLConnection httpURLConnection = null;

    public HttpConnection(String url, // 기본 url 주소
                          ContentValues reqParameter){ // 파라미터들
        // ContentValues -> hashmap과 유사한 형태의 key:value 타입
        // ex) appkey=123&version=1
        StringBuffer sbParams = new StringBuffer();

        // 파라미터가 존재하지 않는 상황
        if(reqParameter == null){
            sbParams.append("");
        }else{
            // 파라미터가 존재할 경우

            // 파라미터 뒤에 &를 붙일지 말지를 결정하는 변수
            boolean isAnd = false;

            String key = "";
            String value = "";

            for(Map.Entry<String, Object> parameter :
                    reqParameter.valueSet()){
                key = parameter.getKey();
                value = parameter.getValue().toString();
                if(isAnd){
                    sbParams.append("&");
                }
                sbParams.append(key)
                        .append("=")
                        .append(value);
                if(!isAnd){
                    if(reqParameter.size() >=2){
                        isAnd = true;
                    }
                }
            }
        }

        try {
            this.url = new URL(url+sbParams);

            httpURLConnection =
                    (HttpURLConnection)this.url.openConnection();
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setRequestProperty(
                    "content-type",
                    "application/x-www-form=urlencoded:charset=utf-8"
            );
            httpURLConnection.setRequestProperty(
                    "Accept",
                    "application/json"
            );

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public String connect(){
        BufferedReader bufferedReader = null;
        String lines = "";
        String line = "";

        // request를 날리는 부분
        try {
            httpURLConnection.connect();

            InputStream inputStream = httpURLConnection.getInputStream();
            bufferedReader =
                    new BufferedReader(
                            new InputStreamReader(inputStream));

            while((line=bufferedReader.readLine())!=null){
                lines+=line;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }


        return lines;
    }


}
