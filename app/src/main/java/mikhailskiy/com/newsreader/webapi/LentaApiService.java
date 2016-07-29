package mikhailskiy.com.newsreader.webapi;

import java.util.List;

import mikhailskiy.com.newsreader.models.LentaNews;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mikhail on 28.07.16.
 */
public interface LentaApiService {
    @GET("/rss")
    Call<List<LentaNews>> getLentaNews();
}
