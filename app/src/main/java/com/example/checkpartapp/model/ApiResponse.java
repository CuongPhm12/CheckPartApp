package com.example.checkpartapp.model;

import java.util.List;

public class ApiResponse {
    private boolean status;
    private List<PartItem> result;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<PartItem> getResult() {
        return result;
    }

    public void setResult(List<PartItem> result) {
        this.result = result;
    }
}
