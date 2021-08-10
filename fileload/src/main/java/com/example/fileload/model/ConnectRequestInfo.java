package com.example.fileload.model;

public class ConnectRequestInfo {
    private String file_name;
    private long file_size;
    private String checksum;

    public ConnectRequestInfo(String file_name, long file_size, String checksum) {
        this.file_name = file_name;
        this.file_size = file_size;
        this.checksum = checksum;
    }

    public ConnectRequestInfo() {
    }

    public String getFile_name() {
        return file_name;
    }

    public void setFile_name(String file_name) {
        this.file_name = file_name;
    }

    public long getFile_size() {
        return file_size;
    }

    public void setFile_size(long file_size) {
        this.file_size = file_size;
    }

    public String getChecksum() {
        return checksum;
    }

    public void setChecksum(String checksum) {
        this.checksum = checksum;
    }
}
