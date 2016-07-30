package mikhailskiy.com.newsreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import mikhailskiy.com.newsreader.adapters.NewsRecyclerViewAdapter;
import mikhailskiy.com.newsreader.models.RssInfo;
import mikhailskiy.com.newsreader.models.RssInfoLenta;
import mikhailskiy.com.newsreader.models.news.BaseNews;
import mikhailskiy.com.newsreader.ui.DividerItemDecoration;
import mikhailskiy.com.newsreader.webapi.WebApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final NewsRecyclerViewAdapter newsAdapter_ = new NewsRecyclerViewAdapter();;

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
        Call<RssInfo> gazetaCall = WebApiProvider.getGazetaApiService().getGazetaNews();

        gazetaCall.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()) {
                    List<BaseNews> list = new ArrayList<>();
                    list.addAll(response.body().getChannel().getNewsList());
                    newsAdapter_.addNews(list);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_unexpected_respond, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RssInfo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();
            }
        });

        Call<RssInfoLenta> lentaCall = WebApiProvider.getLentaApiService().getLentaNews();

        lentaCall.enqueue(new Callback<RssInfoLenta>() {
            @Override
            public void onResponse(Call<RssInfoLenta> call, Response<RssInfoLenta> response) {
                if (response.isSuccessful()) {
                    List<BaseNews> newsList = new ArrayList<>();
                    newsList.addAll(response.body().getChannel().getLentaNewsList());
                    newsAdapter_.addNews(newsList);
                } else {
                    Toast.makeText(getApplicationContext(), R.string.error_unexpected_respond, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RssInfoLenta> call, Throwable t) {
                Toast.makeText(getApplicationContext(), R.string.error_sending_request, Toast.LENGTH_SHORT).show();
            }
        });
    }
}
