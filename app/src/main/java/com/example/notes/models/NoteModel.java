package com.example.notes.models;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notess")
public class NoteModel {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String descr;
    private String arendToDay;

    public NoteModel(int id, String title, String descr, String arendToDay) {
        this.id = id;
        this.title = title;
        this.descr = descr;
        this.arendToDay = arendToDay;
    }

    @Ignore
    public NoteModel(String title, String descr, String arendToDay) {
        this.title = title;
        this.descr = descr;
        this.arendToDay = arendToDay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public void setArendToDay(String arendToDay) {
        this.arendToDay = arendToDay;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescr() {
        return descr;
    }

    public String getArendToDay() {
        return arendToDay;
    }
}
