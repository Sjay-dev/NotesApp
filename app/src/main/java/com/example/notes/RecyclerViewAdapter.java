package com.example.notes;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.noteViewHolder> {
    Context context;
    ArrayList<Note> noteList;


    public RecyclerViewAdapter(Context context, ArrayList<Note> noteList) {
        this.context = context;
        this.noteList = noteList;
    }

    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notes_view, parent, false);
        return new noteViewHolder(view, context);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.noteViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Note note = noteList.get(position);

     String Stitle = note.getTitle();
   String   Scontent = note.getContent();

        holder.title.setText(Stitle);

       holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SavedNotes.class);
                Bundle bundle = new Bundle();
                bundle.putString("TITLE" , Stitle);
                bundle.putString("CONTENT" ,Scontent);

                bundle.putInt("POSITION" , position);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });

        holder.title.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setMessage("Delete this note?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       MainActivity mainActivity = new MainActivity();
                        SharedPreferences sharedPreferences = context.getSharedPreferences("MYPREFS" , Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                        noteList.remove(note);
                        editor.putInt("noteCount" , noteList.size());
                        editor.commit();
                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No" , null);
                builder.show();
                return true;
            }


        });


    }



    @Override
    public int getItemCount() {
        return noteList.size();
    }

    public class noteViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        EditText content;


        public noteViewHolder(@NonNull View itemView, Context context) {
            super(itemView);
            title = itemView.findViewById(R.id.txtSavedTitle1);
            content = itemView.findViewById(R.id.edtContent);


        }


    }
}