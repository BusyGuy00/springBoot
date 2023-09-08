package com.packt.cardatebase.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tour")
public class TourController {
   @GetMapping("/wea") 		//직접 날짜 조회 하기 위해 추가
   public String wather(String CurrentDate, String CourseId ) throws IOException {
      //요청할 주소를 적는다! ==> ?앞에까지의 주소를 적으면 된다!
      String apiUrl = "https://apis.data.go.kr/1360000/TourStnInfoService1/getTourStnVilageFcst1";
      //인증키
      String serviceKey = "ZruUg0gGNbtkTeaDAMGBQdTbvvcIppVjNE5q4Mkqa6BA889cOdVdF9EyoH2ZsCQQL8Fo3pqMbTJnV2LkPCiI9A%3D%3D";
      //페이지번호
      String pageNo = "1";
      //데이터 타입
      String dataType = "JSON";
      //조회하고 싶은 날짜
      //직접 날짜 조회 하기 위해 주석 
      //String CurrentDate = "20230823";
      //조회하고 싶은 날짜의 시간
      String Hour = "24";
      //관광코스 아이디
     // String CourseId = "2";
      //한페이지 결과수
      String numOfRows="10";
      
      //https://apis.data.go.kr/1360000/TourStnInfoService1/getTourStnVilageFcst1?serviceKey=Dtwf%2F1JwzgPrPQMNdmP6hQLiqrBJZTVhBwJb1Et5UBrRmkui2mmisvX6JM3NQHTCxaweFckuPalb1YIynsJcfA%3D%3D&pageNo=1&numOfRows=10&dataType=json&CURRENT_DATE=2019122010&HOUR=24&COURSE_ID=1
      //이런 요청 주소가 필요하다!
      StringBuilder urlBuilder = new StringBuilder(apiUrl);
      urlBuilder.append("?" + URLEncoder.encode("ServiceKey", "UTF-8")+"="+serviceKey);
      //https://apis.data.go.kr/1360000/TourStnInfoService1/getTourStnVilageFcst1?serviceKey=Dtwf%2F1JwzgPrPQMNdmP6hQLiqrBJZTVhBwJb1Et5UBrRmkui2mmisvX6JM3NQHTCxaweFckuPalb1YIynsJcfA%3D%3D&pageNo=1&numOfRows=10&dataType=json&CURRENT_DATE=2019122010&HOUR=24&COURSE_ID=1
      //&pageNo=
      //이걸 추가한거다!
      //StringBuilder는 계속해서 문자를 추가해 주는 것이다!
      urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8")+"="+pageNo);
      //&dataType=JSON
      //이걸 추가한거다!
      urlBuilder.append("&" + URLEncoder.encode("dataType", "UTF-8")+"="+dataType);
      //&CURRENT_DATE=20230823
      //이걸 추가한거다!
      urlBuilder.append("&" + URLEncoder.encode("CURRENT_DATE", "UTF-8")+"="+CurrentDate);
      //&HOUR=24
      //이걸 추가한거다!
      urlBuilder.append("&" + URLEncoder.encode("HOUR", "UTF-8")+"="+Hour);
      //&COURSE_ID=2
      //이걸 추가한거다!
      urlBuilder.append("&" + URLEncoder.encode("COURSE_ID", "UTF-8")+"="+CourseId);
      //&numOfRows=10
      //이걸 추가한거다!
      urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8")+"="+numOfRows);

      
      //get방식으로 전송
      URL url = new URL(urlBuilder.toString());
      HttpURLConnection conn = (HttpURLConnection) url.openConnection();
      //전송메소드 지정
      conn.setRequestMethod("GET");
      conn.setRequestProperty("content-type", "application/json");
      //읽어오겠다. BufferedReader ==> 가변길이 문자를 다 읽어올 때 사용하는 타입이다!
      BufferedReader rd;
      //응답코드가 200번대는 성공이다.
      //응답코드가 200번 이상이면서 300번 이하일 때
      if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
         rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
      }else {
         rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
      }
      StringBuilder sb = new StringBuilder();
      String line;
      //readLine은 한줄한줄 읽어낸다!
      while((line = rd.readLine()) != null) {
         sb.append(line);
      }
      rd.close();
      conn.disconnect();
      String result = sb.toString();
      return result;
   }
}