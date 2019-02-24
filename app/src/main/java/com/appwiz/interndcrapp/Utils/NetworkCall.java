package com.appwiz.interndcrapp.Utils;

import com.appwiz.interndcrapp.Model.InternDCR;


import io.reactivex.Observable;
import retrofit2.http.GET;


public interface NetworkCall {

    //region GET
    @GET("appinion-dev/intern-dcr-data/master/data.json")
    Observable<InternDCR> GetInternDRC();



    //endregion
}
