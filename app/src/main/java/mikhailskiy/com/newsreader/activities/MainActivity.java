package mikhailskiy.com.newsreader.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mikhailskiy.com.newsreader.R;
import mikhailskiy.com.newsreader.adapters.NewsRecyclerViewAdapter;
import mikhailskiy.com.newsreader.db.ListQueryFinishedListener;
import mikhailskiy.com.newsreader.db.storage.NewsStorage;
import mikhailskiy.com.newsreader.models.RssInfo;
import mikhailskiy.com.newsreader.models.news.BaseNews;
import mikhailskiy.com.newsreader.ui.DividerItemDecoration;
import mikhailskiy.com.newsreader.webapi.WebApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    private final NewsRecyclerViewAdapter newsAdapter_ = new NewsRecyclerViewAdapter();
    private NewsStorage storage = new NewsStorage();

    @Bind(R.id.swipe_refresh_layout)
    SwipeRefreshLayout swipeRefreshLayout;

    @Bind(R.id.news_recycler_view)
    RecyclerView newsRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsRecyclerView.setAdapter(newsAdapter_);
        newsRecyclerView.addItemDecoration(new DividerItemDecoration(this));

        swipeRefreshLayout.setOnRefreshListener(this);

        swipeRefreshLayout.post(new Runnable() {
                                    @Override
                                    public void run() {
                                        swipeRefreshLayout.setRefreshing(true);
                                        getNews();
                                    }
                                }
        );
    }

    @Override
    public void onRefresh() {
        getNews();
    }

    private void getNews() {
        Call<RssInfo> gazetaCall = WebApiProvider.getGazetaApiService().getGazetaNews();
        gazetaCall.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {
                    ArrayList<BaseNews> baseNewses = new ArrayList<>();
                    baseNewses.addAll(response.body().getChannel().getNewsList());
                    storage.save(baseNewses,
                            new Transaction.Success() {
                                @Override
                                public void onSuccess(Transaction transaction) {
                                    storage.get(new ListQueryFinishedListener<BaseNews>() {
                                        @Override
                                        public void onQueryFinished(List<BaseNews> items) {
                                            newsAdapter_.addNews(items);
                                        }
                                    });
                                }
                            },
                            new Transaction.Error() {
                                @Override
                                public void onError(Transaction transaction, Throwable error) {
                                    // FAILURE or ERROR
                                    String errorMessage = error.getMessage();
                                    if (errorMessage == null) {
                                        errorMessage = getString(R.string.error_unexpected_respond);
                                    }
                                    if (MainActivity.this != null) {
                                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_unexpected_respond, Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<RssInfo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();

                storage.get(new ListQueryFinishedListener<BaseNews>() {
                    @Override
                    public void onQueryFinished(List<BaseNews> items) {
                        newsAdapter_.addNews(items);
                    }
                });
            }
        });

        Call<RssInfo> lentaCall = WebApiProvider.getLentaApiService().getLentaNews();

        lentaCall.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {
                    ArrayList<BaseNews> baseNewses = new ArrayList<>();
                    baseNewses.addAll(response.body().getChannel().getNewsList());
                    storage.save(baseNewses,
                            new Transaction.Success() {
                                @Override
                                public void onSuccess(Transaction transaction) {
                                    storage.get(new ListQueryFinishedListener<BaseNews>() {
                                        @Override
                                        public void onQueryFinished(List<BaseNews> items) {
                                            newsAdapter_.addNews(items);
                                        }
                                    });
                                }
                            },
                            new Transaction.Error() {
                                @Override
                                public void onError(Transaction transaction, Throwable error) {
                                    // FAILURE or ERROR
                                    String errorMessage = error.getMessage();
                                    if (errorMessage == null) {
                                        errorMessage = getString(R.string.error_unexpected_respond);
                                    }
                                    if (MainActivity.this != null) {
                                        Toast.makeText(MainActivity.this, errorMessage, Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                    );
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_unexpected_respond, Toast.LENGTH_SHORT).show();
                }
                swipeRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onFailure(Call<RssInfo> call, Throwable t) {
                swipeRefreshLayout.setRefreshing(false);
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();
                storage.get(new ListQueryFinishedListener<BaseNews>() {
                    @Override
                    public void onQueryFinished(List<BaseNews> items) {
                        newsAdapter_.addNews(items);
                    }
                });
            }
        });
    }
}
