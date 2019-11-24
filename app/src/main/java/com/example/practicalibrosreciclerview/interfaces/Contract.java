package com.example.practicalibrosreciclerview.interfaces;

import com.example.practicalibrosreciclerview.Model.Book;

import java.util.ArrayList;

public interface Contract
{
    interface PresenterToModel
    {
        void fillList();
        ArrayList<Book> returnList();
    }

    interface  PresenterToView
    {
        void makeReciclerView();
        void showList();
    }
}
