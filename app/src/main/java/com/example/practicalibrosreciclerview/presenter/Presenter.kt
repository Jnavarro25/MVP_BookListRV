package com.example.practicalibrosreciclerview.presenter

import com.example.practicalibrosreciclerview.ServiceListener
import com.example.practicalibrosreciclerview.model.Book
import com.example.practicalibrosreciclerview.model.GetDataService
import org.json.JSONException
import org.json.JSONObject
import java.util.*
import kotlin.collections.HashMap

class Presenter : ServiceListener {
    private var view: View? = null
    override fun onResult(books: ArrayList<Book>) {
        view!!.showData(books)
        view!!.hideProgress()
    }

    override fun onResultSuccess(result: Boolean) {
        if (result) {
            view!!.hideProgress()
            view!!.makeRefresh()
        } else {
            view!!.hideProgress()
            view!!.showErrorMessage()
        }
    }

    fun setView(view: View?) {
        this.view = view
    }

    fun makeRequestGet() {
        view!!.showProgress()
        val getDataService = GetDataService(this)
        getDataService.getData()
    }

    fun makeRequestPost(jsonObject: HashMap<Any?, Any?>) {
        view!!.showProgress()
        val getDataService = GetDataService(this)
        getDataService.setData(jsonObject)
    }

    fun deleteBook(key: String?) {
        val getDataService = GetDataService(this)
        getDataService.deleteData(key!!)
    }

    fun convertData(book: Book): HashMap<Any?, Any?> {
        val data = HashMap<Any?, Any?>()
        data["isbn"] = book.isbn
        data["title"] = book.title
        try {
            data["author"] = JSONObject("{first_name:" + book.authorFirstName + "," + "last_name:" + book.authorLastName + "}")
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        data["category"] = book.category
        data["published"] = book.published
        data["publisher"] = book.publisher
        data["pages"] = book.pagesNumber
        data["description"] = book.description
        data["image_url"] = book.imageUrl
        return data
    }

    interface View {
        fun showData(books: ArrayList<Book>?)
        fun showProgress()
        fun hideProgress()
        fun showErrorMessage()
        fun makeRefresh()
    }
}