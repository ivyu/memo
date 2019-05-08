package com.memo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.memo.db.NotesDB;
import com.memo.db.NotesDao;
import com.memo.model.Note;
import com.memo.adapter.notesAdapter;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Note> notes;
    private notesAdapter adapter;
    private NotesDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // init recycleView
        recyclerView = findViewById(R.id.notes_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //loadNotes();

        // init fab button!!!


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Todo 2019/4/24 add new Notes
                onAddNewNote();
            }
        });

        dao = NotesDB.getInstances(this).notesDao();
    }

    private void loadNotes() {
        this.notes = new ArrayList<>();
       /* for (int i = 0; i <3 ; i++) {
            notes.add(new Note("Here it is~",new Date().getTime()));
        }*/
        List<Note> list = dao.getNotes(); //get all notes from db

        this.notes.addAll(list);
        this.adapter = new notesAdapter(this,notes);
        this.recyclerView.setAdapter(adapter);
        //adapter.notifyDataSetChanged();
    }

    private void onAddNewNote() {
        /*if(notes != null){
            notes.add(new Note("This is a new note (serious:D)", new Date().getTime()));
        }
        if(adapter != null){
            adapter.notifyDataSetChanged();
        }*/

        //TODO: Start Edit Note
        startActivity(new Intent(this,EditNoteActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.save_note) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @Override
    protected void onResume() {
        super.onResume();
        loadNotes();
    }
}
