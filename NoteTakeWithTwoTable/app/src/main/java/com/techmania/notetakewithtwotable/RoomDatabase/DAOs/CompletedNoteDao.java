package com.techmania.notetakewithtwotable.RoomDatabase.DAOs;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;

import java.util.List;

@Dao
public interface CompletedNoteDao {

    @Insert
    void insertCompletedNote(CompletedNotes completedNotes);

    @Delete
    void deleteCompletedNote(CompletedNotes completedNotes);

    @Query("SELECT * FROM completed_notes_table ORDER BY cId ASC")
    LiveData<List<CompletedNotes>> getAllCompletedNotes();

}
