package com.example.fileload;

public class UploadInfo {
    private int file_id;
    private int upload_status;
    private String checksum;

    public UploadInfo(int file_id, int upload_status, String checksum) {
        this.file_id = file_id;
        this.upload_status = upload_status;
        this.checksum = checksum;
    }

    public int getFile_id() {
        return file_id;
    }

    public void setFile_id(int file_id) {
        this.file_id = file_id;
    }

    public int getUpload_status() {
        return upload_status;
    }

    public void setUpload_status(int upload_status) {
        this.upload_status = upload_status;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
