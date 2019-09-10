package com.moha.instaforexapp.Rest;

import com.moha.instaforexapp.Modal.ApiAccount;
import com.moha.instaforexapp.Modal.Item;
import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("api/Authentication/RequestMoblieCabinetApiToken")
    Observable<Object> getAccessToken(@Body ApiAccount account);

    @GET("clientmobile/GetAnalyticSignals" +
            "/20234561?tradingsystem=3&pairs=EURUSD,GBPUSD,USDJPY,USDCHF,USDCAD,AUDUSD,NZDUSD" +
            "&from=1556708020&to=1564656820")
    Observable<List<Item>> getAnalyticSignal(@Header("passkey")String passkey);






}
