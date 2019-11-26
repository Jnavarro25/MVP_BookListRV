package com.example.practicalibrosreciclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.practicalibrosreciclerview.Model.Book;
import com.example.practicalibrosreciclerview.interfaces.Contract;
import com.squareup.picasso.Picasso;


public class BookDetail extends AppCompatActivity implements Contract.PresenterToView
{
    private ImageView bookImage;
    private TextView bookTitle,authorName,cathegory,date,pagesNumber,isbn,descriptionBook;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        makeComponentsView();
        showData();
    }


    @Override
    public void makeComponentsView()
    {
        bookImage= findViewById(R.id.iv_image_book);
        bookTitle = findViewById(R.id.tv_book);
        authorName  =findViewById(R.id. tv_author);
        cathegory=findViewById(R.id.tv_category);
        date =findViewById(R.id.tv_date);
        pagesNumber=findViewById(R.id.tv_pages_number);
        isbn=findViewById(R.id.tv_isbn);
        descriptionBook=findViewById(R.id.tv_description);


    }

    @Override
    public void showData() {
        Book book = (Book) getIntent().getSerializableExtra("bookInfo");
        Picasso.with(this).load(book.getUrlImage()).into(bookImage);
        bookTitle.setText("Titulo:"+" "+book.getTitle());
        authorName.setText("Autor:"+" "+book.getAuthor());
        cathegory.setText("Categoria:"+" "+book.getCategory());
        date.setText("Fecha de publicación:"+" "+book.getCreatedOn());
        pagesNumber.setText("Numero de paginas:"+" "+book.getPagesNumber());
        isbn.setText("Isbn:"+" "+book.getIsbn());
        descriptionBook.setText("Descripcion general:"+" "+book.getDescription());
    }
}
