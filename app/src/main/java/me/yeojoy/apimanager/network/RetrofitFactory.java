package me.yeojoy.apimanager.network;

import me.yeojoy.apimanager.BuildConfig;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public class RetrofitFactory {

    public static Retrofit createDefaultRetrofit() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            // add logging as last interceptor
            httpClient.addInterceptor(logging);  // <-- this is the important line!
        }

        return new Retrofit.Builder()
//                .baseUrl("http://192.168.1.30:8080") // 로컬 API
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(httpClient.build())
                .build();
    }
}
