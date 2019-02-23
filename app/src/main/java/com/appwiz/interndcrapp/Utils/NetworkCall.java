package com.appwiz.interndcrapp.Utils;

import com.appwiz.interndcrapp.Model.InternDRC;


import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface NetworkCall {

    //region GET
    @GET("appinion-dev/intern-dcr-data/master/data.json")
    Observable<InternDRC> GetInternDRC();



    //endregion
}
