package com.example.mynotes;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mynotes.Database.Note;
import com.example.mynotes.Repository.NoteRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

    private NoteRepository repository;
    private LiveData<List<Note>> mAllNotes;

    public ViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        mAllNotes = repository.getmAllNotes();
    }

    public LiveData<List<Note>> getmAllNotes() {
        return mAllNotes;
    }
    public void insert(Note note){
        repository.insert(note);
    }
}
