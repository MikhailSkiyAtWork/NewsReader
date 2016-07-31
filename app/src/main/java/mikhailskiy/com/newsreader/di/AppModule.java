package mikhailskiy.com.newsreader.di;

import android.content.Context;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import mikhailskiy.com.newsreader.NewsReaderApplication;
import mikhailskiy.com.newsreader.R;
import mikhailskiy.com.newsreader.db.storage.NewsStorage;
import mikhailskiy.com.newsreader.webapi.GazetaApiService;
import mikhailskiy.com.newsreader.webapi.LentaApiService;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Mikhail on 01.08.16.
 */
@Module
public class AppModule {
    private final NewsReaderApplication application;

    public AppModule(NewsReaderApplication application) {
        this.application = application;
    }

    /**
     * Allow the application context to be injected but require that it be annotated with
     * {@link ForApplication @Annotation} to explicitly differentiate it from an activity context.
     */
    @Provides
    @Singleton
    @ForApplication
    Context providesNewsReaderApplicationContext() {
        return application;
    }

    @Provides
    @Singleton
    GazetaApiService providesGazetaApiService(@ForApplication Context context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.gazeta_url))
                .addConverterFactory(SimpleXmlConverterFactory.create());

        Retrofit retrofit = builder.build();
        return retrofit.create(GazetaApiService.class);
    }

    @Provides
    @Singleton
    LentaApiService providesLentaApiService(@ForApplication Context context) {
        Retrofit.Builder lentaBuilder = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.lenta_url))
                .addConverterFactory(SimpleXmlConverterFactory.create());

        Retrofit retrofit = lentaBuilder.build();
        return retrofit.create(LentaApiService.class);
    }


    @Provides
    @Singleton
    Picasso providesPicasso(@ForApplication Context context) {
        Picasso picasso = Picasso.with(context);
        return picasso;
    }

    @Provides
    @Singleton
    NewsStorage providesStorage(@ForApplication Context context) {
        NewsStorage storage = new NewsStorage();
        return storage;
    }
}

