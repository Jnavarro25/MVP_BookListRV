package com.example.practicalibrosreciclerview.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;

import com.example.practicalibrosreciclerview.Model.DataAdapter;
import com.example.practicalibrosreciclerview.Presenter.Presenter;
import com.example.practicalibrosreciclerview.R;
import com.example.practicalibrosreciclerview.interfaces.Contract;

/**
 * Esta clase + el archivo XML se consideran la VISTA (VIEW)
 */
public class BookListActivity extends AppCompatActivity implements Contract.PresenterToView
{
    private RecyclerView recyclerView;
    private LinearLayoutManager linearLayoutManager;
    private Presenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rv_book_list);

        presenter = new Presenter(this);
        presenter.fillList();
        makeReciclerView();
        showList();
    }

    @Override
    public void makeReciclerView()
    {
        recyclerView = findViewById(R.id.rV_bookList);
        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void showList()
    {
        DataAdapter dataAdapter = new DataAdapter(presenter.returnList());
        recyclerView.setAdapter(dataAdapter);
    }
}
