package mikhailskiy.com.newsreader;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowConfig;
import com.raizlabs.android.dbflow.config.FlowManager;

import mikhailskiy.com.newsreader.di.AppComponent;
import mikhailskiy.com.newsreader.di.AppModule;
import mikhailskiy.com.newsreader.di.DaggerAppComponent;

/**
 * Created by Mikhail on 28.07.16.
 */
public class NewsReaderApplication extends Application {
    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        FlowManager.init(new FlowConfig.Builder(this).build());
        component = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
    }

    public AppComponent component() {
        return component;
    }
}
