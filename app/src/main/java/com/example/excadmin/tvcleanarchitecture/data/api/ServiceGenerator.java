package com.example.excadmin.tvcleanarchitecture.data.api;

import android.content.Context;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class ServiceGenerator {

    public static final String API_BASE_URL = "https://storage.googleapis.com/android-tv/";

    private static OkHttpClient.Builder httpClient;

    private static Retrofit.Builder builder;

    private static Context mContext;

    public static <S> S createService(Class<S> serviceClass) {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        OkHttpClient client = httpClient.addInterceptor(interceptor).build();
        Retrofit retrofit = builder
                .client(client)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        return retrofit.create(serviceClass);
    }

    public static <S> S createService(Class<S> serviceClass, String key, Context c) {
        httpClient = new OkHttpClient.Builder();
        builder = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create());

        mContext = c;
        httpClient.addInterceptor(chain -> {
            Request original = chain.request();

            Request.Builder requestBuilder = original.newBuilder()
                    .header("Accept-Encoding", "gzip")
                    .header("Content-type", "application/x-www-form-urlencoded; charset=utf-8")
                    .header("Authorization", "Basic VnB1Zlp1b3FQWnJIa05HSDVKbng6YURGVG9Fd1NVY2sxWjhGNGh1aDJwMnN2")
                    .method(original.method(), original.body());

            Request request = requestBuilder.build();
            return chain.proceed(request);
        });

        httpClient.authenticator((route, response) -> {
            if (responseCount(response) >= 2) {
                // If both the original call and the call with refreshed token failed,
                // it will probably keep failing, so don't try again.
                return null;
            }

            return null;
//            // We need a new client, since we don't want to make another call using our client with access token
//            APIClient tokenClient = createService(APIClient.class);
//            Call<AccessToken> call = tokenClient.getRefreshAccessToken(mToken.getRefreshToken(),
//                    mToken.getClientID(), mToken.getClientSecret(), API_OAUTH_REDIRECT,
//                    "refresh_token");
//            try {
//                retrofit2.Response<AccessToken> tokenResponse = call.execute();
//                if (tokenResponse.code() == 200) {
//                    AccessToken newToken = tokenResponse.body();
//                    mToken = newToken;
//                    SharedPreferences prefs = mContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
//                    prefs.edit().putBoolean("oauth.loggedin", true).apply();
//                    prefs.edit().putString("oauth.accesstoken", newToken.getAccessToken()).apply();
//                    prefs.edit().putString("oauth.refreshtoken", newToken.getRefreshToken()).apply();
//                    prefs.edit().putString("oauth.tokentype", newToken.getTokenType()).apply();
//
//                    return response.request().newBuilder()
//                            .header("Authorization", newToken.getTokenType() + " " + newToken.getAccessToken())
//                            .build();
//                } else {
//                    return null;
//                }
//            } catch (IOException e) {
//                return null;
//            }
        });

        OkHttpClient client = httpClient.build();
        Retrofit retrofit = builder.client(client).build();
        return retrofit.create(serviceClass);
    }

    private static int responseCount(Response response) {
        int result = 1;
        while ((response = response.priorResponse()) != null) {
            result++;
        }
        return result;
    }
}
