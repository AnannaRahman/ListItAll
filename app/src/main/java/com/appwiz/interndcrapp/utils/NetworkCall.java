package com.appwiz.interndcrapp.utils;

import com.appwiz.interndcrapp.model.data;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NetworkCall {

    //region GET
    @GET("appinion-dev/intern-dcr-data/master/data.json")
    Observable<data> GetSampleData();



    //endregion
}
