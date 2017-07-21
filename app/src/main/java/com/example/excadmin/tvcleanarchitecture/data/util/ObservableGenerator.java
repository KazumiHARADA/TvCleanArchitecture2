package com.example.excadmin.tvcleanarchitecture.data.util;

import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by excadmin on 2017/07/21.
 */

public class ObservableGenerator <T> {

    public Observable<T> generate (Call<T> call) {
        Single<T> single = Single.create(singleSubscribeOn -> {
            call.enqueue(new Callback<T>() {
                @Override
                public void onResponse(Call<T> call, Response<T> response) {
                    response.body();
                    singleSubscribeOn.onSuccess(response.body());
                }

                @Override
                public void onFailure(Call<T> call, Throwable t) {
                    singleSubscribeOn.onError(t);
                }
            });
        });
        return single.toObservable();
    }
}
