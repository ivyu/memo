package com.memo;

import java.util.Date;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import com.memo.db.NotesDB;
import com.memo.db.NotesDao;
import com.memo.model.Note;

public class EditNoteActivity extends AppCompatActivity {

    private EditText inputNote;
    private NotesDao dao;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);
        inputNote = findViewById(R.id.input_note);
        dao = NotesDB.getInstances(this).notesDao();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id == R.id.save_note)
            onSaveNote();
        return super.onOptionsItemSelected(item);
    }

    private void onSaveNote() {
        //TODO: Save Note
        String text = inputNote.getText().toString();
        if(!text.isEmpty()){
            long date = new Date().getTime();
            Note note = new Note(text,date);
            dao.insertNote(note);
            finish(); //return to the mainActivity
        }
    }
}
