package com.example.fileload;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
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
import io.reactivex.android.schedulers.AndroidSchedulers;
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
    @BindView(R.id.tv_status)
    TextView tv_status;
    @BindView(R.id.et_fileName)
    EditText et_fileName;

    Gson gson = new Gson();
    FileInfo fileInfo;
    UploadInfo uploadInfo;
    ResponseInfo responseInfo = new ResponseInfo();

    SHA256 sha256 = new SHA256();
    String checksum;
    int uploadStatus = 1;
    int count = 0;
    int file_id;
    long fileSize;
    int uploadMaximum;
    String fileName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        check();

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
        fileName = et_fileName.getText().toString();

        readFileFirst(fileName);

        fileInfo = new FileInfo(fileName, fileSize, checksum);

        fileService.connect(fileInfo)
                .observeOn(Schedulers.io())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(observerFirst);

        Log.d("TAG", "" + file_id + "/" + uploadMaximum);


        Thread thread = new Thread();
        try {
            thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        uploadStatus = 1;

        readFile(fileName);
    }

    public void readFileFirst(String fileName) {

        try (FileInputStream fin = openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
            byte[] buffer = new byte[1024*1024];

            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    sha256.setSrc(new String(buffer, 0, flag));
                }
            } while (true);
            checksum = sha256.shaEncryptFirst();
            Log.d("TAG", "checksum in observable" + checksum);
            fileSize = sha256.getbtSize();
            Log.d("TAG", "fileSize in observable" + fileSize);

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
                tv_status.setText(responseBody.string());
                //new Gson().fromJson(responseBody.string(), ResponseInfo.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            file_id = responseInfo.getFile_id();
//            uploadMaximum = responseInfo.getUpload_maximum();
            Log.d("TAG", "file_id: " + file_id);
            Log.d("TAG", "uploadMaximum: " + uploadMaximum);
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
            do {
                int flag = bufferedInputStream.read(buffer);
                if (flag == -1) {
                    break;
                } else {
                    checksum = sha256.shaEncrypt(new String(buffer, 0, flag));
                    Log.d("TAG", "checkSum: " + checksum);
                    Log.d("TAG", "read word: " + new String(buffer, 0, flag));
                    ++file_id;
                    ++count;

                    if ((fileSize / uploadMaximum) == count) {
                        uploadStatus = 2;
                    }

                    uploadInfo = new UploadInfo(file_id, uploadStatus, checksum);

                    RequestBody requestFile = RequestBody.create(MediaType.parse("application/json"), gson.toJson(uploadInfo));

                    RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain"), new String(buffer, 0, flag));

                    fileService.uploadFile(requestFile, requestBody)
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribeOn(Schedulers.io())
                            .subscribe(observer);

                    Thread thread = new Thread();
                    thread.sleep(2000);

                }
            } while (true);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    Observer observer = new Observer<ResponseBody>() {
        @Override
        public void onSubscribe(@NotNull Disposable d) {
            Log.d("TAG", "onSubscribe");
        }

        @Override
        public void onNext(@NotNull ResponseBody requestBody) {
            try {
                tv_response.setText(requestBody.string());
            } catch (IOException e) {
                e.printStackTrace();
            }
            Log.d("TAG", "onNext");
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