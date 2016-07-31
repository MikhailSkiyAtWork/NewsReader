package mikhailskiy.com.newsreader;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import mikhailskiy.com.newsreader.webapi.WebApiProvider;

/**
 * Created by Mikhail on 28.07.16.
 */
public class NewsReaderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        WebApiProvider.init(this);
        FlowManager.init(new FlowConfig.Builder(this).build());
    }
}
