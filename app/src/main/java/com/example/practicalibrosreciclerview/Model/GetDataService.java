package com.example.practicalibrosreciclerview.model;

import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.practicalibrosreciclerview.MyApp;
import com.example.practicalibrosreciclerview.ServiceListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDataService {

    private ServiceListener listener;

    private ArrayList<Book> bookList = new ArrayList<>();
    // Constructor Method

    public GetDataService(ServiceListener listener) {
        this.listener = listener;
    }

    public void getData() {
        RequestQueue queue = Volley.newRequestQueue(MyApp.getContext());
        String url = "https://jsonbox.io/box_479f5c073a80294b4c3b";

        JsonArrayRequest request =
                new JsonArrayRequest(
                        url,
                        new Response.Listener<JSONArray>() {
                            @Override
                            public void onResponse(JSONArray jsonArray) {
                                try {
                                    decodeJSON(jsonArray);
                                    Log.d("Respuesta", "Response is so good");
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError volleyError) {
                                Log.d("Response", "That didn't work!");
                            }
                        });

        queue.add(request);
        try {
            Thread.sleep(3200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void decodeJSON(JSONArray jsonArray) {
        try {
            for (int i = 1; i < jsonArray.length(); i++) {
                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                JSONObject authorObject = object.getJSONObject("author");
                String firstName = authorObject.getString("first_name");
                String lastName = authorObject.getString("last_name");
                String nameAuthor = firstName + " " + lastName;
                String category = object.getString("category");
                String imageUrl = object.getString("image_url");
                String createdOn = object.getString("_createdOn");
                String pagesNumber = object.getString("pages");
                String isbn = object.getString("isbn");
                String bookDescription = object.getString("description");

                Book book = new Book(
                        title,
                        nameAuthor,
                        category,
                        imageUrl,
                        createdOn,
                        pagesNumber,
                        isbn,
                        bookDescription);
                bookList.add(book);
            }
            listener.onResult(bookList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }
}
