package com.example.mynotes.Database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "NOTE_TABLE")
public class Note {

    @PrimaryKey(autoGenerate = true)
    private  int id;
    private String title;
    private String notes;
    private String date;

    public Note(String title, String notes, String date) {
        this.title = title;
        this.notes = notes;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getNotes() {
        return notes;
    }
}
