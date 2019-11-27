package com.example.practicalibrosreciclerview;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.practicalibrosreciclerview.model.Book;
import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {
  private ImageView bookImage;
  private TextView bookTitle, authorName, category, date, pagesNumber, isbn, descriptionBook;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_book_detail);
    makeComponentsView();
    showData();
  }

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

  public void showData() {
    Book book = (Book) getIntent().getSerializableExtra("bookInfo");
    if (book != null) {
      Picasso.get().load(book.getUrlImage()).into(bookImage);
      bookTitle.setText(getString(R.string.book_title, book.getTitle()));
      authorName.setText(getString(R.string.author_name, book.getAuthor()));
      category.setText(getString(R.string.category, book.getCategory()));
      date.setText(getString(R.string.date, book.getCreatedOn()));
      pagesNumber.setText(getString(R.string.pages_number, book.getPagesNumber()));
      isbn.setText(getString(R.string.isbn, book.getIsbn()));
      descriptionBook.setText(getString(R.string.description, book.getDescription()));
    }
  }
}
