package com.example.smartfarm;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class PageInsertMode extends AppCompatActivity {

    // 여기부터
    ListView listview;
    ArrayAdapter<String> adapter;

    int Addition = 1;

    ArrayList<smartfarm> storage = new ArrayList<>();
    ArrayList<String> title = new ArrayList<>();
    // 여기까지


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_insert_mode);

        // 여기부터
        init();
        // 여기까지

        // 빈 데이터 리스트 생성
        final ArrayList<String> items = new ArrayList<String>();
        // ArrayAdapter 생성, 아이템 View를 선택(Single choice)가능하도록 만듦.
        final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, items);

        // listview 생성 및 adapter 지정.
        final ListView listview = (ListView) findViewById(R.id.listMode);
        listview.setAdapter(adapter);


        // add button에 대한 이벤트 처리
        Button addButton = (Button)findViewById(R.id.add);
        addButton.setOnClickListener(new Button.OnClickListener(){
            public void onClick(View v){
                int count;
                count = adapter.getCount();

                // 아이템 추가
                items.add("LIST" + Integer.toString(count + 1));

                // listview 갱신
                adapter.notifyDataSetChanged();


            }
        });




    }
    //여기부터
    void init(){
        listview = (ListView)findViewById(R.id.listMode);

        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, title);

        listview.setAdapter(adapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long I) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(PageInsertMode.this);
                final int position = i;
                dlg.setTitle("삭제")
                        .setMessage("삭제하시겠습니까 ?")
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        })
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                title.remove(position);
                                storage.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        }).show();
                return true;
            }
        });

        /* 다른 액티비티에 출력해주는 기능
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, Main3Activity.class);

                intent.putExtra("restaurant", storage.get(i));

                startActivity(intent);
            }
        });
        다른 액티비티에 출력해주는 기능 여까지 */



    }

    public void onClick(View v){
        Intent intent = new Intent(this,PageModeDown.class);

        startActivityForResult(intent, Addition);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(requestCode == Addition){
            if(resultCode == RESULT_OK){
                smartfarm s = data.getParcelableExtra("smartfarm");
                title.add(s.getName());
                storage.add(s);
                adapter.notifyDataSetChanged();
            }
        }
    }
    //여기까지
}
