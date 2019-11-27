package com.example.practicalibrosreciclerview.presenter;

import com.example.practicalibrosreciclerview.ServiceListener;
import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.GetDataService;

import java.util.ArrayList;

public class Presenter implements ServiceListener {

  private View view;

  public Presenter() {}

  public void setView(View view) {
    this.view = view;
  }

  public void makeRequest() {
    GetDataService getDataService = new GetDataService(this);
    getDataService.getData();
  }

  @Override
  public void onResult(ArrayList<Book> books) {
    view.showData(books);
  }






  public interface View {
    void showData(ArrayList<Book> books);
  }
}
