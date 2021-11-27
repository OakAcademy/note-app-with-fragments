package com.techmania.notetakewithtwotable.RoomDatabase.Repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.techmania.notetakewithtwotable.RoomDatabase.DAOs.CompletedNoteDao;
import com.techmania.notetakewithtwotable.RoomDatabase.DAOs.NoteDao;
import com.techmania.notetakewithtwotable.RoomDatabase.Database.NoteDatabase;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.Note;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NoteRepository {

    private final NoteDao noteDao;
    private final CompletedNoteDao completedNoteDao;
    private final LiveData<List<Note>> notes;
    private final LiveData<List<CompletedNotes>> completedNotes;

    ExecutorService executors = Executors.newSingleThreadExecutor();

    public NoteRepository(Application application)
    {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        notes = noteDao.getAllNotes();

        completedNoteDao = database.completedNoteDao();
        completedNotes = completedNoteDao.getAllCompletedNotes();
    }

    public void insert(Note note)
    {

        executors.execute(new Runnable() {
            @Override
            public void run() {
                noteDao.insert(note);
            }
        });
    }

    public void insertCompletedNote(CompletedNotes note)
    {

        executors.execute(() -> completedNoteDao.insertCompletedNote(note));
    }

    public void update(Note note)
    {

        executors.execute(() -> noteDao.update(note));
    }
    public void delete(Note note)
    {
        executors.execute(() -> noteDao.delete(note));
    }

    public void deleteCompletedNotes(CompletedNotes note)
    {
        executors.execute(() -> completedNoteDao.deleteCompletedNote(note));
    }

    public LiveData<List<Note>> getAllNotes()
    {
        return notes;
    }

    public LiveData<List<CompletedNotes>> getAllCompletedNotes()
    {
        return completedNotes;
    }

}
