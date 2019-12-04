package com.example.practicalibrosreciclerview.view;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.SearchView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.practicalibrosreciclerview.AddBookActivity;
import com.example.practicalibrosreciclerview.BookDetailActivity;
import com.example.practicalibrosreciclerview.R;
import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.DataAdapter;
import com.example.practicalibrosreciclerview.presenter.Presenter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

/** Esta clase + el archivo XML se consideran la VISTA (VIEW) */
public class BookListActivity extends AppCompatActivity implements Presenter.View {

  private RecyclerView recyclerView;
  private Context context = this;
  private DataAdapter dataAdapter;
  private ProgressDialog progressDialog;
  private Presenter presenter;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_rv_book_list);
    presenter = new Presenter();
    presenter.setView(this);
    makeComponentsView();
    presenter.makeRequestGet();
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {

    MenuInflater inflater = getMenuInflater();
    inflater.inflate(R.menu.search_menu, menu);

    MenuItem searchItem = menu.findItem(R.id.action_search);
    SearchView searchView = (SearchView) searchItem.getActionView();

    searchView.setImeOptions(EditorInfo.IME_ACTION_DONE);

    searchView.setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override
          public boolean onQueryTextSubmit(String query) {
            dataAdapter.getFilter().filter(query);
            return false;
          }

          @Override
          public boolean onQueryTextChange(String newText) {
            dataAdapter.getFilter().filter(newText);
            return false;
          }
        });
    return true;
  }

  @Override
  public void showData(final ArrayList<Book> books) {
    dataAdapter = new DataAdapter(books);
    recyclerView.setAdapter(dataAdapter);

    dataAdapter.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {

            Intent detailIntent = new Intent(context, BookDetailActivity.class);
            int position = recyclerView.getChildAdapterPosition(view);
            detailIntent.putExtra("bookInfo", books.get(position));
            startActivity(detailIntent);
          }
        });



    dataAdapter.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        final int bookToDelete = recyclerView.getChildAdapterPosition(v);
        //Toast.makeText(BookListActivity.this,"Esto es un click largo",Toast.LENGTH_SHORT).show();
        //presenter.deleteBook(recyclerView.getChildAdapterPosition(v));

        new AlertDialog.Builder(BookListActivity.this)
                .setTitle("¡Alerta!")
                .setMessage("Estas seguro que deseas eliminar: "+books.get(bookToDelete).getTitle())
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {

                   String idToDelete = books.get(bookToDelete).getId();
                    presenter.deleteBook(idToDelete);

                  }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                  }
        }).setIcon(R.drawable.ic_warning_black_24dp)
                .show();


        return false;
      }
    });


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
    Toast.makeText(this, "Servicio no disponible", Toast.LENGTH_LONG).show();
  }

  @Override
  public void makeRefresh() {
    Intent refresh = new Intent(this, BookListActivity.class);
    startActivity(refresh);
  }

  public void makeComponentsView() {
    recyclerView = findViewById(R.id.rV_bookList);
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
    recyclerView.setHasFixedSize(true);
    recyclerView.setLayoutManager(linearLayoutManager);
    progressDialog = new ProgressDialog(BookListActivity.this);
    progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    progressDialog.setMessage(getString(R.string.pd_message));
    progressDialog.setIndeterminate(true);
    FloatingActionButton floatingActionButton = findViewById(R.id.fab_add_book);

    floatingActionButton.setOnClickListener(
        new View.OnClickListener() {
          @Override
          public void onClick(View view) {
            Intent listToAdd = new Intent(context, AddBookActivity.class);
            startActivity(listToAdd);
          }
        });
  }
}
