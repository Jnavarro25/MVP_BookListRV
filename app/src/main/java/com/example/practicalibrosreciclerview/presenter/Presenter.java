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

  public void deleteBook(String key) {
    GetDataService getDataService = new GetDataService(this);
    getDataService.deleteData(key);
  }

  public HashMap convertData(Book book){

    HashMap data = new HashMap();
    data.put("isbn",book.getIsbn());
    data.put("title",book.getTitle());
    try {
      data.put("author",new JSONObject("{first_name:"+book.getAuthorFirstName()+","+ "last_name:"+book.getAuthorLastName()+"}"));
    } catch (JSONException e) {
      e.printStackTrace();
    }
    data.put("category",book.getCategory());
    data.put("published",book.getPublished());
    data.put("publisher",book.getPublisher());
    data.put("pages",book.getPagesNumber());
    data.put("description",book.getDescription());
    data.put("image_url",book.getImageUrl());

    return data;
  }

  @Override
  public void onResult(ArrayList<Book> books) {
    view.showData(books);
    view.hideProgress();

  }

  @Override
  public void onResultSucces(boolean flag) {

    if(flag){
      view.hideProgress();
      view.makeRefresh();

    }else {
      view.showErrorMessage();
    }

  }



    public interface View {
    void showData(ArrayList<Book> books);
    void showProgres();
    void hideProgress();
    void showErrorMessage();
    void makeRefresh();
  }
}
