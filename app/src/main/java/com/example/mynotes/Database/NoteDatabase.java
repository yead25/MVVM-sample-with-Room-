package com.example.mynotes.Database;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Note.class}, version = 1)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(final Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
            Log.d("Here" ,"getInstance: Database hoise");
        }

        return instance;
    }

    private static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDb(instance).execute();
        }
    };

    private static class PopulateDb extends AsyncTask<Void, Void, Void> {
        private NoteDao noteDao;

        public PopulateDb(NoteDatabase noteDatabase) {

            this.noteDao = noteDatabase.noteDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            noteDao.deletAll();
            Log.d("Here", "doInBackground: ");
            noteDao.insert(new Note("Example 1","This is for Example 1","16  july, 2020"));


            return null;
        }
    }

}
