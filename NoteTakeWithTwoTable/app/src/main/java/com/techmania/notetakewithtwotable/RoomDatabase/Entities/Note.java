package com.techmania.notetakewithtwotable.RoomDatabase.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class Note {

    @PrimaryKey(autoGenerate = true)
    public int id;
    public String title;
    public String description;
    public String time;

    public Note(String title, String description, String time) {
        this.title = title;
        this.description = description;
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getTime() {
        return time;
    }

    public void setId(int id) {
        this.id = id;
    }
}
