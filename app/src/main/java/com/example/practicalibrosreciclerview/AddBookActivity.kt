package com.example.practicalibrosreciclerview

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.practicalibrosreciclerview.model.Book
import com.example.practicalibrosreciclerview.model.OpenHelperDataBase
import com.example.practicalibrosreciclerview.presenter.Presenter
import com.example.practicalibrosreciclerview.view.BookListActivity
import java.util.*

class AddBookActivity : AppCompatActivity(), Presenter.View {
    private var presenter: Presenter? = null
    private var progressDialog: ProgressDialog? = null
    private var book: Book? = null
    private var bntAddBook: Button? = null
    private var isbn: EditText? = null
    private var bookTitle: EditText? = null
    private var urlImage: EditText? = null
    private var authorName: EditText? = null
    private var editor: EditText? = null
    private var pagesNumber: EditText? = null
    private var descriptionBook: EditText? = null
    private var category: EditText? = null
    private var cancel = false
    private var openHelperDataBase: OpenHelperDataBase? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_book)
        openHelperDataBase = OpenHelperDataBase(MyApp.context)
        presenter = Presenter()
        presenter!!.setView(this)
        makeComponentsView()
        bntAddBook!!.setOnClickListener {
            val newBook = makeBook()
            cancel = validateBookData(newBook)
            if (!cancel) {
                openHelperDataBase!!.addBookToPersitence(newBook.isbn, newBook.title,
                        newBook.authorFirstName, newBook.authorLastName, newBook.category,
                        newBook.published, newBook.publisher, newBook.pagesNumber, newBook.description, newBook.imageUrl)
                val data= presenter!!.convertData(newBook)
                presenter!!.makeRequestPost(data)
            } else {
                Toast.makeText(this@AddBookActivity, getString(R.string.error_toast_form), Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun showData(books: ArrayList<Book>?) {}

    override fun showProgress() {
        progressDialog!!.show()
    }

    override fun hideProgress() {
       progressDialog!!.dismiss()
    }

    override fun showErrorMessage() {
        val alertDialog = AlertDialog.Builder(this@AddBookActivity)
                alertDialog.setTitle(getString(R.string.warning_title))
                alertDialog.setMessage(getString(R.string.warning_body))
                alertDialog.setPositiveButton(getString(R.string.warning_option)) { dialog, _ -> dialog.dismiss() }
                alertDialog.setIcon(R.drawable.ic_warning_black_24dp)

        val dialog = alertDialog.create()
                dialog.show()
    }

    override fun makeRefresh() {
        val refresh = Intent(this, BookListActivity::class.java)
        startActivity(refresh)
        finish()
    }

    private fun makeComponentsView() {
        progressDialog = ProgressDialog(this@AddBookActivity)
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.setMessage(getString(R.string.pd_message_add_book))
        progressDialog!!.isIndeterminate = true
        bntAddBook = findViewById(R.id.btn_agregar)
    }

     private fun makeBook(): Book {
         var authorNameFirst = ""
        var authorNameSecond = ""
         val dateS: String


         isbn = findViewById(R.id.et_isbn)
         val isbnS: String = isbn!!.text.toString()

        bookTitle = findViewById(R.id.et_title)
         val bookTitleS: String = bookTitle!!.text.toString()

        authorName = findViewById(R.id.et_author)
         val authorNameS: String = authorName!!.text.toString()
        if (!TextUtils.isEmpty(authorNameS)) {
            if (authorNameS.contains(" ")) {
                val parts = authorNameS.split(" ").toTypedArray()
                authorNameFirst = parts[0]
                authorNameSecond = parts[1]
            }
        }
        val date = findViewById<EditText>(R.id.et_fecha_pub)
        dateS = date.text.toString()
        editor = findViewById(R.id.et_editorial)
         val editorS: String = editor!!.text.toString()
        pagesNumber = findViewById(R.id.et_pages_number)
         val pagesNumberS: String = pagesNumber!!.text.toString()
        descriptionBook = findViewById(R.id.et_description)
         val descriptionBookS: String = descriptionBook!!.text.toString()
        urlImage = findViewById(R.id.et_image_url)
         val urlImageS: String = urlImage!!.text.toString()
        category = findViewById(R.id.et_category)
         val categoryS: String = category!!.text.toString()


        return Book("", isbnS, bookTitleS, authorNameFirst, authorNameSecond, dateS, editorS, pagesNumberS, descriptionBookS, urlImageS, categoryS).also { book = it }
    }

    private fun validateBookData(newBook: Book): Boolean {
        var flag = false
        if (TextUtils.isEmpty(newBook.isbn)) {
            isbn!!.error = getString(R.string.et_empty)
            flag = true
        } else if (newBook.isbn.length < 10) {
            isbn!!.error = getString(R.string.et_isbn_error_tamanos)
            flag = true
        } else if (!TextUtils.isDigitsOnly(newBook.isbn)) {
            isbn!!.error = getString(R.string.et_isbn_error_lertras)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.imageUrl)) {
            urlImage!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.title)) {
            bookTitle!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.authorFirstName) && TextUtils.isEmpty(newBook.authorLastName)) {
            authorName!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.category)) {
            category!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.description)) {
            descriptionBook!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.pagesNumber)) {
            pagesNumber!!.error = getString(R.string.et_empty)
            flag = true
        }
        if (TextUtils.isEmpty(newBook.publisher)) {
            editor!!.error = getString(R.string.et_empty)
            flag = true
        }
        return flag
    }
}