package com.example.practicalibrosreciclerview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicalibrosreciclerview.model.Book;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity  {
    private ImageView bookImage;
    private TextView bookTitle, authorName, category, date, pagesNumber, isbn, descriptionBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        makeComponentsView();
        showData();
    }

    @Override
    public void makeComponentsView() {
        bookImage = findViewById(R.id.iv_image_book);
        bookTitle = findViewById(R.id.tv_book);
        authorName = findViewById(R.id.tv_author);
        category = findViewById(R.id.tv_category);
        date = findViewById(R.id.tv_date);
        pagesNumber = findViewById(R.id.tv_pages_number);
        isbn = findViewById(R.id.tv_isbn);
        descriptionBook = findViewById(R.id.tv_description);
    }

    @Override
    public void showData() {
        Book book = (Book) getIntent().getSerializableExtra("bookInfo");
        if (book != null) {
            Picasso.with(this).load(book.getUrlImage()).into(bookImage);
            bookTitle.setText(getString(R.string.book_title, book.getTitle()));
            authorName.setText("Autor:" + " " + book.getAuthor());
            category.setText("Categoria:" + " " + book.getCategory());
            date.setText("Fecha de publicación:" + " " + book.getCreatedOn());
            pagesNumber.setText("Numero de paginas:" + " " + book.getPagesNumber());
            isbn.setText("Isbn:" + " " + book.getIsbn());
            descriptionBook.setText("Descripcion general:" + " " + book.getDescription());
        }
    }
}
