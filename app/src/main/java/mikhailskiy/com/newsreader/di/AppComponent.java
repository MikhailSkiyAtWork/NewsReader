package mikhailskiy.com.newsreader.di;

import javax.inject.Singleton;

import dagger.Component;
import mikhailskiy.com.newsreader.activities.MainActivity;

/**
 * Created by Mikhail on 01.08.16.
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity mainActivity);
}
