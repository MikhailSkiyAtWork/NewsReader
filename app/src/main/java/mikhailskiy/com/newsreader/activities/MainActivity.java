package mikhailskiy.com.newsreader.activities;

import android.os.Bundle;
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
import mikhailskiy.com.newsreader.db.storage.GazetaNewsListStorage;
import mikhailskiy.com.newsreader.db.storage.LentaNewsStorage;
import mikhailskiy.com.newsreader.models.RssInfo;
import mikhailskiy.com.newsreader.models.RssInfoLenta;
import mikhailskiy.com.newsreader.models.news.BaseNews;
import mikhailskiy.com.newsreader.models.news.GazetaNews;
import mikhailskiy.com.newsreader.models.news.LentaNews;
import mikhailskiy.com.newsreader.ui.DividerItemDecoration;
import mikhailskiy.com.newsreader.webapi.WebApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends BaseActivity {

    private final NewsRecyclerViewAdapter newsAdapter_ = new NewsRecyclerViewAdapter();
    private GazetaNewsListStorage storage = new GazetaNewsListStorage();
    private LentaNewsStorage lentaNewsStorage = new LentaNewsStorage();

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
        getRealNews();
    }

    private void getRealNews() {
        if (isRequestInProgress_) {
            return;
        }

        isRequestInProgress_ = true;
        showProgressBar();

        Call<RssInfo> gazetaCall = WebApiProvider.getGazetaApiService().getGazetaNews();

        gazetaCall.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {
                    storage.save(response.body().getChannel().getNewsList(),
                            new Transaction.Success() {
                                @Override
                                public void onSuccess(Transaction transaction) {
                                    storage.get(new ListQueryFinishedListener<GazetaNews>() {
                                        @Override
                                        public void onQueryFinished(List<GazetaNews> items) {
                                            List<BaseNews> news = new ArrayList<>();
                                            news.addAll(items);
                                            newsAdapter_.addNews(news);
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
                isRequestInProgress_ = false;
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<RssInfo> call, Throwable t) {
                isRequestInProgress_ = false;
                hideProgressBar();
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();
            }
        });

        Call<RssInfoLenta> lentaCall = WebApiProvider.getLentaApiService().getLentaNews();

        lentaCall.enqueue(new Callback<RssInfoLenta>() {
            @Override
            public void onResponse(Call<RssInfoLenta> call, Response<RssInfoLenta> response) {
                if (response.isSuccessful()) {
                    lentaNewsStorage.save(response.body().getChannel().getLentaNewsList(),
                            new Transaction.Success() {
                                @Override
                                public void onSuccess(Transaction transaction) {
                                    lentaNewsStorage.get(new ListQueryFinishedListener<LentaNews>() {
                                        @Override
                                        public void onQueryFinished(List<LentaNews> items) {
                                            List<BaseNews> news = new ArrayList<>();
                                            news.addAll(items);
                                            newsAdapter_.addNews(news);
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
                isRequestInProgress_ = false;
                hideProgressBar();
            }

            @Override
            public void onFailure(Call<RssInfoLenta> call, Throwable t) {
                isRequestInProgress_ = false;
                hideProgressBar();
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
