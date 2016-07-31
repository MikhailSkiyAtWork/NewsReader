package mikhailskiy.com.newsreader.db;

import java.util.List;

/**
 * Created by Mikhail on 31.07.16.
 */
public interface ListQueryFinishedListener<T> {
    void onQueryFinished(List<T> items);
}
