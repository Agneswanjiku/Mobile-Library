package com.moringaschool.mobilelibrary.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.moringaschool.mobilelibrary.BookDetailActivity;
import com.moringaschool.mobilelibrary.R;
import com.moringaschool.mobilelibrary.model.Item;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.BookViewHolder> {
    private List<Item> books;
    private Context mContext;

    public BookListAdapter(Context context, List<Item> agnes_books) {
        mContext = context;
        books = agnes_books;
    }


    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        holder.bindagnesbooks(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.bookImageView)
        ImageView mBookImageView;
        @BindView(R.id.titleTextView)
        TextView mTitleTextView;

        private Context mContext;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BookDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("booksAgnes", Parcels.wrap(books));
            mContext.startActivity(intent);
        }

        public void bindagnesbooks(Item google_books) {
            Picasso.get().load(google_books.getVolumeInfo().getImageLinks().getThumbnail()).into(mBookImageView);
            mTitleTextView.setText(google_books.getVolumeInfo().getTitle());


        }
    }

}
