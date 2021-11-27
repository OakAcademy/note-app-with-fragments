package com.techmania.notetakewithtwotable.RoomDatabase.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.Note;
import com.techmania.notetakewithtwotable.RoomDatabase.Repository.NoteRepository;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> notes;
    private LiveData<List<CompletedNotes>> completedNotes;

    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        notes = repository.getAllNotes();
        completedNotes = repository.getAllCompletedNotes();
    }

    public void insert(Note note)
    {
        repository.insert(note);
    }

    public void insertCompletedNote(CompletedNotes note)
    {

        repository.insertCompletedNote(note);
    }

    public void update(Note note)
    {
        repository.update(note);
    }

    public void delete(Note note)
    {
        repository.delete(note);
    }

    public void deleteCompletedNotes(CompletedNotes note)
    {
        repository.deleteCompletedNotes(note);
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
