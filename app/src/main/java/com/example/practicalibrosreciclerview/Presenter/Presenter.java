package com.example.practicalibrosreciclerview.Presenter;

import android.content.Context;

import com.example.practicalibrosreciclerview.Model.Book;
import com.example.practicalibrosreciclerview.Model.GetDataService;
import com.example.practicalibrosreciclerview.interfaces.Contract;

import java.util.ArrayList;

public class Presenter implements Contract.PresenterToModel
{
    private Context context;
    private  GetDataService getDataService;

    public Presenter (Context context)
    {
        this.context=context;
    }


    @Override
    public void fillList()
    {
        getDataService = new GetDataService(context);
        getDataService.getData();
    }

    @Override
    public ArrayList<Book> returnList() {
       return getDataService.getBookList();
    }


}
