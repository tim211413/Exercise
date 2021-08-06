package com.example.fileload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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

    Gson gson = new Gson();
    FileInfo fileInfo;
    UploadInfo uploadInfo;
    ResponseInfo responseInfo = new ResponseInfo();

    SHA256 sha256;
    String checksum;
    int uploadStatus;
    int count;
    int file_id;
    long fileSize;
    int uploadMaximum;
    int times;
    String fileName;
    String showString = "";
    String responseConnectionBodyString = "";
    String responseUploadBodyString = "";
    Boolean isFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

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
        times = 0;
        isFinish = false;

        fileName = et_fileName.getText().toString();

        readFileFirst(fileName);

        fileInfo = new FileInfo(fileName, fileSize, checksum);

        fileService.connect(fileInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(observerFirst);

        et_fileName.setText("");

        tv_detail.setText(fileName + "/" + fileSize + "\n" + checksum);
    }

    public void readFileFirst(String fileName) {
        sha256 = new SHA256();

        try (FileInputStream fin = openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[1024 * 1024];

            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    sha256.setSrc(new String(buffer, 0, flag));
                }
            } while (true);
            checksum = sha256.shaEncryptFirst();
            Log.d("TAG", "checksum in read " + checksum);
            fileSize = sha256.getbtSize();
            Log.d("TAG", "fileSize in read " + fileSize);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    Observer observerFirst = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.d("TAG", "onSubscribe");
        }

        @Override
        public void onNext(@NotNull ResponseBody responseBody) {
            try {
                responseConnectionBodyString = responseBody.string();
                Log.d("TAG", responseConnectionBodyString);
            } catch (IOException e) {
                e.printStackTrace();
            }
            showString = "connection: " + responseConnectionBodyString + "\n";

            responseInfo = new Gson().fromJson(responseConnectionBodyString, ResponseInfo.class);

            file_id = responseInfo.getFile_id();
            uploadMaximum = responseInfo.getUpload_maximum();

            Log.d("TAG", "file_id: " + file_id);
            Log.d("TAG", "uploadMaximum: " + uploadMaximum);

            if (responseInfo.getStatus() == 0) {
                uploadStatus = 1;
                count = 0;
                readFile(fileName);
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

    public void readFile(String fileName) {
        try (FileInputStream fin = openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[uploadMaximum];
            Log.d("TAG", "uploadMaximum: " + uploadMaximum);
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    checksum = sha256.shaEncrypt(new String(buffer, 0, flag));
//                    Log.d("TAG", "checkSum: " + checksum);
//                    Log.d("TAG", "read word: " + new String(buffer, 0, flag));
                    ++count;
                    Log.d("TAG", "count = " + count);

                    if ((fileSize / uploadMaximum) >= count) {
                        uploadStatus = 1;
                    } else {
                        uploadStatus = 2;
                    }
                    //Log.d("TAG", "(fileSize / uploadMaximum) = " + (int)(fileSize / uploadMaximum));

                    uploadInfo = new UploadInfo(file_id, uploadStatus, checksum);

                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/json"), gson.toJson(uploadInfo));
                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), new String(buffer, 0, flag));


                    while (!isFinish) {
                        Log.d("TAG", "while isFinish: " + isFinish);
                        if (!isFinish) {
                            Log.d("TAG", "if in while isFinish1: " + isFinish);

                            doSomething(requestFile, requestBody);

                            Log.d("TAG", "if in while isFinish2: " + isFinish);
                        } else {
                            break;
                        }
                    }

                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void doSomething(RequestBody requestFile, RequestBody requestBody) {

        fileService.uploadFile(requestFile, requestBody)
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(@NotNull Disposable d) {
                        isFinish = false;
                        Log.d("TAG", "onSubscribe " + isFinish);
                    }

                    @Override
                    public void onNext(@NotNull ResponseBody responseBody) {
                        ++times;
                        try {
                            responseUploadBodyString = responseBody.string();
                            UploadStatus uploadStatus = new Gson().fromJson(responseUploadBodyString, UploadStatus.class);
                            Log.d("TAG", "responseUploadBodyString: " + responseUploadBodyString);

                            if (uploadStatus.getStatus() == 0) {
                                isFinish = true;
                            }
                            showString += times + ".upload: " + responseUploadBodyString + "\n";
                        } catch (IOException e) {
                            isFinish = false;
                            e.printStackTrace();
                        }
                        Log.d("TAG", "onNext " + isFinish);
                    }

                    @Override
                    public void onError(@NotNull Throwable e) {
                        isFinish = false;
                        Log.d("TAG", "onError : " + e + isFinish);
                    }

                    @Override
                    public void onComplete() {
                        runOnUiThread(() -> tv_response.setText(showString));
                        Log.d("TAG", "onComplete " + isFinish);
                    }
                });
    }

    @OnClick(R.id.btn_download)
    public void downloadClick(View view) {
        fileService.downloadConnection();
        fileName = et_fileName.getText().toString();
        String fileUrl = fileName;
        fileService.download("upload\\/1\\/" + fileUrl);
    }
}