package com.conoz.singletour;

/**
 * Created by yunhee on 2017. 8. 25..
 */

public class CzFullItem {
    private String fullyNo;
    private String title;
    private String address;
    private String telNo;
    private String businessHours;
    private String imgPath;
    private String latitude;
    private String longitude;


    // Constructor.
    public CzFullItem(String fullyNo, String title, String address, String telNo, String businessHours, String imgPath, String latitude, String longitude) {
        this.fullyNo = fullyNo;
        this.title = title;
        this.address = address;
        this.telNo = telNo;
        this.businessHours = businessHours;
        this.imgPath = imgPath;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public CzFullItem() {
    }

    public String getFullyNo() {
        return fullyNo;
    }
    public void setFullyNo(String fullyNo) {
        this.fullyNo = fullyNo;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelNo() {
        return telNo;
    }
    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getBusinessHours() {
        return businessHours;
    }
    public void setBusinessHours(String businessHours) {
        this.businessHours = businessHours;
    }

    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    public String getLatitude() {
        return latitude;
    }
    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }
    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

}
