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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class PageModeDown extends AppCompatActivity {

    TextView text01;
    SQLiteDatabase database;
    String tableName = "CUSTOMER";
    final ArrayList<String> items = new ArrayList<String>(); // 빈 데이터 리스트 생성
    ArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_mode_down);

        text01 = (TextView) findViewById(R.id.text01); // 테스트용 TextView

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items); // ArrayAdapter 생성. 아이템(single choice) 가능하도록
        final ListView listview = (ListView) findViewById(R.id.listview1);
        listview.setAdapter(adapter);


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(listview.isItemChecked(0)){
                    text01.append("1번이 체크되었습니다.");
                }
                else if(listview.isItemChecked(1)){
                    text01.append("2번이 체크되었습니다.");
                }
            }
        });



        createDatabase();
        createTable();

        //데이터넣기 버튼
        Button downNum1 = (Button) findViewById(R.id.downNum1);
            downNum1.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                insertData1();
                Toast.makeText(getApplicationContext(), "상추, 배추, 쑥갓 - 엽채류 모드 다운로드 완료", Toast.LENGTH_SHORT).show();
                queryData();
            }
        });

        Button downNum2 = (Button) findViewById(R.id.downNum2);
        downNum2.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                insertData2();
                Toast.makeText(getApplicationContext(), "샐러리, 미나리 - 엽채류 모드 다운로드 완료", Toast.LENGTH_SHORT).show();
                queryData();
            }
        });




    } // onCreate





    //DB 생성
    private void createDatabase(){
        String name = "customer.db";
        database = openOrCreateDatabase(name, Context.MODE_PRIVATE, null); //DB가 존재하면 오픈. 존재하지않은면 생성
        // text01.append("데이터베이스가 만들어졌어요"+name+"\n");
    }

    //테이블 생성
    private void createTable() {

        String sql = "create table " + tableName + "(name text, growth integer, watervalue integer, nutrientvalue integer, usinglighttime integer)";

        try {
            database.execSQL(sql);//slq문 실행
            // text01.append("테이블이 만들어졌어요"+tableName+"\n");
        } catch (Exception e) {
            // text01.append("테이블 만들 때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }
    }

    //데이터넣기 1 (상추, 배추, 쑥갓 - 엽채류 모드 다운로드)
    private void insertData1(){


        database.beginTransaction(); //sql문을 실행하는 일정구간을 트랜잭션으로 묶어주겠다라는 의미
        //트랜잭션 시작을 나타내는 메소드
        try{
            String sql = "insert into "+ tableName + "(name, growth, watervalue, nutrientvalue, usinglighttime) values('상추, 배추, 쑥갓 - 엽채류 모드',1, 1000, 3, 16)";
            database.execSQL(sql);

            // text01.append("데이터를 넣었어요\n");
            database.setTransactionSuccessful(); //트랜잭션으로 묶어준 일정영역의 SQL문들이 모두 성공적으로 끝났을 지정

        }catch(Exception e){
            //SQL문 실행중 오류가 발생하면 예외처리가 되고..
            //트랜잭션에 정의된 SQL쿼리가 성공되지 않았기때문에 finally블록에서
            //트랜잭션 종료메서드 실행시(endTransaction()) 롤백이 된다.

            // text01.append("데이터 추가할때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }finally{
            database.endTransaction(); //트랜잭션을 끝내는 메소드.
        }
    }


    //데이터넣기 2 (샐러리, 미나리 - 엽채류 모드 다운로드)
    private void insertData2(){

        database.beginTransaction(); //sql문을 실행하는 일정구간을 트랜잭션으로 묶어주겠다라는 의미
        //트랜잭션 시작을 나타내는 메소드
        try{
            String sql = "insert into "+ tableName + "(name, growth, watervalue, nutrientvalue, usinglighttime) values('샐러리, 미나리 - 엽채류 모드',1, 1000, 5, 16)";
            database.execSQL(sql);

            // text01.append("데이터를 넣었어요\n");

            database.setTransactionSuccessful(); //트랜잭션으로 묶어준 일정영역의 SQL문들이 모두 성공적으로 끝났을 지정

        }catch(Exception e){
            //SQL문 실행중 오류가 발생하면 예외처리가 되고..
            //트랜잭션에 정의된 SQL쿼리가 성공되지 않았기때문에 finally블록에서
            //트랜잭션 종료메서드 실행시(endTransaction()) 롤백이 된다.

            // text01.append("데이터 추가할때 예외 : "+e.getMessage()+"\n");
            e.printStackTrace();
        }finally{
            database.endTransaction(); //트랜잭션을 끝내는 메소드.
        }
    }

    private void queryData(){
        String sql = "select name from " + tableName;
        Cursor cursor = database.rawQuery(sql, null);
        items.clear();
        if(cursor != null){
            int count = cursor.getCount(); // 조회된 개수 얻기
            try {
                for(int i =0; i<count; i++)
                {
                    cursor.moveToNext();
                    // text01.append("데이터를 조회했어요. 레코드 갯수 :" + count + "\n");
                    String a = cursor.getString(0);
                    items.add(a);
                    // text01.append("데이터 #" + i + " : " + name + "\n");
                    adapter.notifyDataSetChanged();
                }
            }
            catch (Exception e){
                Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_SHORT).show();
                //e.printStackTrace();
            }
        }
    }
}