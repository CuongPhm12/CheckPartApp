package com.example.checkpartapp.model;

public class PartItem {
    private String PART_ID;
    private String SITE_ID;
    private String UPN_ID;

    public PartItem(String PART_ID, String SITE_ID, String UPN_ID) {
        this.PART_ID = PART_ID;
        this.SITE_ID = SITE_ID;
        this.UPN_ID = UPN_ID;
    }

    public String getPART_ID() {
        return PART_ID;
    }

    public void setPART_ID(String PART_ID) {
        this.PART_ID = PART_ID;
    }

    public String getSITE_ID() {
        return SITE_ID;
    }

    public void setSITE_ID(String SITE_ID) {
        this.SITE_ID = SITE_ID;
    }

    public String getUPN_ID() {
        return UPN_ID;
    }

    public void setUPN_ID(String UPN_ID) {
        this.UPN_ID = UPN_ID;
    }
}

