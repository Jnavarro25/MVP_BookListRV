package com.example.practicalibrosreciclerview.Presenter;

import android.content.Context;

import com.example.practicalibrosreciclerview.Model.Book;
import com.example.practicalibrosreciclerview.Model.GetDataService;
import com.example.practicalibrosreciclerview.interfaces.Contract;

import java.util.ArrayList;

public class Presenter implements Contract.PresenterToModel
{
    private Context context;
    private GetDataService getDataService;

    public Presenter (Context context)
    {
        this.context=context;

    }


    @Override
    public void makeRequest()
    {
        getDataService = new GetDataService(context);
        getDataService.getData();
    }

    @Override
    public ArrayList<Book> returnList() {
       return getDataService.getBookList();
    }

    @Override
    public ArrayList<Book> filter(String text,ArrayList<Book> books)
    {
            ArrayList<Book> result = new ArrayList<>();
            text = text.toLowerCase();
            for(Book item: books)
            {
                if(item.getTitle().toLowerCase().contains(text) || item.getIsbn().toLowerCase().contains(text))
                {
                    result.add(item);
                }
            }
        return result;
    }


}
