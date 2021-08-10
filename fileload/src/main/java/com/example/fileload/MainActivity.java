package com.example.fileload;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fileload.model.ConnectRequestInfo;
import com.example.fileload.model.ConnectResponseInfo;
import com.example.fileload.model.DownloadInfo;
import com.example.fileload.model.ResponseDownloadInfo;
import com.example.fileload.model.UploadInfo;
import com.example.fileload.model.UploadStatus;
import com.example.fileload.util.FileUtil;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;

public class MainActivity extends AppCompatActivity {

    private FileService fileService;

    @BindView(R.id.btn_request)
    Button btn_request;
    @BindView(R.id.tv_response)
    TextView tv_response;
    @BindView(R.id.tv_detail)
    TextView tv_detail;
    @BindView(R.id.et_fileName)
    EditText et_fileName;
    @BindView(R.id.btn_download)
    Button btn_download;

    ConnectResponseInfo connectResponseInfo;
    ResponseDownloadInfo responseDownloadInfo;

    FileUtil file = new FileUtil();
    DownloadInfo downloadInfo;

    Gson gson = new Gson();

    int times;
    int readLength;
    String fileName;
    String fileUrl;
    String showString = "";
    String responseConnectBodyString = "";
    String responseUploadBodyString = "";
    String responseDownloadBodyString = "";

    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        context = getApplicationContext();

        check();
        tv_response.setMovementMethod(new ScrollingMovementMethod());

        fileService = RetrofitManager.getInstance().create(FileService.class);
    }

    public void check() {
        if (Build.VERSION.SDK_INT >= 23) {
            int REQUEST_CODE_PERMISSION_STORAGE = 100;
            String[] permissions = {
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
            };

            for (String str : permissions) {
                if (this.checkSelfPermission(str) != PackageManager.PERMISSION_GRANTED) {
                    this.requestPermissions(permissions, REQUEST_CODE_PERMISSION_STORAGE);
                    return;
                }
            }
        }
    }


    @OnClick(R.id.btn_request)
    public void requestClick(View view) {
        times = 0; // TextView 排序
        fileName = et_fileName.getText().toString();

        if (fileName.equals("") || fileName.isEmpty()) {
            Toast.makeText(context, "請輸入檔案名稱！", Toast.LENGTH_SHORT).show();
        }

        connect();

        et_fileName.setText("");
    }
    public void connect() {
        readLength = 1024 * 1024;

        ConnectRequestInfo connectRequestInfo =  file.getConnectRequestInfo(fileName, context, readLength);

        fileService.connect(connectRequestInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observerFirst);

        String requestInfo = fileName + "/" +
                connectRequestInfo.getFile_size() + "\n" +
                connectRequestInfo.getChecksum();

//        runOnUiThread(() -> tv_detail.setText(requestInfo));
        tv_detail.setText(requestInfo);
    }

    Observer observerFirst = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.d("TAG", "Connect onSubscribe");
        }

        @Override
        public void onNext(@NotNull ResponseBody responseBody) {
            Log.d("TAG", "Connect onNext");
            try {
                responseConnectBodyString = responseBody.string();
                //Log.d("TAG", responseConnectionBodyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showString = "connection: " + responseConnectBodyString + "\n";

            connectResponseInfo = new Gson().fromJson(responseConnectBodyString, ConnectResponseInfo.class);

            if (connectResponseInfo.getStatus() == 0) {
//                upload();
            }
        }

        @Override
        public void onError(@NotNull Throwable e) {
            Log.d("TAG", "Connect onError: " + e);
        }

        @Override
        public void onComplete() {
            Log.d("TAG", "Connect onComplete");
        }

    };

    public void upload() {
        readLength = connectResponseInfo.getUpload_maximum();

        UploadInfo uploadInfo = file.getUploadInfo(fileName, context, readLength, connectResponseInfo);
        String readString = file.getReadString(fileName, context, readLength);

        RequestBody requestFile = RequestBody.create(MediaType.parse("application/json"), gson.toJson(uploadInfo));
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), readString);

        if (uploadInfo.getUpload_status() == 1) {
            doSomething(requestFile, requestBody);
        }
    }

    public void doSomething(RequestBody requestFile, RequestBody requestBody) {

        fileService.uploadFile(requestFile, requestBody)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        Log.d("TAG", "onSubscribe ");
                    }

                    @Override
                    public void onNext(@NotNull ResponseBody responseBody) {
                        ++times;
                        try {
                            responseUploadBodyString = responseBody.string();
                            UploadStatus uploadStatus = new Gson().fromJson(responseUploadBodyString, UploadStatus.class);
                            Log.d("TAG", "responseUploadBodyString: " + responseUploadBodyString);

                            if (uploadStatus.getStatus() == 0) {
                                upload();
                            } else {

                            }

                            showString += times + ".upload: " + responseUploadBodyString + "\n";
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Log.d("TAG", "onNext ");
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        Log.d("TAG", "onError : " + e);
                    }

                    @Override
                    public void onComplete() {
                        runOnUiThread(() -> tv_response.setText(showString));
                        Log.d("TAG", "onComplete ");
                    }

                });
    }


    @OnClick(R.id.btn_download)
    public void downloadClick(View view) {
        downloadInfo = new DownloadInfo();
        downloadInfo.setFile_id(connectResponseInfo.getFile_id());


        fileName = et_fileName.getText().toString();

        if (fileName.equals("") || fileName.isEmpty()) {
            Toast.makeText(context, "請輸入檔案名稱！", Toast.LENGTH_SHORT).show();
        }

        fileService.downloadConnection(downloadInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(downloadObserver);

        fileUrl = fileName;
    }

    Observer downloadObserver = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.d("TAG", "onSubscribe");
        }

        @Override
        public void onNext(@NotNull ResponseBody responseBody) {
            try {
                responseDownloadBodyString = responseBody.string();
                //Log.d("TAG", responseConnectionBodyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showString = "connection: " + responseConnectBodyString + "\n";

            responseDownloadInfo = new Gson().fromJson(responseDownloadBodyString, ResponseDownloadInfo.class);

            if (responseDownloadInfo.getStatus() == 0) {
                download();
            }

        }

        @Override
        public void onError(@NotNull Throwable e) {
            Log.d("TAG", "onError: " + e);
        }

        @Override
        public void onComplete() {
            Log.d("TAG", "onComplete");
        }

    };

    public void download() {
        fileService.download("upload\\/1\\/" + fileUrl)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(downloadFileObserver);
    }

    Observer downloadFileObserver = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.d("TAG", "onSubscribe");
        }

        @Override
        public void onNext(@NotNull ResponseBody responseBody) {
            Log.d("TAG", "onNext: ");
//            try {
//                File path = Environment.getExternalStorageDirectory();
//                File file = new File(path, "file_name.jpg");
//                FileOutputStream fileOutputStream = new FileOutputStream(file);
//                IOUtils.write(responseBody.bytes(), fileOutputStream);
//            }
//            catch (Exception ex){
//            }
        }

        @Override
        public void onError(@NotNull Throwable e) {
            Log.d("TAG", "onError: " + e);
        }

        @Override
        public void onComplete() {
            Log.d("TAG", "onComplete");
        }

    };

}