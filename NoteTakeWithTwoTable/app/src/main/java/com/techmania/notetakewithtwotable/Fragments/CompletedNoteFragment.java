package com.techmania.notetakewithtwotable.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.techmania.notetakewithtwotable.Adapters.CompletedNotesAdapter;
import com.techmania.notetakewithtwotable.R;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;
import com.techmania.notetakewithtwotable.RoomDatabase.ViewModel.NoteViewModel;

import java.util.List;


public class CompletedNoteFragment extends Fragment {

    public static CompletedNoteFragment newInstance(){
        return new CompletedNoteFragment();
    }

    private NoteViewModel noteViewModel;

    public CompletedNoteFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_completed_note, container, false);

        RecyclerView recyclerView = view.findViewById(R.id.rvCompleted);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        //create adapter object
        CompletedNotesAdapter adapter = new CompletedNotesAdapter(requireActivity().getApplication());

        //set adapter
        recyclerView.setAdapter(adapter);

        //viewModel
        noteViewModel = new ViewModelProvider.AndroidViewModelFactory(requireActivity().getApplication())
                .create(NoteViewModel.class);
        noteViewModel.getAllCompletedNotes().observe(requireActivity(), new Observer<List<CompletedNotes>>() {
            @Override
            public void onChanged(List<CompletedNotes> notes) {

                //update Recycler View
                adapter.setNotes(notes);

            }
        });

        return view;
    }
}