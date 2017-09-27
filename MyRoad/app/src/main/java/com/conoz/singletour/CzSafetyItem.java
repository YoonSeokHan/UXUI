package com.conoz.singletour;

/**
 * Created by yunhee on 2017. 8. 25..
 */

public class CzSafetyItem {
    private String safetyNo;
    private String accommodationsName;
    private String imgPath;
    private String address;
    private String telNo;
    private String priceComment;


    // Constructor.
    public CzSafetyItem(String safetyNo, String accommodationsName, String imgPath, String address, String telNo, String priceComment) {
        this.safetyNo = safetyNo;
        this.accommodationsName = accommodationsName;
        this.imgPath = imgPath;
        this.address = address;
        this.telNo = telNo;
        this.priceComment = priceComment;
    }

    public CzSafetyItem() {
    }

    public String getSafetyNo() {
        return safetyNo;
    }
    public void setSafetyNo(String safetyNo) {
        this.safetyNo = safetyNo;
    }

    public String getAccommodationsName() {
        return accommodationsName;
    }
    public void setAccommodationsName(String accommodationsName) {
        this.accommodationsName = accommodationsName;
    }

    public String getImgPath() {
        return imgPath;
    }
    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
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

    public String getPriceComment() {
        return priceComment;
    }
    public void setPriceComment(String priceComment) {
        this.priceComment = priceComment;
    }


}
