package mikhailskiy.com.newsreader.db;

import com.raizlabs.android.dbflow.annotation.Database;

/**
 * Created by Mikhail on 31.07.16.
 */
@Database(name = NewsReaderDatabase.DB_NAME, version = NewsReaderDatabase.VERSION)
public class NewsReaderDatabase {
    public static final String DB_NAME = "NewsReaderDatabase";
    public static final int VERSION = 1;
}

