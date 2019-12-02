package com.example.practicalibrosreciclerview.model;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.practicalibrosreciclerview.MyApp;
import com.example.practicalibrosreciclerview.ServiceListener;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class GetDataService {

  private ServiceListener listener;
  private RequestQueue queue;

  private ArrayList<Book> bookList = new ArrayList<>();
  // Constructor Method

  public GetDataService(ServiceListener listener) {
    this.listener = listener;
  }

  public void getData() {
    queue = Volley.newRequestQueue(MyApp.getContext());
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
  }

  public void setData(HashMap data){
    queue = Volley.newRequestQueue(MyApp.getContext());
    String url = "https://jsonbox.io/box_479f5c073a80294b4c3b";
    JSONObject jsonObject = new JSONObject(data);
    JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,jsonObject,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {

                listener.onResultSucces(true);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                listener.onResultSucces(false);
              }
            }
    );
    queue.add(jsonobj);

  }

  public void deleteData(String key){

    queue = Volley.newRequestQueue(MyApp.getContext());
    String url = "https://jsonbox.io/box_479f5c073a80294b4c3b/"+key;

    JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.DELETE, url,null,
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {

                listener.onResultSucces(true);
              }
            },
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                listener.onResultSucces(false);
              }
            }
    );
    queue.add(jsonobj);

  }

  private void decodeJSON(JSONArray jsonArray) {
    try {
      for (int i = 0; i < jsonArray.length(); i++) {
        JSONObject object = jsonArray.getJSONObject(i);
        String id = object.getString("_id");
        String title = object.getString("title");
        JSONObject authorObject = object.getJSONObject("author");
        String firstName = authorObject.getString("first_name");
        String lastName = authorObject.getString("last_name");
        String category = object.getString("category");
        String imageUrl = object.getString("image_url");
        String published = object.getString("published");
        String publisher = object.getString("publisher");
        String pagesNumber = object.getString("pages");
        String isbn = object.getString("isbn");
        String bookDescription = object.getString("description");

        Book book =
            new Book(
                id,
                isbn,
                title,
                firstName,
                lastName,
                published,
                publisher,
                pagesNumber,
                bookDescription,imageUrl,category);
        bookList.add(book);
      }
      listener.onResult(bookList);
    } catch (JSONException e) {
      e.printStackTrace();
    }
  }
}
