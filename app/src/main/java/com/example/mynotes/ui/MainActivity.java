package com.example.mynotes.ui;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mynotes.Database.Note;
import com.example.mynotes.R;
import com.example.mynotes.ViewModel;
import com.example.mynotes.adapter.NoteAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import static android.widget.LinearLayout.*;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    List< Note > notes;
    private ViewModel viewModel;
    public static final int INSERT_REQUEST_CODE =1;
    public static final String TITLE_TAG = "Title_here";
    public static final String DESCRIPTION_TAG = "description_tag";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView= findViewById(R.id.recycler);
        final NoteAdapter adapter = new NoteAdapter();
        LinearLayoutManager linearLayout = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayout);
        recyclerView.setAdapter(adapter);
        notes = new ArrayList<>();

        viewModel = ViewModelProviders.of(this).get(ViewModel.class);

        //viewModel.insert(new Note("tkjd","Dfd","DFdfdf"));
        viewModel.getmAllNotes().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
                Log.d("Here", "onChanged: "+notes.toString());
                //Toast.makeText(MainActivity.this, "Changed", Toast.LENGTH_SHORT).show();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fab);

        fab.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,InsertActivity.class);
              startActivityForResult(intent,INSERT_REQUEST_CODE);
            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == INSERT_REQUEST_CODE && resultCode == RESULT_OK){
            Log.d("here","onActivityResult: ");
            viewModel.insert(new Note(data.getStringExtra(TITLE_TAG),data.getStringExtra(DESCRIPTION_TAG),"16 july,2020"));
        }
    }
}