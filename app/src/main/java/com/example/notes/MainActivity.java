package com.example.notes;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Context context;

    EditText title, content;
    Button save;
    String Scontent, Stitle , Loadtitle , Loadcontent;
    ArrayList<Note> noteList  = new ArrayList<>();

    RecyclerView recyclerView;




    final static String textTITLE = "textTitle";
    final static String textCOTENT = "textContent";
   final  static  String keyNote = "noteCount";
    final static String PREFS = "MYPREFS";


    @SuppressLint("MissingInflatedId")
    @Override
    protected  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title = findViewById(R.id.edtTitle);
        content = findViewById(R.id.edtContent);
        save = findViewById(R.id.btnSave);
            recyclerView = findViewById(R.id.RecyclerNotes);


        loadSharedPreferences();
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this , noteList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);



        save.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                saveNote();

            }});



    }



     void loadSharedPreferences() {

        SharedPreferences sharedPreferences = getSharedPreferences(PREFS, MODE_PRIVATE);
      int  noteCount = sharedPreferences.getInt(keyNote , 0);
        for (int i = 0 ; i<noteCount; i++){

            Loadtitle =  sharedPreferences.getString(textTITLE + i, null);
            Loadcontent = sharedPreferences.getString(textCOTENT + i , null);

            Note note = new Note(Loadtitle , Loadcontent);
            note.setTitle(Loadtitle);
            note.setContent(Loadcontent);
            noteList.add(note);

        }

    }







    private void saveNote() {


        if (!title.getText().toString().isEmpty()) {
            Stitle = title.getText().toString();
            Scontent = content.getText().toString();
            Note note = new Note(Stitle, Scontent);
            note.setTitle(Stitle);
            note.setContent(Scontent);
            noteList.add(note);
            savetoSharedPreferences();

            title.getText().clear();
            content.getText().clear();
        }
        else{
            Toast.makeText(this, "Type in a Title", Toast.LENGTH_SHORT).show();
        }



    }

    public void savetoSharedPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(keyNote , noteList.size());
        for(int i =0 ; i<noteList.size();  i++){
            Note note = noteList.get(i);
            editor.putString(textTITLE + i,note.getTitle());
            editor.putString(textCOTENT + i, note.getContent());
        }

        editor.apply();
        editor.commit();

    }



}


