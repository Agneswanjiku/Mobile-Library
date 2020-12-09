package com.moringaschool.mobilelibrary.adapters;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
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

public class BookListAdapterblabla extends RecyclerView.Adapter<BookListAdapterblabla.BookViewHolder> {
    private List<Item> books;
    private Context mContext;

    public BookListAdapterblabla(Context context, List<Item> mBooks) {
        mContext = context;
        books = mBooks;
    }


    @NonNull
    @Override
    public BookListAdapterblabla.BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        BookViewHolder viewHolder = new BookViewHolder(v);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(@NonNull BookListAdapterblabla.BookViewHolder holder, int position) {
        holder.bindVolumeInfo(books.get(position));
    }


    @Override
    public int getItemCount() {
        return books.size();
    }


    public class BookViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.bookImageView) ImageView mBookImageView;
        @BindView(R.id.titleTextView) TextView mTitleTextView;
        @BindView(R.id.publisherTextView) TextView mPublisherTextView;
        @BindView(R.id.selfLinkTextView) TextView mSelfLinkTextView;

        private Context mContext;

        public BookViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            final Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.bounce);
            flipIt(view);
            view.startAnimation(animation);
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, BookDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("books", Parcels.wrap(books));
            mContext.startActivity(intent);
        }

        public void bindVolumeInfo(Item book) {

//            Picasso.get().load().book.getVolumeInfo().getImageLinks().into(mBookImageView);
            Picasso.get().load(book.getVolumeInfo().getImageLinks().getThumbnail()).into(mBookImageView);
            mTitleTextView.setText(book.getVolumeInfo().getTitle());
            mPublisherTextView.setText(book.getVolumeInfo().getPublisher());
            mSelfLinkTextView.setText(book.getSelfLink());


//            mBookName.setText(("book Name: ") + book.getVolumeInfo().getAuthors());
//            mFullBookName.setText(("author  Name: ")+ book.getVolumeInfo().getAuthors();

        }

        private void flipIt(final View viewToFlip) {
            ObjectAnimator flip = ObjectAnimator.ofFloat(viewToFlip, "rotationX", 2.0f, 360f);
            flip.setDuration(2000);
            flip.start();
        }



    }

}
