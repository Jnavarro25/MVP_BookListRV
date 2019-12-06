package com.example.practicalibrosreciclerview.model;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class OpenHelperDataBase extends SQLiteOpenHelper {

    private static final String DB_NAME = "book_list.db";
    private static final int DB_VERSION = 1;

    private static final String BOOK_TABLE =
            "CREATE TABLE `book` (" +
                    "`isbn` TEXT NOT NULL," +
                    "`title` TEXT NOT NULL," +
                    "`author_first_name` TEXT NOT NULL," +
                    "`author_last_name` TEXT NOT NULL," +
                    "`category` TEXT NOT NULL," +
                    "`published` TEXT NOT NULL," +
                    "`publisher` TEXT NOT NULL," +
                    "`pages` TEXT NOT NULL," +
                    "`description` TEXT NOT NULL," +
                    "`image_url` TEXT NOT NULL" +
                    ");";

    public OpenHelperDataBase(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(BOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS book;");
        db.execSQL(BOOK_TABLE);

    }

    public boolean addBookToPersitence(String isbn, String title, String author_first_name,
                                       String author_last_name, String category, String published,
                                       String publisher, String pages, String description,
                                       String image_url) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("INSERT INTO book(isbn,title,author_first_name,author_last_name," +
                    "category, published,publisher,pages,description,image_url)" +
                    "VALUES('" + isbn + "','" + title + "','" + author_first_name + "'," +
                    "'" + author_last_name + "','" + category + "','" + published + "','" + publisher + "'," +
                    "'" + pages + "','" + description + "'," +
                    "'" + image_url + "')");
            db.close();
            return true;
        } else {
            return false;
        }
    }
}
