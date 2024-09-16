package com.example.notes;

import static com.example.notes.MainActivity.PREFS;
import static com.example.notes.MainActivity.keyNote;
import static com.example.notes.MainActivity.textCOTENT;
import static com.example.notes.MainActivity.textTITLE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.nio.channels.NonReadableChannelException;
import java.util.ArrayList;
import java.util.List;

public class SavedNotes extends AppCompatActivity {

    EditText content , title ;
    Button button;

    ArrayList<Note> noteList  = new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_notes);
                title = findViewById(R.id.edtSavedTitle);
            content = findViewById(R.id.edtSavedContent);
        button = findViewById(R.id.btnSaveSave);

        Bundle bundle = getIntent().getExtras();


        String Stitle = bundle.getString("TITLE");
        String Scontent = bundle.getString("CONTENT");
        int position = bundle.getInt("POSITION");

        title.setText(Stitle);
        content.setText(Scontent);




button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        editor.putString(textTITLE + position ,title.getText().toString());
            editor.putString(textCOTENT + position, content.getText().toString());
        editor.apply();
        editor.commit();
        Intent intent = new Intent(SavedNotes.this , MainActivity.class);
        startActivity(intent);


    }
});



    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent= new Intent(this , MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);

    }
}