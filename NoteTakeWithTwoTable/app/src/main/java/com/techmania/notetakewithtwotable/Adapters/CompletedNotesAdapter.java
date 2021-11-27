package com.techmania.notetakewithtwotable.Adapters;

import android.annotation.SuppressLint;
import android.app.Application;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.techmania.notetakewithtwotable.R;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.CompletedNotes;
import com.techmania.notetakewithtwotable.RoomDatabase.ViewModel.NoteViewModel;

import java.util.ArrayList;
import java.util.List;

public class CompletedNotesAdapter extends RecyclerView.Adapter<CompletedNotesAdapter.CompletedNoteHolder> {

    private List<CompletedNotes> notes = new ArrayList<>();
    Application application;

    public CompletedNotesAdapter(Application application) {
        this.application = application;
    }

    private NoteViewModel noteViewModel = new ViewModelProvider.AndroidViewModelFactory(application)
            .create(NoteViewModel.class);

    @NonNull
    @Override
    public CompletedNoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.completed_card_design,parent,false);
        return new CompletedNoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CompletedNoteHolder holder, int position) {

        CompletedNotes currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getcTitle());
        holder.textViewDescription.setText(currentNote.getcDescription());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noteViewModel.deleteCompletedNotes(getNotes(holder.getAdapterPosition()));

            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNotes(List<CompletedNotes> myNotes)
    {

        this.notes = myNotes;
        notifyDataSetChanged();

    }

    public CompletedNotes getNotes(int position)
    {
        return notes.get(position);
    }

    static class CompletedNoteHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle;
        TextView textViewDescription;
        ImageView imageView;

        public CompletedNoteHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.compTextViewTitle);
            textViewDescription = itemView.findViewById(R.id.compTextViewDescription);
            imageView = itemView.findViewById(R.id.imageViewDelete);

        }
    }
}
