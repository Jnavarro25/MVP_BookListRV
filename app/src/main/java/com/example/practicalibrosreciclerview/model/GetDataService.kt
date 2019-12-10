package com.example.practicalibrosreciclerview.model

import android.util.Log
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.practicalibrosreciclerview.MyApp
import com.example.practicalibrosreciclerview.ServiceListener
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.util.*

class GetDataService // Constructor Method
(private val listener: ServiceListener) {

    private lateinit var queue: RequestQueue
    private val bookList = ArrayList<Book>()

    fun getData()
    {
        queue = Volley.newRequestQueue(MyApp.context)
        val url = "https://jsonbox.io/box_479f5c073a80294b4c3b"
        val request = JsonArrayRequest(
                url,
                Response.Listener { jsonArray ->
                    try {
                        decodeJSON(jsonArray)
                        Log.d("Response", "Response is so good")
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                },
                Response.ErrorListener {
                    listener.onResultSuccess(false)
                    Log.d("Response", "That didn't work!")
                })
        queue.add(request)

    }

    fun setData(data: HashMap<Any?, Any?>) {
        queue = Volley.newRequestQueue(MyApp.context)
        val url = "https://jsonbox.io/box_479f5c073a80294b4c3b"
        val jsonObject = JSONObject(data)
        val jsonObjectRequest = JsonObjectRequest(Request.Method.POST, url, jsonObject,
                Response.Listener { listener.onResultSuccess(true) },
                Response.ErrorListener { listener.onResultSuccess(false) }
        )
        queue.add(jsonObjectRequest)
    }

    fun deleteData(key: String) {
        queue = Volley.newRequestQueue(MyApp.context)
        val url = "https://jsonbox.io/box_479f5c073a80294b4c3b/$key"
        val jsonObject = JsonObjectRequest(Request.Method.DELETE, url, null,
                Response.Listener { listener.onResultSuccess(true) },
                Response.ErrorListener { listener.onResultSuccess(false) }
        )
        queue.add(jsonObject)
    }

    private fun decodeJSON(jsonArray: JSONArray) {
        try {
            for (i in 0 until jsonArray.length()) {
                val jsonBody = jsonArray.getJSONObject(i)
                val id = jsonBody.getString("_id")
                val title = jsonBody.getString("title")
                val authorObject = jsonBody.getJSONObject("author")
                val firstName = authorObject.getString("first_name")
                val lastName = authorObject.getString("last_name")
                val category = jsonBody.getString("category")
                val imageUrl = jsonBody.getString("image_url")
                val published = jsonBody.getString("published")
                val publisher = jsonBody.getString("publisher")
                val pagesNumber = jsonBody.getString("pages")
                val isbn = jsonBody.getString("isbn")
                val bookDescription = jsonBody.getString("description")
                val book = Book(
                        id,
                        isbn,
                        title,
                        firstName,
                        lastName,
                        published,
                        publisher,
                        pagesNumber,
                        bookDescription, imageUrl, category)
                bookList.add(book)
            }
            listener.onResult(bookList)
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

}