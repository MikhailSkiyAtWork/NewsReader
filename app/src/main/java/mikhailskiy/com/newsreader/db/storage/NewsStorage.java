package mikhailskiy.com.newsreader.db.storage;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.raizlabs.android.dbflow.config.FlowManager;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.raizlabs.android.dbflow.structure.database.DatabaseWrapper;
import com.raizlabs.android.dbflow.structure.database.transaction.ITransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.ProcessModelTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.QueryTransaction;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

import mikhailskiy.com.newsreader.db.ListQueryFinishedListener;
import mikhailskiy.com.newsreader.db.NewsReaderDatabase;
import mikhailskiy.com.newsreader.models.news.BaseNews;

/**
 * Created by Mikhail on 31.07.16.
 */
public class NewsStorage implements ListStorage<BaseNews> {

    @Override
    public void save(@NonNull List<BaseNews> items, Transaction.Success successListener, Transaction.Error errorListener) {
        {
            FlowManager.getDatabase(NewsReaderDatabase.class)
                    .beginTransactionAsync(
                            new ProcessModelTransaction.Builder<>(items, new ProcessModelTransaction.ProcessModel<BaseNews>() {
                                @Override
                                public void processModel(BaseNews model) {
                                    save(model);
                                }
                            }).build()
                    )
                    .success(successListener)
                    .error(errorListener)
                    .build()
                    .execute();
        }
    }

    @Override
    public void save(BaseNews model) {
        model.save();
    }

    @Override
    public void get(final ListQueryFinishedListener<BaseNews> callback) {

        SQLite.select()
                .from(BaseNews.class)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<BaseNews>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @Nullable List<BaseNews> tResult) {
                        if (tResult == null) {
                            if (callback != null) {
                                callback.onQueryFinished(null);
                            }
                        } else {
                            if (callback != null) {
                                callback.onQueryFinished(tResult);
                            }
                        }
                    }
                })
                .execute();
    }

    @Override
    public void clearData(Transaction.Success successListener, Transaction.Error errorListener) {
        FlowManager.getDatabase(NewsReaderDatabase.class)
                .beginTransactionAsync(new ITransaction() {
                    @Override
                    public void execute(DatabaseWrapper databaseWrapper) {
                        SQLite.delete(BaseNews.class);
                    }
                })
                .success(successListener)
                .error(errorListener)
                .build()
                .execute();
    }
}
