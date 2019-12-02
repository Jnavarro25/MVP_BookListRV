package com.example.practicalibrosreciclerview;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.presenter.Presenter;
import com.example.practicalibrosreciclerview.view.BookListActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class AddBookActivity extends AppCompatActivity implements  Presenter.View{


    private Presenter presenter;
    private ProgressDialog progressDialog;
    private Book book;
    private Button bntAddBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        presenter = new Presenter();
        presenter. setView(this);
        makeComponentsView();


        bntAddBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                makeBook();
                HashMap data = presenter.convertData(book);
                presenter.makeRequestPost(data);

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

        Toast.makeText(this,"No se pudo conectar con el servicio",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void makeRefresh()
    {
        Intent refresh = new Intent(this, BookListActivity.class);
        startActivity(refresh);
        finish();
    }

    private void makeComponentsView() {


        progressDialog= new ProgressDialog(AddBookActivity.this);
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progressDialog.setMessage(getString(R.string.pd_message_add_book));
        progressDialog.setIndeterminate(true);
        bntAddBook = findViewById(R.id.btn_agregar);
    }

    public void makeBook(){

        String  bookTitleS, authorNameS,authorNameFirst,authorNameSecond, categoryS, dateS, pagesNumberS, isbnS, descriptionBookS, urlImageS, editorS;

        EditText isbn = findViewById(R.id.et_isbn);
        isbnS = isbn.getText().toString();

        EditText bookTitle = findViewById(R.id.et_title);
        bookTitleS= bookTitle.getText().toString();

        EditText authorName = findViewById(R.id.et_author);
        authorNameS = authorName.getText().toString();

        String[] parts = authorNameS.split(" ");
        authorNameFirst = parts[0];
        authorNameSecond = parts[1];

        EditText date = findViewById(R.id.et_fecha_pub);
        dateS= date.getText().toString();

        EditText editor = findViewById(R.id.et_editorial);
        editorS= editor.getText().toString();

        EditText pagesNumber = findViewById(R.id.et_pages_number);
        pagesNumberS = pagesNumber.getText().toString();

        EditText descriptionBook = findViewById(R.id.et_description);
        descriptionBookS = descriptionBook.getText().toString();

        EditText urlImage = findViewById(R.id.et_image_url);
        urlImageS = urlImage.getText().toString();

        EditText category = findViewById(R.id.et_category);
        categoryS= category.getText().toString();

        book = new Book("",isbnS,bookTitleS,authorNameFirst,authorNameSecond,dateS,editorS,pagesNumberS,descriptionBookS,urlImageS,categoryS);
    }

}
