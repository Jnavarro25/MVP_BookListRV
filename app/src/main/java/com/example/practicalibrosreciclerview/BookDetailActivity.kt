package com.example.practicalibrosreciclerview

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.practicalibrosreciclerview.model.Book
import com.squareup.picasso.Picasso

class BookDetailActivity : AppCompatActivity() {

    private var bookImage: ImageView? = null
    private var bookTitle: TextView? = null
    private var authorName: TextView? = null
    private var category: TextView? = null
    private var date: TextView? = null
    private var pagesNumber: TextView? = null
    private var isbn: TextView? = null
    private var descriptionBook: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book_detail)
        makeComponentsView()
        showData()
    }

     private fun makeComponentsView() {
        bookImage = findViewById(R.id.iv_image_book)
        bookTitle = findViewById(R.id.tv_book)
        authorName = findViewById(R.id.tv_author)
        category = findViewById(R.id.tv_category)
        date = findViewById(R.id.tv_date)
        pagesNumber = findViewById(R.id.tv_pages_number)
        isbn = findViewById(R.id.tv_isbn)
        descriptionBook = findViewById(R.id.tv_description)
    }

    private fun showData() {
        val book = intent.getSerializableExtra("bookInfo") as Book
        Picasso.get().load(book.imageUrl).into(bookImage)
        bookTitle!!.text = getString(R.string.book_title, book.title)
        authorName!!.text = getString(R.string.author_name, book.authorFirstName, book.authorLastName)
        category!!.text = getString(R.string.category, book.category)
        date!!.text = getString(R.string.date, book.published)
        pagesNumber!!.text = getString(R.string.pages_number, book.pagesNumber)
        isbn!!.text = getString(R.string.isbn, book.isbn)
        descriptionBook!!.text = getString(R.string.description, book.description)
    }
}