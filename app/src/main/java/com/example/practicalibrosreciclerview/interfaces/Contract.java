package com.example.practicalibrosreciclerview.interfaces;

import com.example.practicalibrosreciclerview.Model.Book;
import java.util.ArrayList;

public interface Contract
{
    interface PresenterToModel
    {
        void makeRequest();
        ArrayList<Book> returnList();
        ArrayList<Book> filter(String text,ArrayList<Book> books);

    }

    interface  PresenterToView
    {
        void makeComponentsView();
        void showData();
    }
}
