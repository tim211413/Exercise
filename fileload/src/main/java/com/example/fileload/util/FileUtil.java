package com.example.fileload.util;

import android.content.Context;
import android.util.Log;

import com.example.fileload.model.ConnectRequestInfo;
import com.example.fileload.model.ConnectResponseInfo;
import com.example.fileload.model.UploadInfo;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class FileUtil {
    ConnectRequestInfo connectRequestInfo;
    //    ConnectResponseInfo connectResponseInfo;
    UploadInfo uploadInfo = new UploadInfo();

    int count;

    public String readFile(String fileName, Context context, int readLength, int start) {

        String path = "/data/user/0/com.example.fileload/files/text.txt";
        File file = new File(path);
        int fileSize = Integer.parseInt(String.valueOf(file.length()));

        String read = "";
        try (FileInputStream fin = context.openFileInput(fileName);
             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {

            byte[] buffer = new byte[fileSize];
            Log.d("TAG", "buffer.len: " + buffer.length);

            int flag = bufferedInputStream.read(buffer, start, readLength);
//            int flag = bufferedInputStream.read(buffer);
            if (flag == -1) {
//                break;
//                return "";
            } else {
                read = (new String(buffer, 0, flag));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("TAG", "hello: " + read);
        Log.d("TAG", "hello.length(): " + read.length());

        return read;
    }

//    public String readFile(String fileName, Context context, int readLength) {
//        String read = "";
//        try (FileInputStream fin = context.openFileInput(fileName);
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
//
//            byte[] buffer = new byte[readLength];
//
//            do {
//                int flag = bufferedInputStream.read(buffer);
//                if (flag == -1) {
//                    break;
//                } else {
//                    read += (new String(buffer, 0, flag)) + ",";
//                }
//            } while (true);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("TAG", "hello: " + read);
//        Log.d("TAG", "hello.length(): " + read.length());
//
//        return read;
//    }

//    public String readFile(String fileName, Context context, int readLength) {
//        String read = "";
//        try (FileInputStream fin = context.openFileInput(fileName);
//             BufferedInputStream bufferedInputStream = new BufferedInputStream(fin)) {
//
//            byte[] buffer = new byte[readLength];
//
//            do {
//                int flag = bufferedInputStream.read(buffer);
//                if (flag == -1) {
//                    break;
//                } else {
//                    read += (new String(buffer, 0, flag)) ;
//                }
//            } while (true);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Log.d("TAG", "hello: " + read);
//        Log.d("TAG", "hello.length(): " + read.length());
//
//        return read;
//    }

    public ConnectRequestInfo getConnectRequestInfo(String fileName, Context context, int readLength) {
        connectRequestInfo = new ConnectRequestInfo();

        String path = "/data/user/0/com.example.fileload/files/text.txt";
        File file = new File(path);
        int fileSize = Integer.parseInt(String.valueOf(file.length()));

        int count = (int) Math.ceil(((float) fileSize / readLength));
        Log.d("TAG", "getConnectRequestInfo. fileSize: " + fileSize);
        Log.d("TAG", "getConnectRequestInfo. readLength: " + readLength);
        Log.d("TAG", "getConnectRequestInfo. count: " + count);

        String read = "";
//        read = readFile(fileName, context, readLength);

//        read.replaceAll(",", "");

        int start = 0;

        while (count > 0) {
            if(count == 1) {
                read += readFile(fileName, context, fileSize - start*readLength, start*readLength-1);
            } else {
                read += readFile(fileName, context, readLength, start*readLength);
//            read += readFile(fileName, context, readLength);
            }
            ++start;
            --count;
            Log.d("TAG", "getConnectRequestInfo. while(). count: " + count);
        }

        Log.d("TAG", "getConnectRequestInfo.while.read.len: " + read.length());

        connectRequestInfo.setFile_size(fileSize);
        connectRequestInfo.setFile_name(fileName);
        connectRequestInfo.setChecksum(SHA256.shaEncrypt(read));

        Log.d("TAG", "checksum in read " + connectRequestInfo.getChecksum());
        Log.d("TAG", "fileSize in read " + connectRequestInfo.getFile_size());

        return connectRequestInfo;
    }


    public UploadInfo getUploadInfo(String fileName, Context context, int readLength, ConnectResponseInfo connectResponseInfo) {
        uploadInfo = new UploadInfo();

        String path = "/data/user/0/com.example.fileload/files/text.txt";
        File file = new File(path);
        int fileSize = Integer.parseInt(String.valueOf(file.length()));

        count = (int) Math.ceil((float) fileSize / connectResponseInfo.getUpload_maximum());

        String read = "";

//        read = readFile(fileName, context, readLength);

        for(String result : read.split(",")) {
//            result
        }

//        String hello = read.substring(2 * readLength);
//        Log.d("TAG", "getUploadInfo. substring: " + read.substring(readLength));

        uploadInfo.setFile_id(connectResponseInfo.getFile_id());
        uploadInfo.setChecksum(SHA256.shaEncrypt(read));
        uploadInfo.setUpload_status(getStatus());

        return uploadInfo;
    }

    public String getReadString(String fileName, Context context, int readLength) {
        String read = "";

//        read = readFile(fileName, context, readLength);


//        String hello = read.substring(2 * readLength);

        return read;
    }


    public int getStatus() {
        if (count > 0) {
            uploadInfo.setUpload_status(1);
        } else {
            uploadInfo.setUpload_status(2);
        }
        --count;

        Log.d("TAG", "FileUtil getStatus: " + uploadInfo.getUpload_status() + ", count: " + count);
        return uploadInfo.getUpload_status();
    }

}
