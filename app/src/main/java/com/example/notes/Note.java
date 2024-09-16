package com.example.notes;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Note {
    String title ;
    String content;

    public Note(String title, String content) {
        this.title = title;
        this.content = content;
    }


    public String getTitle() {



        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
