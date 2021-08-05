package com.example.fileload;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface FileService {

    @POST("upload_hs.php")
    Observable<ResponseBody> connect(@Body FileInfo fileInfo);

    @POST("upload.php")
    @Multipart
    Observable<ResponseBody> uploadFile(@Part ("Data\"; filename=\"data.txt")RequestBody description,
                                        @Part ("Upload\"; filename=\"upload.txt")RequestBody file);

}