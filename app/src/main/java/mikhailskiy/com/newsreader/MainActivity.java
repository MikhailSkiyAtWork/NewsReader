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
import mikhailskiy.com.newsreader.models.BaseNews;
import mikhailskiy.com.newsreader.models.RssInfo;
import mikhailskiy.com.newsreader.ui.DividerItemDecoration;
import mikhailskiy.com.newsreader.webapi.WebApiProvider;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private final NewsRecyclerViewAdapter newsAdapter_ = new NewsRecyclerViewAdapter();

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
        //getNews();
    }

    private void getRealNews(){
        Call<RssInfo> gazetaCall = WebApiProvider.getGazetaApiService().getGazetaNews();

        gazetaCall.enqueue(new Callback<RssInfo>() {
            @Override
            public void onResponse(Call<RssInfo> call, Response<RssInfo> response) {
                if (response.isSuccessful()){
                    List<BaseNews> list = new ArrayList<>();
                    list = response.body().getChannel().getNewsList();
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
    }

    // TODO Update this method after Api integration
    private void getNews() {
        List<BaseNews> fetchedNews = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
//            BaseNews baseNewsItem = new BaseNews("https://icdn.lenta.ru/images/2016/07/28/19/20160728194941272/pic_a32284d66bce4bc5247ed8aeaa672b3b.jpg",
//                    "СМИ сообщили о возможном переходе турецких силовиков в подчинение Эрдогану",
//                    "Президент Турции Реджеп Тайип Эрдоган выступил с предложением передать в подчинение главы государства " +
//                            "Генеральный штаб вооруженных сил страны и Национальное разведывательное управление. В настоящее " +
//                            "время оба ведомства подчиняются премьер-министру Турции. Инициатива Эрдогана потребует внесения " +
//                            "изменений в конституцию.");

         //   fetchedNews.add(baseNewsItem);
        }

        newsAdapter_.addNews(fetchedNews);
    }
}
