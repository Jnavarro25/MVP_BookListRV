package com.example.practicalibrosreciclerview.presenter;

import com.example.practicalibrosreciclerview.ServiceListener;
import com.example.practicalibrosreciclerview.model.Book;
import com.example.practicalibrosreciclerview.model.GetDataService;

import java.util.ArrayList;

public class Presenter implements ServiceListener {

    private View view;
    private GetDataService getDataService;

    public Presenter() {}

    public void setView(View view) {
        this.view = view;
    }

    public void makeRequest() {
        getDataService = new GetDataService(this);
        getDataService.getData();
    }

    @Override
    public void onResult(ArrayList<Book> books) {
        view.showData(books);
    }

    public ArrayList<Book> filter(String text, ArrayList<Book> books) {
        ArrayList<Book> result = new ArrayList<>();
        text = text.toLowerCase();
        for (Book item : books) {
            if (item.getTitle().toLowerCase().contains(text)
                    || item.getIsbn().toLowerCase().contains(text)) {
                result.add(item);
            }
        }
        return result;
    }

    public interface View {
        void showData(ArrayList<Book> books);
    }
}
