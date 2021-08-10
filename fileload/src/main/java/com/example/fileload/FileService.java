package com.example.fileload;

import com.example.fileload.model.DownloadInfo;
import com.example.fileload.model.ConnectRequestInfo;

import io.reactivex.Observable;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Url;

public interface FileService {

    @POST("upload_hs.php")
    Observable<ResponseBody> connect(@Body ConnectRequestInfo fileInfo);

    @POST("upload.php")
    @Multipart
    Observable<ResponseBody> uploadFile(@Part ("Data\"; filename=\"data.txt")RequestBody description,
                                        @Part ("Upload\"; filename=\"upload.txt")RequestBody file);

    @POST("download_hs.php")
    Observable<RequestBody> downloadConnection(@Body DownloadInfo downloadInfo);

    @GET()
    Observable<RequestBody> download(@Url String fileUrl);

}
