package com.example.practicalibrosreciclerview.view

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.View.OnLongClickListener
import android.view.inputmethod.EditorInfo
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener
import com.example.practicalibrosreciclerview.AddBookActivity
import com.example.practicalibrosreciclerview.BookDetailActivity
import com.example.practicalibrosreciclerview.R
import com.example.practicalibrosreciclerview.model.Book
import com.example.practicalibrosreciclerview.model.DataAdapter
import com.example.practicalibrosreciclerview.presenter.Presenter
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.util.*

class BookListActivity : AppCompatActivity(), Presenter.View, OnRefreshListener {
    private lateinit var recyclerView: RecyclerView
    private val context: Context = this
    private var dataAdapter: DataAdapter? = null
    private var progressDialog: ProgressDialog? = null
    private var presenter: Presenter? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rv_book_list)
        presenter = Presenter()
        presenter!!.setView(this)
        makeComponentsView()
        presenter!!.makeRequestGet()
        swipeRefreshLayout!!.setOnRefreshListener(this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.search_menu, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextSubmit(query: String): Boolean {
                        if (dataAdapter != null) dataAdapter!!.filter.filter(query)
                        return false
                    }

                    override fun onQueryTextChange(newText: String): Boolean {
                        if (dataAdapter != null) {
                            dataAdapter!!.filter.filter(newText)
                        }
                        return false
                    }
                })
        return true
    }

    override fun showData(books: ArrayList<Book>?) {
        dataAdapter = DataAdapter(books!!)
        recyclerView.adapter = dataAdapter
        dataAdapter!!.setOnClickListener(
                View.OnClickListener { view ->
                    val detailIntent = Intent(context, BookDetailActivity::class.java)
                    val position = recyclerView.getChildAdapterPosition(view)
                    detailIntent.putExtra("bookInfo", books[position])
                    startActivity(detailIntent)
                })
        dataAdapter!!.setOnLongClickListener(OnLongClickListener { v ->
            val bookToDelete = recyclerView.getChildAdapterPosition(v)
            AlertDialog.Builder(this@BookListActivity)
                    .setTitle(getString(R.string.alert_title))
                    .setMessage(getString(R.string.alert_body, books[bookToDelete].title))
                    .setPositiveButton(getString(R.string.alert_option_yes)) { _, _ ->
                        val idToDelete = books[bookToDelete].id
                        presenter!!.deleteBook(idToDelete)
                    }.setNegativeButton(getString(R.string.alert_option_no)) { dialog, _ -> dialog.dismiss() }.setIcon(R.drawable.ic_warning_black_24dp)
                    .show()
            false
        })
    }

    override fun showProgress() {
        progressDialog!!.show()
    }

    override fun hideProgress() {
        progressDialog!!.dismiss()
    }

    override fun showErrorMessage() {
        AlertDialog.Builder(this@BookListActivity)
                .setTitle(getString(R.string.warning_title))
                .setMessage(getString(R.string.warning_body_internet))
                .setPositiveButton(getString(R.string.warning_option)) { dialog, _ ->
                    dialog.dismiss()
                    //presenter.makeRequestGet();
                }.setIcon(R.drawable.ic_warning_black_24dp)
                .show()
    }

    override fun makeRefresh() {
        val refresh = Intent(this, BookListActivity::class.java)
        startActivity(refresh)
        finish()
    }

    private fun makeComponentsView() {
        recyclerView = findViewById(R.id.rV_bookList)
        val linearLayoutManager = LinearLayoutManager(context)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = linearLayoutManager

        progressDialog = ProgressDialog(this@BookListActivity)
        progressDialog!!.setProgressStyle(ProgressDialog.STYLE_SPINNER)
        progressDialog!!.setMessage(getString(R.string.pd_message))
        progressDialog!!.isIndeterminate = true

        val floatingActionButton = findViewById<FloatingActionButton>(R.id.fab_add_book)
        floatingActionButton.setOnClickListener {
            val listToAdd = Intent(context, AddBookActivity::class.java)
            startActivity(listToAdd)
        }
        swipeRefreshLayout = findViewById(R.id.srf_swipe_update)
    }

    override fun onRefresh() {
        presenter!!.makeRequestGet()
        swipeRefreshLayout!!.isRefreshing = false
    }
}