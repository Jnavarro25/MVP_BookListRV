package com.example.practicalibrosreciclerview.model

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.practicalibrosreciclerview.MyApp
import com.example.practicalibrosreciclerview.R
import com.example.practicalibrosreciclerview.model.DataAdapter.ViewHolderData
import com.squareup.picasso.Picasso
import kotlin.collections.ArrayList

class DataAdapter(val bookList: ArrayList<Book>) : RecyclerView.Adapter<ViewHolderData>(), View.OnClickListener, Filterable, OnLongClickListener {

    private lateinit var bookListFull: ArrayList<Book>
    private var listener: View.OnClickListener? = null
    private var longListener: OnLongClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderData {
        bookListFull = ArrayList<Book>()
        val context = parent.context
        val itemView = LayoutInflater.from(context).inflate(R.layout.cv_item_book, parent, false)
        itemView.setOnClickListener(this)
        itemView.setOnLongClickListener(this)
        return ViewHolderData(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolderData, position: Int) {
        holder.dataAsign(bookList[position])
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    override fun onClick(view: View) {
        if (listener != null) {
            listener!!.onClick(view)
        }
    }

    override fun onLongClick(view: View): Boolean {
        if (longListener != null) {
            longListener!!.onLongClick(view)
        }
        return true
    }

    override fun getFilter(): Filter {
        return exampleFilter
    }
    fun setOnClickListener(listener: View.OnClickListener?) {
        this.listener = listener
    }

    fun setOnLongClickListener(listener: OnLongClickListener?) {
        longListener = listener
    }



    private val exampleFilter: Filter = object : Filter() {
        var results = FilterResults()

        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<Book> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(bookListFull)
            } else {
                val filterPattern = constraint.toString().trim { it <= ' ' }
                for (item in bookListFull) {
                    if (item.title.contains(filterPattern)
                            || item.isbn.contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            results.values = filteredList
            return results
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            bookList.clear()

            if (filterResults.values is ArrayList<*>){
                bookList.addAll(filterResults.values as ArrayList<Book>)

            }
            notifyDataSetChanged()
        }
    }

    inner class ViewHolderData(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var imageBook: ImageView = itemView.findViewById(R.id.iv_book)
        private var bookTitle: TextView = itemView.findViewById(R.id.tV_book_title)
        private var authorName: TextView = itemView.findViewById(R.id.tV_author_name)
        private var category: TextView = itemView.findViewById(R.id.tV_category)

        fun dataAsign(book: Book) {
            Picasso.get().load(book.imageUrl).into(imageBook)
            bookTitle.text = MyApp.context!!.getString(R.string.book_title, book.title)
            authorName.text = MyApp.context!!.getString(R.string.author_name, book.authorFirstName, book.authorLastName)
            category.text = MyApp.context!!.getString(R.string.category, book.category)
        }

    }

    init {
        bookListFull = ArrayList(bookList)
    }
}