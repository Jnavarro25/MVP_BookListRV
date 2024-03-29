package com.example.practicalibrosreciclerview.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.practicalibrosreciclerview.BookDetail;
import com.example.practicalibrosreciclerview.Model.Book;
import com.example.practicalibrosreciclerview.Model.DataAdapter;
import com.example.practicalibrosreciclerview.Presenter.Presenter;
import com.example.practicalibrosreciclerview.R;
import com.example.practicalibrosreciclerview.interfaces.Contract;

import java.util.ArrayList;

/**
 * Esta clase + el archivo XML se consideran la VISTA (VIEW)
 */
public class BookListActivity extends AppCompatActivity implements Contract.PresenterToView
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Presenter presenter;
    private Context context= this;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_book_list);
        presenter = new Presenter(context);
        presenter.makeRequest();
        makeComponentsView();
        showData();
    }

    @Override
    public void makeComponentsView()
    {
        recyclerView = findViewById(R.id.rV_bookList);
        linearLayoutManager = new LinearLayoutManager(context);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showData()
    {
        final ArrayList<Book> books = presenter.returnList();
        DataAdapter dataAdapter = new DataAdapter(books);
        recyclerView.setAdapter(dataAdapter);

        dataAdapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent detailIntent = new Intent(context, BookDetail.class);
                int position= recyclerView.getChildAdapterPosition(view);
                detailIntent.putExtra("bookInfo",books.get(position));
                startActivity(detailIntent);
                System.out.println();
            }
        });
    }

}
