package com.example.practicalibrosreciclerview.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SearchView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.practicalibrosreciclerview.BookDetailActivity;
import com.example.practicalibrosreciclerview.R;
import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.DataAdapter;
import com.example.practicalibrosreciclerview.presenter.Presenter;

import java.util.ArrayList;

/** Esta clase + el archivo XML se consideran la VISTA (VIEW) */
public class BookListActivity extends AppCompatActivity
        implements Presenter.View, SearchView.OnQueryTextListener {
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Presenter presenter;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_book_list);
        presenter = new Presenter();
        presenter.setView(this);
        presenter.makeRequest();
        makeComponentsView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_menu, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(item);
        // searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public void showData() {
        final ArrayList<Book> books = presenter.returnList();
        DataAdapter dataAdapter = new DataAdapter(books);
        recyclerView.setAdapter(dataAdapter);

        dataAdapter.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        Intent detailIntent = new Intent(context, BookDetailActivity.class);
                        int position = recyclerView.getChildAdapterPosition(view);
                        detailIntent.putExtra("bookInfo", books.get(position));
                        startActivity(detailIntent);
                        System.out.println();
                    }
                });
    }

    @Override
    public boolean onQueryTextSubmit(String s) {

        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {

        return true;
    }

    public void makeComponentsView() {
        recyclerView = findViewById(R.id.rV_bookList);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }
}
