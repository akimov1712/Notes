package com.example.notes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.notes.dao.NotesDao;
import com.example.notes.models.NoteModel;

// БАЗА ДАННЫХ. Создаем абстрактный класс, который расширяет класс Room Datavase

@Database(entities = NoteModel.class, version = 1, exportSchema = false)/* ентити это таблица, мы ее создали в классе NoteModel, версия понятно, exportSchema
она экспортирует схему этого класса в JSON
*/
public abstract class NotesDatabase extends RoomDatabase {
    private static NotesDatabase database; // создаем пустую базу данных
    private static final String DB_NAME = "notes2.db"; // добавляем в переменнуб названия базы данных, 2 потомучто у нас уже есть база notes.db
    private static final Object LOCK = new Object(); // создаем OBJECT замок для синхронизации, если мы будем создавать базу из двух разных потоков, то
    // она создастся в 2 экземплярах, нам это не нужно, поэтому создаем замок, если 2 поток увидет, что замок уже занят, тогда он не будет создавать еще одну базу.

    public static NotesDatabase getInstance(Context context){ // метод возвращает базу данных(строит ее) если она пустая.
        synchronized (LOCK) {
            if (database == null){
                database = Room.databaseBuilder(context,NotesDatabase.class,DB_NAME).build();
            }
        }
        return database;
    }

    public abstract NotesDao notesDao(); // добавляем интрефейс с методами для использования БД

}
