package com.example.practicalibrosreciclerview;

import com.example.practicalibrosreciclerview.model.Book;

import java.util.ArrayList;

public interface ServiceListener {
  void onResult(ArrayList<Book> books);
  void onResultPost(boolean result);
}
