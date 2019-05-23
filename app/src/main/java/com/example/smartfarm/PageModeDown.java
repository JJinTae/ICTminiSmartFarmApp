package com.example.smartfarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PageModeDown extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

    // 여기부터 수정
    EditText etName, etMenu;
    // 여기까지


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_mode_down);


        Intent intent = getIntent();
        Log.d("ACTIVITY_LC", intent.getStringExtra("message"));

        et_save = (EditText)findViewById(R.id.et_save);
        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        String value = sharedPreferences.getString("kim","");
        et_save.setText(value);

        // 여기
        init();
        // 여기 까지

    }


    // 여기 함수
    void init(){
        etName = (EditText) findViewById(R.id.etname);
        etMenu = (EditText) findViewById(R.id.etmenu);
    }

    public void onClick(View v){
        if(v.getId() == R.id.downNum1){
            Intent intent = new Intent();

            String name = etName.getText().toString();
            String menu = etMenu.getText().toString();

            smartfarm temp = new smartfarm(name, menu);

            intent.putExtra("smartfarm", temp);
            setResult(RESULT_OK, intent);
            // 모드 적용 페이지 바로 띄우기
            Intent activity = new Intent(getApplicationContext(), PageInsertMode.class);
            startActivity(activity);
            // 모드 적용 페이지 바로 띄우기
        }


    }
    // 여기 까지

    @Override
    protected void onDestroy() {
        super.onDestroy();

        SharedPreferences sharedPreferences = getSharedPreferences(shared, 0);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        String value = et_save.getText().toString();
        editor.putString("kim", value);
        editor.commit();
    }
}
