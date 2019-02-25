package com.appwiz.interndcrapp.utils;


import android.os.Handler;

import com.appwiz.interndcrapp.model.data;

import io.reactivex.Observable;
import retrofit2.Retrofit;

public class NetworkService {

    private NetworkCall networkCall = null;


    public NetworkService() {
        Retrofit retrofit = RestClient.getClient();
        networkCall = retrofit.create(NetworkCall.class);
    }



    public Observable<data> GetSample() {

        return networkCall.GetSampleData();


    }

    //endregion

}
