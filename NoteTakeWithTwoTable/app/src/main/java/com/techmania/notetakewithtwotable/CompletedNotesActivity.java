package com.techmania.notetakewithtwotable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.techmania.notetakewithtwotable.Fragments.CompletedNoteFragment;

public class CompletedNotesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_completed_notes);
    /*
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        CompletedNoteFragment noteFragment = new CompletedNoteFragment();
        ft.add(R.id.frame,noteFragment);
        ft.commit();

     */



    }
}