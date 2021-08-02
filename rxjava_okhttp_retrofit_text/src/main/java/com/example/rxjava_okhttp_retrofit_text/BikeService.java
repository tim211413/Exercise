package com.example.rxjava_okhttp_retrofit_text;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BikeService {

    @GET("api/v1/rest/datastore/a1b4714b-3b75-4ff8-a8f2-cc377e4eaa0f")
    Observable<ResponseBody> getBike(@Query("limit") int limit,
                                     @Query("offset") int offset);

//    @GET("/")
//    Observable<Bike> getBike();
}
