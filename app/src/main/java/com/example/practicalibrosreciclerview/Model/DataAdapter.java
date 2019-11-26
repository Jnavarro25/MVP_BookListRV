package com.example.practicalibrosreciclerview.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.practicalibrosreciclerview.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter
        extends RecyclerView.Adapter<DataAdapter.ViewHolderData>
        implements View.OnClickListener
{
    private ArrayList<Book> bookList;
    private Context context;
    private View.OnClickListener listener;

    public DataAdapter(ArrayList<Book> bookList) {
        this.bookList = bookList;
    }

    @NonNull
    @Override
    public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        context= parent.getContext();
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.cv_item_book,null,false);

        itemView.setOnClickListener(this);

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

    public void setOnClickListener(View.OnClickListener listener)
     {
         this.listener=listener;
     }

    @Override
    public void onClick(View view)
    {
        if(listener!=null)
        {
            listener.onClick(view);
        }
    }

    public class ViewHolderData extends RecyclerView.ViewHolder
    {
        ImageView imageBook ;
        TextView bookTitle;
        TextView authorName;
        TextView category;


        public ViewHolderData(@NonNull View itemView)
        {
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
