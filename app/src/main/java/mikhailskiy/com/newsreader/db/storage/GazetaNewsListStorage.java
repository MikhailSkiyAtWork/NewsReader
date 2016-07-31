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
import mikhailskiy.com.newsreader.models.news.GazetaNews;

/**
 * Created by Mikhail on 31.07.16.
 */
public class GazetaNewsListStorage implements ListStorage<GazetaNews> {

    @Override
    public void save(@NonNull List<GazetaNews> items, Transaction.Success successListener, Transaction.Error errorListener) {
        {
            FlowManager.getDatabase(NewsReaderDatabase.class)
                    .beginTransactionAsync(
                            new ProcessModelTransaction.Builder<>(items, new ProcessModelTransaction.ProcessModel<GazetaNews>() {
                                @Override
                                public void processModel(GazetaNews model) {
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
    public void save(GazetaNews model) {
        model.save();
    }

    @Override
    public void get(final ListQueryFinishedListener<GazetaNews> callback) {

        SQLite.select()
                .from(GazetaNews.class)
                .async()
                .queryListResultCallback(new QueryTransaction.QueryResultListCallback<GazetaNews>() {
                    @Override
                    public void onListQueryResult(QueryTransaction transaction, @Nullable List<GazetaNews> tResult) {
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
                        SQLite.delete(GazetaNews.class);
                    }
                })
                .success(successListener)
                .error(errorListener)
                .build()
                .execute();
    }
}
