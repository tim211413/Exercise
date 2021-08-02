package com.example.retrofittext;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MyAPIService {

    // 設置一個 GET 連線, 路徑為 albums/1
    @GET("albums/1")
    // 取得的回傳資料用 Albums 物件接收, 連線名稱取為 getAlbums
    Call<Albums> getAlbums();

    // 用 {} 表示路徑參數, @Path 會將參數帶入致該位置
    @GET("albums/{id}")
    Call<Albums> getAlbumsById(@Path("id") int id);

    // 用 @Body 表示要傳送 Body 資料
    @POST("albums")
    Call<Albums> postAlbums(@Body Albums albums);
}
