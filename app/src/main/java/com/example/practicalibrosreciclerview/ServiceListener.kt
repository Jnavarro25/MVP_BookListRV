package com.example.practicalibrosreciclerview

import com.example.practicalibrosreciclerview.model.Book
import java.util.*

interface ServiceListener {
    fun onResult(books: ArrayList<Book>)
    fun onResultSuccess(result: Boolean)
}