package mikhailskiy.com.newsreader.webapi;

import mikhailskiy.com.newsreader.models.RssInfo;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Mikhail on 28.07.16.
 */
public interface LentaApiService {
    @GET("/rss")
    Call<RssInfo> getLentaNews();
}
