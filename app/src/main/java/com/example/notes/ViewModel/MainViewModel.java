package com.example.notes.ViewModel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.notes.NotesDatabase;
import com.example.notes.models.NoteModel;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDatabase database;

    private LiveData<List<NoteModel>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = NotesDatabase.getInstance(getApplication());
        notes = database.notesDao().getAllNotes();
    }

    public LiveData<List<NoteModel>> getNotes() {
        return notes;
    }

    public void insertNote(NoteModel note){
        new InsertTask().execute(note);
    }

    public void deleteNote(NoteModel note){
        new DeleteTask().execute(note);
    }

    public void deleteAllNote(){
        new DeleteAllTask().execute();
    }

    private static class InsertTask extends AsyncTask<NoteModel, Void, Void>{

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            if (noteModels != null && noteModels.length > 0){
                database.notesDao().insertNote(noteModels[0]);
            }
            return null;
        }

    }
    private static class DeleteTask extends AsyncTask<NoteModel, Void, Void>{

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            if (noteModels != null && noteModels.length > 0){
                database.notesDao().deleteNote(noteModels[0]);
            }
            return null;
        }

    }

    private static class DeleteAllTask extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... noteModels) {
            database.notesDao().deleteAllNotes();
            return null;
        }

    }


}
