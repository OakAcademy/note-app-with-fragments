package com.techmania.notetakewithtwotable.Adapters;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.techmania.notetakewithtwotable.R;
import com.techmania.notetakewithtwotable.RoomDatabase.Entities.Note;
import com.techmania.notetakewithtwotable.UpdateNoteActivity;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteHolder> {

    private List<Note> notes = new ArrayList<>();
    Activity activity;


    public NoteAdapter(Activity activity) {
        this.activity = activity;
    }

    @NonNull
    @Override
    public NoteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.note_item,parent,false);

        return new NoteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteHolder holder, int position) {

        Note currentNote = notes.get(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewTime.setText(currentNote.getTime());

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, UpdateNoteActivity.class);
                intent.putExtra("id",currentNote.getId());
                intent.putExtra("title",currentNote.getTitle());
                intent.putExtra("description",currentNote.getDescription());
                activity.startActivityForResult(intent,2);
            }
        });

    }

    @Override
    public int getItemCount() {
        return notes.size();
    }

    public void setNotes(List<Note> myNotes)
    {

        this.notes = myNotes;
        notifyDataSetChanged();

    }

    public Note getNotes(int position)
    {
        return notes.get(position);
    }

    static class NoteHolder extends RecyclerView.ViewHolder
    {

        TextView textViewTitle;
        TextView textViewTime;
        TextView textViewDescription;
        CardView cardView;

        public NoteHolder(@NonNull View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewDescription = itemView.findViewById(R.id.textViewDescription);
            cardView = itemView.findViewById(R.id.cardView);
            textViewTime = itemView.findViewById(R.id.textViewTime);

        }
    }
}
