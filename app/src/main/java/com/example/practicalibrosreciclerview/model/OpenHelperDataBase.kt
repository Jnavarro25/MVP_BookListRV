package com.example.practicalibrosreciclerview.model

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class OpenHelperDataBase(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(BOOK_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS book;")
        db.execSQL(BOOK_TABLE)
    }

    fun addBookToPersitence(isbn: String, title: String, author_first_name: String,
                            author_last_name: String, category: String, published: String,
                            publisher: String, pages: String, description: String,
                            image_url: String) {
        val db = writableDatabase
        if (db != null) {
            db.execSQL("INSERT INTO book(isbn,title,author_first_name,author_last_name," +
                    "category, published,publisher,pages,description,image_url)" +
                    "VALUES('" + isbn + "','" + title + "','" + author_first_name + "'," +
                    "'" + author_last_name + "','" + category + "','" + published + "','" + publisher + "'," +
                    "'" + pages + "','" + description + "'," +
                    "'" + image_url + "')")
            db.close()
        }
    }

    companion object {
        private const val DB_NAME = "book_list.db"
        private const val DB_VERSION = 1
        private const val BOOK_TABLE = "CREATE TABLE `book` (" +
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
                ");"
    }
}