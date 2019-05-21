package com.example.smartfarm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class PageModeDown extends AppCompatActivity {

    EditText et_save;
    String shared = "file";

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

    }

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
