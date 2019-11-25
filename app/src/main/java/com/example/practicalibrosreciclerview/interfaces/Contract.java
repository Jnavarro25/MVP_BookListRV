package com.example.practicalibrosreciclerview.interfaces;

import com.example.practicalibrosreciclerview.Model.Book;
import java.util.ArrayList;

public interface Contract
{
    interface PresenterToModel
    {
        void makeRequest();
        ArrayList<Book> returnList();
    }

    interface  PresenterToView
    {
        void makeComponentsView();
        void showData();
    }
}
