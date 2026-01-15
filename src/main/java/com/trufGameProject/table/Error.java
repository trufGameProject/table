package com.trufGameProject.table;

public class Error {
    private String timestamp;
    private int status;
    private String error;
    private int code;

    public Error(String timestamp, int status, String error, int code) {
        this.timestamp =  timestamp;
        this.status = status;
        this.error = error;
        this.code = code;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String set) {
        timestamp = set;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int set) {
        status = set;
    }

    public String getError() {
        return error;
    }

    public void setError(String set) {
        error = set;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int set) {
        code = set;
    }
}
