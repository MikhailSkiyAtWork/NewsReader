package mikhailskiy.com.newsreader.db.storage;

import android.support.annotation.NonNull;

import com.raizlabs.android.dbflow.structure.BaseModel;
import com.raizlabs.android.dbflow.structure.database.transaction.Transaction;

import java.util.List;

import mikhailskiy.com.newsreader.db.ListQueryFinishedListener;

/**
 * Created by Mikhail on 31.07.16.
 */
public interface ListStorage<Model extends BaseModel> {
    void save(Model model);

    void save(@NonNull final List<Model> items, Transaction.Success successListener, Transaction.Error errorListener);

    void get(ListQueryFinishedListener<Model> callback);

    void clearData(Transaction.Success successListener, Transaction.Error errorListener);
}

