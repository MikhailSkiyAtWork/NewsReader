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
import mikhailskiy.com.newsreader.models.news.LentaNews;

/**
 * Created by Mikhail on 31.07.16.
 */
public class LentaNewsStorage implements ListStorage<LentaNews> {

    @Override
    public void save(@NonNull List<LentaNews> items, Transaction.Success successListener, Transaction.Error errorListener) {
        {
            FlowManager.getDatabase(NewsReaderDatabase.class)
                    .beginTransactionAsync(
                            new ProcessModelTransaction.Builder<>(items, new ProcessModelTransaction.ProcessModel<LentaNews>() {
                                @Override
                                public void processModel(LentaNews model) {
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
    public void save(LentaNews model) {
        model.save();
    }

    @Override
    public void get(final ListQueryFinishedListener<LentaNews> callback) {

        SQLite.select()
                .from(LentaNews.class)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<LentaNews>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @Nullable List<LentaNews> tResult) {
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
                        SQLite.delete(LentaNews.class);
                    }
                })
                .success(successListener)
                .error(errorListener)
                .build()
                .execute();
    }
}

