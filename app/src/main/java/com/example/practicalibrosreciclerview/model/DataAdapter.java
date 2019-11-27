package com.example.practicalibrosreciclerview.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.practicalibrosreciclerview.R;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolderData>
    implements View.OnClickListener, Filterable {
  private ArrayList<Book> bookList;
  private ArrayList<Book> bookListFull;
  private Context context;
  private View.OnClickListener listener;

  public DataAdapter(ArrayList<Book> bookList) {
    this.bookList = bookList;
    bookListFull = new ArrayList<>(bookList);
  }

  @NonNull
  @Override
  public ViewHolderData onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    context = parent.getContext();
    View itemView = LayoutInflater.from(context).inflate(R.layout.cv_item_book, null, false);

    itemView.setOnClickListener(this);

    return new ViewHolderData(itemView);
  }

  @Override
  public void onBindViewHolder(@NonNull ViewHolderData holder, int position) {
    holder.dataAsign(bookList.get(position));
  }

  @Override
  public int getItemCount() {
    return bookList.size();
  }

  public void setOnClickListener(View.OnClickListener listener) {
    this.listener = listener;
  }

  @Override
  public void onClick(View view) {
    if (listener != null) {
      listener.onClick(view);
    }
  }

  public void filter() {}

  @Override
  public Filter getFilter() {
    return exampleFilter;
  }

  private Filter exampleFilter =
      new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
          List<Book> filteredList = new ArrayList<>();

          if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(bookList);
          } else {
            String filterPattern = constraint.toString().toLowerCase().trim();

            for (Book item : bookListFull) {
              if (item.getTitle().toLowerCase().contains(filterPattern)
                  || item.getIsbn().toLowerCase().contains(filterPattern)) {
                filteredList.add(item);
              }
            }
          }

          FilterResults results = new FilterResults();
          results.values = filteredList;

          return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

            if (bookList.size() != 0) {
            bookList.clear();
            bookList.addAll((List) filterResults.values);
          } else {
              bookList.addAll((List) filterResults.values);
          }

          notifyDataSetChanged();
        }
      };

  public class ViewHolderData extends RecyclerView.ViewHolder {
    ImageView imageBook;
    TextView bookTitle;
    TextView authorName;
    TextView category;

    private ViewHolderData(@NonNull View itemView) {
      super(itemView);

      imageBook = itemView.findViewById(R.id.iv_book);
      bookTitle = itemView.findViewById(R.id.tV_book_title);
      authorName = itemView.findViewById(R.id.tV_author_name);
      category = itemView.findViewById(R.id.tV_category);
    }

    private void dataAsign(Book book) {
      Picasso.get().load(book.getUrlImage()).into(imageBook);
      bookTitle.setText(book.getTitle());
      authorName.setText(book.getAuthor());
      category.setText(book.getCategory());
    }
  }
}