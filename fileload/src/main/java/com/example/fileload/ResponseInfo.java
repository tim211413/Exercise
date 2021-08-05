package com.example.fileload;

public class ResponseInfo {
    private int status;
    private int file_id;
    private int upload_maximum;

    public ResponseInfo(int status, int file_id, int upload_maximum) {
        this.status = status;
        this.file_id = file_id;
        this.upload_maximum = upload_maximum;
    }

    public ResponseInfo() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getUpload_maximum() {
        return upload_maximum;
    }

    public void setUpload_maximum(int upload_maximum) {
        this.upload_maximum = upload_maximum;
    }
}
