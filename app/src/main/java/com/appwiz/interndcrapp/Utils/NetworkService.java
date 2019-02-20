package com.appwiz.interndcrapp.Utils;


import android.os.Handler;

import com.appwiz.interndcrapp.Model.InternDRC;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;

public class NetworkService {

    private NetworkCall networkCall = null;
    private NetworkCall networkCall2 = null;

    private Handler handler;
    public static int SUCCESS = 1;
    public static int FAILED = 0;

    public NetworkService() {
        Retrofit retrofit = RestClient.getClient();
        networkCall = retrofit.create(NetworkCall.class);
    }



    public Observable<InternDRC> GetInternDRC() {

        return networkCall.GetInternDRC();


    }

    //endregion

}
