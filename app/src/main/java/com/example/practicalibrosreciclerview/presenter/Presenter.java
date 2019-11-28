package com.example.practicalibrosreciclerview.presenter;

import com.example.practicalibrosreciclerview.ServiceListener;
import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.GetDataService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class Presenter implements ServiceListener {

  private View view;

  public Presenter() {}

  public void setView(View view) {
    this.view = view;
  }

  public void makeRequest() {
    view.showProgres();
    GetDataService getDataService = new GetDataService(this);
    getDataService.getData();
  }

  public void makeRequestPost(HashMap jsonObject){
    view.showProgres();
    GetDataService getDataService = new GetDataService(this);
    getDataService.setData(jsonObject);
  }

  public HashMap convertData(Book book){




    HashMap data = new HashMap();
    data.put("isbn",book.getIsbn());
    data.put("title",book.getTitle());
    data.put("author","{''}");
    data.put("title",book.getTitle());
    data.put("title",book.getTitle());



    return data;
  }

  @Override
  public void onResult(ArrayList<Book> books) {
    view.showData(books);
    view.hideProgress();
  }

  public interface View {
    void showData(ArrayList<Book> books);
    void showProgres();
    void hideProgress();
  }
}
