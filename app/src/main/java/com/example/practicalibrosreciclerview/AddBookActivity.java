package com.example.practicalibrosreciclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.OpenHelperDataBase;
import com.example.practicalibrosreciclerview.presenter.Presenter;
import com.example.practicalibrosreciclerview.view.BookListActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AddBookActivity extends AppCompatActivity implements Presenter.View {


    private Presenter presenter;
    private ProgressDialog progressDialog;
    private Book book;
    private Button bntAddBook;
    private EditText isbn, bookTitle, urlImage, authorName, editor, pagesNumber, descriptionBook, category;
    private boolean cancel = false;
    private OpenHelperDataBase openHelperDataBase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        this.openHelperDataBase = new OpenHelperDataBase(MyApp.getContext());
        presenter = new Presenter();
        presenter.setView(this);
        makeComponentsView();


        bntAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Book newBook = makeBook();
                //TODO: Realizar la validacion del campo fecha
                cancel = validateBookData(newBook);

                if (!cancel) {
                    openHelperDataBase.addBookToPersitence(newBook.getIsbn(), newBook.getTitle(),
                            newBook.getAuthorFirstName(), newBook.getAuthorLastName(), newBook.getCategory(),
                            newBook.getPublished(), newBook.getPublisher(), newBook.getPagesNumber(), newBook.getDescription(), newBook.getImageUrl());
                    HashMap data = presenter.convertData(newBook);
                    presenter.makeRequestPost(data);
                } else {
                    Toast.makeText(AddBookActivity.this, "Error al llenar el formulario", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void showData(ArrayList<Book> books) {


    }

    @Override
    public void showProgres() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showErrorMessage() {

        new AlertDialog.Builder(AddBookActivity.this)
                .setTitle("¡Atencion!")
                .setMessage("No es posible agregar un libro si no tienes conexión a internet")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).setIcon(R.drawable.ic_warning_black_24dp)
                .show();
    }

    @Override
    public void makeRefresh() {
        Intent refresh = new Intent(this, BookListActivity.class);
        startActivity(refresh);
        finish();
    }

    private void makeComponentsView() {
        progressDialog = new ProgressDialog(AddBookActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getString(R.string.pd_message_add_book));
        progressDialog.setIndeterminate(true);
        bntAddBook = findViewById(R.id.btn_agregar);
    }

    public Book makeBook() {

        String bookTitleS, authorNameS, authorNameFirst = "", authorNameSecond = "", categoryS, dateS, pagesNumberS, isbnS, descriptionBookS, urlImageS, editorS;

        isbn = findViewById(R.id.et_isbn);
        isbnS = isbn.getText().toString();

        bookTitle = findViewById(R.id.et_title);
        bookTitleS = bookTitle.getText().toString();

        authorName = findViewById(R.id.et_author);
        authorNameS = authorName.getText().toString();

        if (!TextUtils.isEmpty(authorNameS))
        {
            if(authorNameS.contains(" ")){
                String[] parts = authorNameS.split(" ");
                authorNameFirst = parts[0];
                authorNameSecond = parts[1];
            }

        }


        EditText date = findViewById(R.id.et_fecha_pub);
        dateS = date.getText().toString();

        editor = findViewById(R.id.et_editorial);
        editorS = editor.getText().toString();

        pagesNumber = findViewById(R.id.et_pages_number);
        pagesNumberS = pagesNumber.getText().toString();

        descriptionBook = findViewById(R.id.et_description);
        descriptionBookS = descriptionBook.getText().toString();

        urlImage = findViewById(R.id.et_image_url);
        urlImageS = urlImage.getText().toString();

        category = findViewById(R.id.et_category);
        categoryS = category.getText().toString();


        return book = new Book("", isbnS, bookTitleS, authorNameFirst, authorNameSecond, dateS, editorS, pagesNumberS, descriptionBookS, urlImageS, categoryS);

    }

    private boolean validateBookData(Book newBook) {

        boolean flag = false;

        if (TextUtils.isEmpty(newBook.getIsbn())) {
            isbn.setError(getString(R.string.et_empty));
            flag = true;
        } else if (newBook.getIsbn().length() < 10) {
            isbn.setError(getString(R.string.et_isbn_error_tamanos));
            flag = true;
        } else if (!TextUtils.isDigitsOnly(book.getIsbn())) {
            isbn.setError(getString(R.string.et_isbn_error_lertras));
            flag = true;
        }

        if (TextUtils.isEmpty(newBook.getImageUrl())) {
            urlImage.setError(getString(R.string.et_empty));
            flag = true;
        }

        if (TextUtils.isEmpty(newBook.getTitle())) {
            bookTitle.setError(getString(R.string.et_empty));
            flag = true;
        }

        if (TextUtils.isEmpty(newBook.getAuthorFirstName()) && TextUtils.isEmpty(newBook.getAuthorLastName())) {
            authorName.setError(getString(R.string.et_empty));
            flag = true;
        }

        if (TextUtils.isEmpty(newBook.getCategory())) {
            category.setError(getString(R.string.et_empty));
            flag = true;
        }
        if (TextUtils.isEmpty(newBook.getDescription())) {
            descriptionBook.setError(getString(R.string.et_empty));
            flag = true;
        }
        if (TextUtils.isEmpty(newBook.getPagesNumber())) {
            pagesNumber.setError(getString(R.string.et_empty));
            flag = true;
        }

        if (TextUtils.isEmpty(newBook.getPublisher())) {
            editor.setError(getString(R.string.et_empty));
            flag = true;
        }

        return flag;
    }

}
