package com.example.smartfarm;

import android.os.Parcel;
import android.os.Parcelable;

public class smartfarm implements Parcelable{
    private String name, menu;

    public smartfarm(String name, String menu){
        this.name = name;
        this.menu = menu;
    }

    protected smartfarm(Parcel in){
        name=in.readString();
        menu=in.readString();
    }

    public static final Creator<smartfarm> CREATOR = new Creator<smartfarm>() {
        @Override
        public smartfarm createFromParcel(Parcel in) { return new smartfarm(in); }

        @Override
        public smartfarm[] newArray(int size) { return new smartfarm[size]; }
    };

    @Override
    public int describeContents() { return 0; }

    @Override
    public void writeToParcel(Parcel parcel, int i){
        parcel.writeString(name);
        parcel.writeString(menu);
    }

    public String getName() { return name; }
    public void setName(String name) {this.name = name;}

    public String getMenu(){return menu;}
    public void setMenu(String menu){ this.menu = menu;}

}
