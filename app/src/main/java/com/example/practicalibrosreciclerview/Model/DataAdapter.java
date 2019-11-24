package com.example.practicalibrosreciclerview.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.practicalibrosreciclerview.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderData>
{
    private ArrayList<Book> bookList;
    private Context context;

    public DataAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.item_book,null,false);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,"Presionaste un libro",Toast.LENGTH_LONG).show();
            }
        });

        return new ViewHolderData(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderData holder, int position)
    {
        holder.dataAsign(bookList.get(position));
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public class ViewHolderData extends RecyclerView.ViewHolder {
        ImageView imageBook ;
        TextView bookTitle;
        TextView authorName;
        TextView category;

        public ViewHolderData(@NonNull View itemView) {
            super(itemView);
            imageBook = itemView.findViewById(R.id.iv_book);
            bookTitle = itemView.findViewById(R.id.tV_book_title);
            authorName= itemView.findViewById(R.id.tV_author_name);
            category= itemView.findViewById(R.id.tV_category);


        }

        public void dataAsign(Book book)
        {
            Picasso.with(context).load(book.getUrlImage()).into(imageBook);
            bookTitle.setText(book.getTitle());
            authorName.setText(book.getAuthor());
            category.setText(book.getCategory());
        }
    }
}
