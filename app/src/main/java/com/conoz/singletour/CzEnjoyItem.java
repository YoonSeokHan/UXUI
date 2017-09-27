package com.conoz.singletour;

/**
 * Created by yunhee on 2017. 8. 25..
 */

public class CzEnjoyItem {
    private String enjoyNo;
    private String beaconId;
    private String eType;
    private String placeName;


    // Constructor.
    public CzEnjoyItem(String enjoyNo, String beaconId, String eType, String placeName) {
        this.enjoyNo = enjoyNo;
        this.beaconId = beaconId;
        this.eType = eType;
        this.placeName = placeName;
    }

    public CzEnjoyItem() {
    }

    public String getEnjoyNo() {
        return enjoyNo;
    }
    public void setEnjoyNo(String enjoyNo) {
        this.enjoyNo = enjoyNo;
    }

    public String getBeaconId() {
        return beaconId;
    }
    public void setBeaconId(String beaconId) {
        this.beaconId = beaconId;
    }

    public String getEType() {
        return eType;
    }
    public void setEType(String eType) {
        this.eType = eType;
    }

    public String getPlaceName() {
        return placeName;
    }
    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }


}
