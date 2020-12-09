package com.moringaschool.mobilelibrary;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.moringaschool.mobilelibrary.adapters.BookListAdapter;
import com.moringaschool.mobilelibrary.model.GoogleBooksSearchResponse;
import com.moringaschool.mobilelibrary.model.Item;
import com.moringaschool.mobilelibrary.model.VolumeInfo;
import com.moringaschool.mobilelibrary.network.GoogleApi;
import com.moringaschool.mobilelibrary.network.GoogleClient;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookListActivity extends AppCompatActivity {

    private static final  String TAG = " ";

    @BindView(R.id.errorTextView) TextView mError;
    @BindView(R.id.progressBar) ProgressBar mProgressBar;

    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    private BookListAdapter mAdapter;

//    List<VolumeInfo> books;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_list);
        ButterKnife.bind(this);




        GoogleApi client = GoogleClient.getClient();
        Call<GoogleBooksSearchResponse> Call = client.getBooks("q", "AIzaSyCgkz3A0W4a-AMczr9uiRCVyVjiFBbqvOc");


        Call.enqueue(new Callback<GoogleBooksSearchResponse>() {
            @Override
            public void onResponse(Call<GoogleBooksSearchResponse> call, Response<GoogleBooksSearchResponse> response) {
                hideProgressBar();
                Log.d(TAG, "onResponse: Successful");
                if(response.isSuccessful()){
                    List<Item> books = new ArrayList<>();

                    books = response.body().getItems();
                    Log.d(TAG, "BOOKS SHOWN ONE");

                    mAdapter = new BookListAdapter(BookListActivity.this, books);
                    mRecyclerView.setAdapter(mAdapter);
                    Log.d(TAG, "BOOKS SHOWN TWO");

                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    Log.d(TAG, "BOOKS SHOWN THREE");

                    showBooks();
                    Log.d(TAG, "BOOKS SHOWN FOUR");




                }else{
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<GoogleBooksSearchResponse> call, Throwable t) {
                Log.d(TAG, "onFailure: failed");
                hideProgressBar();
                showFailureMessage();
            }
        });
            }

            private void showBooks() {
           return;
            }




    private void showFailureMessage() {
        mError.setText("Something went wrong. Please check your Internet connection and try again!");
        mError.setVisibility(View.VISIBLE);
    }
    private void showUnsuccessfulMessage() {
        mError.setText("Something went wrong. Please try again later");
        mError.setVisibility(View.VISIBLE);
    }
    private void showHero() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }
    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

}



//    private static final  String TAG = " ";
//
//    @BindView(R.id.errorTextView) TextView mError;
//    @BindView(R.id.progressBar) ProgressBar mProgressBar;
//
//    @BindView(R.id.recyclerView)
//    RecyclerView mRecyclerView;
//    private BookListAdapter mAdapter;
//
//    List<VolumeInfo> books;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_book_list);
//        ButterKnife.bind(this);
//
//
//
//
//        GoogleApi client = GoogleClient.getClient();
//        Call<GoogleBooksSearchResponse> Call = client.getBooks("quilting", "AIzaSyCgkz3A0W4a-AMczr9uiRCVyVjiFBbqvOc");
//
//        Call.enqueue(new Callback<GoogleBooksSearchResponse>() {
//
//            @Override
//            public void onResponse(Call<GoogleBooksSearchResponse> call, Response<GoogleBooksSearchResponse> response) {
//
//                hideProgressBar();
//
////                    books = response.body().;
//                    mAdapter = new BookListAdapter(BookListActivity.this, books);
//                    mRecyclerView.setAdapter(mAdapter);
//                    RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(BookListActivity.this);
//                    mRecyclerView.setLayoutManager(layoutManager);
//                    mRecyclerView.setHasFixedSize(true);
//
//                    showBooks();
//
//
//
//
//                }
//
//            private void showBooks() {
//
//            }
//
//
//        }else{
//            showUnsuccessfulMessage();
//        }
//    }
//
//    @Override
//    public void onFailure(Call<Hero> call, Throwable t) {
//        Log.d(TAG, "onFailure: failed");
//        hideProgressBar();
//        showFailureMessage();
//    }
//});
//        }
//
//private void showFailureMessage() {
//        mError.setText("Something went wrong. Please check your Internet connection and try again!");
//        mError.setVisibility(View.VISIBLE);
//        }
//private void showUnsuccessfulMessage() {
//        mError.setText("Something went wrong. Please try again later");
//        mError.setVisibility(View.VISIBLE);
//        }
//private void showHero() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//        }
//private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//        }
//
//        }
//            @Override
//            public void onFailure(retrofit2.Call<GoogleBooksSearchResponse> call, Throwable t) {
//                Log.d(TAG, "onFailure: failed");
//                hideProgressBar();
//                showFailureMessage();
//
//            }else{
//                    showUnsuccessfulMessage();
//                }
//            }
//    })
//}
//                /@Override
//public void onFailure(Call<VolumeInfo> call, Throwable t) {
//        Log.d(TAG, "onFailure: failed");
//        hideProgressBar();
//        showFailureMessage();
//        }
//        });
//        }
//
//
//
//
////}else{
////        showUnsuccessfulMessage();
////        }
////        }
////
////@Override
////public void onFailure(Call<Hero> call, Throwable t) {
////        Log.d(TAG, "onFailure: failed");
////        hideProgressBar();
////        showFailureMessage();
////        }
////        });
////        }
////
//
//private void showFailureMessage() {
//        mError.setText("Something went wrong. Please check your Internet connection and try again!");
//        mError.setVisibility(View.VISIBLE);
//    }
//    private void showUnsuccessfulMessage() {
//        mError.setText("Something went wrong. Please try again later");
//        mError.setVisibility(View.VISIBLE);
//    }
//    private void showHero() {
//        mRecyclerView.setVisibility(View.VISIBLE);
//    }
//    private void hideProgressBar() {
//        mProgressBar.setVisibility(View.GONE);
//    }
//
//}