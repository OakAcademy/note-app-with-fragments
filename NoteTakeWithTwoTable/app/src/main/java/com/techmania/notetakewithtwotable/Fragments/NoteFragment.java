package com.techmania.notetakewithtwotable.Fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.techmania.notetakewithtwotable.Adapters.NoteAdapter;
import com.techmania.notetakewithtwotable.AddNoteActivity;
import com.techmania.notetakewithtwotable.R;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.Note;
import com.techmania.notetakewithtwotable.RoomDatabase.ViewModel.NoteViewModel;

import java.util.List;

public class NoteFragment extends Fragment {

    public static NoteFragment newInstance(){
        return new NoteFragment();
    }

    public NoteFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    NoteViewModel noteViewModel;
    FloatingActionButton fab;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_note, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        fab = view.findViewById(R.id.floatingActionButton);

        NoteAdapter adapter = new NoteAdapter(requireActivity());
        recyclerView.setAdapter(adapter);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(requireActivity(), AddNoteActivity.class);
                startActivityForResult(intent,1);
            }
        });

        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())
                .create(NoteViewModel.class);

        noteViewModel.getAllNotes().observe(requireActivity(), new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

                String completedTitle = adapter.getNotes(viewHolder.getAdapterPosition()).getTitle();
                String completedDesc = adapter.getNotes(viewHolder.getAdapterPosition()).getDescription();

                CompletedNotes completedTasks = new CompletedNotes(completedTitle,completedDesc);
                noteViewModel.insertCompletedNote(completedTasks);

                noteViewModel.delete(adapter.getNotes(viewHolder.getAdapterPosition()));

                Toast.makeText(requireActivity().getApplicationContext(), "Note moved to Completed Tasks", Toast.LENGTH_SHORT).show();

            }
        }).attachToRecyclerView(recyclerView);

        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null)
        {
            String title = data.getStringExtra("noteTitle");
            String description = data.getStringExtra("noteDescription");
            String time = data.getStringExtra("time");

            Note note = new Note(title,description,time);
            noteViewModel.insert(note);

        }
        else if (requestCode == 2 && resultCode == RESULT_OK && data != null)
        {

            String title = data.getStringExtra("titleLast");
            String description = data.getStringExtra("descriptionLast");
            String newtime = data.getStringExtra("newtime");
            int id = data.getIntExtra("noteId",-1);

            Note note = new Note(title,description,newtime);
            note.setId(id);
            noteViewModel.update(note);

        }
    }
}