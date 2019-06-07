package com.example.smartfarm;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


public class PageModeDown extends AppCompatActivity {

    TextView text01;
    SQLiteDatabase database;
    String tableName = "CUSTOMER";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_mode_down);

        text01 = (TextView) findViewById(R.id.text01);

        createDatabase();
        createTable();

        //데이터넣기 버튼
        Button downNum1 = (Button) findViewById(R.id.downNum1);
            downNum1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                insertData1();
                Toast.makeText(getApplicationContext(), "상추, 배추, 쑥갓 - 엽채류 모드 다운로드", Toast.LENGTH_SHORT).show();
            }
        });

        Button downNum2 = (Button) findViewById(R.id.downNum2);
        downNum2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                insertData2();
                Toast.makeText(getApplicationContext(), "샐러리, 미나리 - 엽채류 모드 다운로드", Toast.LENGTH_SHORT).show();
            }
        });


    }
    //DB 생성
    private void createDatabase(){
        String name = "customer.db";
        database = openOrCreateDatabase(name, Context.MODE_PRIVATE, null); //DB가 존재하면 오픈. 존재하지않은면 생성
        text01.append("데이터베이스가 만들어졌어요"+name+"\n");
    }

    //테이블 생성
    private void createTable() {

        String sql = "create table " + tableName + "(growth integer, watervalue integer, nutrientvalue integer, usinglighttime integer)";

        try {
            database.execSQL(sql);//slq문 실행
            text01.append("테이블이 만들어졌어요"+tableName+"\n");
        } catch (Exception e) {
            text01.append("테이블 만들 때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }
    }

    //데이터넣기 1
    private void insertData1(){


        database.beginTransaction(); //sql문을 실행하는 일정구간을 트랜잭션으로 묶어주겠다라는 의미
        //트랜잭션 시작을 나타내는 메소드
        try{
            String sql = "insert into "+ tableName + "(growth, watervalue, nutrientvalue, usinglighttime) values(1, 1000, 3, 16)";
            database.execSQL(sql);

            text01.append("데이터를 넣었어요\n");

            database.setTransactionSuccessful(); //트랜잭션으로 묶어준 일정영역의 SQL문들이 모두 성공적으로 끝났을 지정

        }catch(Exception e){
            //SQL문 실행중 오류가 발생하면 예외처리가 되고..
            //트랜잭션에 정의된 SQL쿼리가 성공되지 않았기때문에 finally블록에서
            //트랜잭션 종료메서드 실행시(endTransaction()) 롤백이 된다.

            text01.append("데이터 추가할때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }finally{
            database.endTransaction(); //트랜잭션을 끝내는 메소드.
        }

    }


    //데이터넣기 2
    private void insertData2(){

        database.beginTransaction(); //sql문을 실행하는 일정구간을 트랜잭션으로 묶어주겠다라는 의미
        //트랜잭션 시작을 나타내는 메소드
        try{
            String sql = "insert into "+ tableName + "(growth, watervalue, nutrientvalue, usinglighttime) values(1, 1000, 5, 16)";
            database.execSQL(sql);

            text01.append("데이터를 넣었어요\n");

            database.setTransactionSuccessful(); //트랜잭션으로 묶어준 일정영역의 SQL문들이 모두 성공적으로 끝났을 지정

        }catch(Exception e){
            //SQL문 실행중 오류가 발생하면 예외처리가 되고..
            //트랜잭션에 정의된 SQL쿼리가 성공되지 않았기때문에 finally블록에서
            //트랜잭션 종료메서드 실행시(endTransaction()) 롤백이 된다.

            text01.append("데이터 추가할때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }finally{
            database.endTransaction(); //트랜잭션을 끝내는 메소드.
        }
    }
}