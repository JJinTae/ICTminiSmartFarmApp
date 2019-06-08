package com.example.smartfarm;

import android.provider.BaseColumns;

public final class DataBases {
    public static final class CreateDB implements BaseColumns {
        public static final String TIME = "time";
        public static final String GROW = "grow";
        public static final String WV = "wv";
        public static final String NV = "nv";
        public static final String LIGHT = "light";
        public static final String _TABLENAME0 = "usertable"; //테이블 명칭
        public static final String _CREATE0 = "create table if not exists "+_TABLENAME0+"("
                +_ID+" integer primary key autoincrement, " // 구분자
                +TIME+" text not null , "
                +GROW+" text not null , "
                +WV+" integer not null , "
                +NV+" text not null , "
                +LIGHT+" text not null );";
    }
}
