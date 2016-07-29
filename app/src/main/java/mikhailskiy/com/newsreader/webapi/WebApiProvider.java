package mikhailskiy.com.newsreader.webapi;

import android.content.Context;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

/**
 * Created by Mikhail on 28.07.16.
 */
public class WebApiProvider {

    private static final String BASE_URL_GAZETA = "http://www.gazeta.ru/";
    private static final String BASE_URL_LENTA = "https://lenta.ru/";

    private static volatile WebApiProvider instance_;

    private final Retrofit retrofit_;
    private final GazetaApiService gazetaApiService_;
    private final LentaApiService lentaApiService_;

    public static void init(Context context) {
        if (instance_ == null) {
            synchronized (WebApiProvider.class) {
                if (instance_ == null) {
                    instance_ = new WebApiProvider(context);
                }
            }
        }
    }

    private WebApiProvider(final Context context) {
        Retrofit.Builder builder = new Retrofit.Builder()
                .baseUrl(BASE_URL_GAZETA)
                .addConverterFactory(SimpleXmlConverterFactory.create());

        OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                // Inject "Content-Type" header
                Request request;
                request = chain.request().newBuilder().addHeader("Content-Type", "application/xml").build();
                return chain.proceed(request);
            }
        });


        builder.client(clientBuilder.build());
        retrofit_ = builder.build();
        gazetaApiService_ = retrofit_.create(GazetaApiService.class);

        // Build Lenta service
        Retrofit.Builder xigniteBuilder = new Retrofit.Builder()
                .baseUrl(BASE_URL_LENTA)
                .addConverterFactory(SimpleXmlConverterFactory.create());


        lentaApiService_ = xigniteBuilder.build().create(LentaApiService.class);
    }

    private static WebApiProvider getInstance() {
        if (instance_ == null) {
            throw new RuntimeException("You should init WebApiProvider first with init()");
        }
        return instance_;
    }

    public static GazetaApiService getGazetaApiService() {
        return WebApiProvider.getInstance().gazetaApiService_;
    }

    public static LentaApiService getLentaApiService() {
        return WebApiProvider.getInstance().lentaApiService_;
    }

    private Retrofit getRetrofit() {
        return retrofit_;
    }

    public static Retrofit getRetrofitInstance() {
        return getInstance().getRetrofit();
    }
}
