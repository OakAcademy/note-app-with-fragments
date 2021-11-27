package com.techmania.notetakewithtwotable.RoomDatabase.Entities;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "completed_notes_table")
public class CompletedNotes {

    @PrimaryKey(autoGenerate = true)
    public int cId;

    public String cTitle;

    public String cDescription;

    public CompletedNotes(String cTitle, String cDescription) {
        this.cTitle = cTitle;
        this.cDescription = cDescription;
    }

    public int getcId() {
        return cId;
    }

    public String getcTitle() {
        return cTitle;
    }

    public String getcDescription() {
        return cDescription;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }
}
