package com.example.practicalibrosreciclerview.Model;

import android.content.Context;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetDataService
{
    private Context context;
    private ArrayList<Book> bookList = new ArrayList<>();
    private Book book;
    //Constructor Method

    public GetDataService(Context context)
    {
        this.context = context;
    }

    public void getData()
    {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://jsonbox.io/box_479f5c073a80294b4c3b";

        JsonArrayRequest request = new JsonArrayRequest(url, new Response.Listener<JSONArray>()
        {
            @Override
            public void onResponse(JSONArray jsonArray)
            {
                try
                {
                    decodeJSON(jsonArray);
                    Log.d("Respuesta","Response is so good");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError)
            {
                Log.d("Response","That didn't work!");
            }
        });

        queue.add(request);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void decodeJSON(JSONArray jsonArray)
    {
        try
        {
            for (int i =1; i<jsonArray.length();i++)
            {
                JSONObject object = jsonArray.getJSONObject(i);
                String title = object.getString("title");
                JSONObject authorObject = object.getJSONObject("author");
                String nameAuthor = authorObject.getString("first_name");
                String category = object.getString("category");
                String imageUrl = object.getString("image_url");
                book = new Book(title,nameAuthor,category,imageUrl);
                bookList.add(book);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }


    public ArrayList<Book> getBookList() {
        return bookList;
    }
}
