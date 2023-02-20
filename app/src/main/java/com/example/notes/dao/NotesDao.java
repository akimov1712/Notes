package com.example.notes.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.notes.models.NoteModel;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM notess ORDER BY arendToDay")
    LiveData<List<NoteModel>> getAllNotes();

    @Insert
    void insertNote(NoteModel note);

    @Delete
    void deleteNote(NoteModel note);

    @Query("DELETE FROM notess")
    void deleteAllNotes();

}
