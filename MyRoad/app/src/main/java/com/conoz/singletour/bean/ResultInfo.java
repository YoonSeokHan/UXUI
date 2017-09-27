package com.conoz.singletour.bean;

import com.conoz.singletour.CzEnjoyItem;
import com.conoz.singletour.CzFullItem;
import com.conoz.singletour.CzMissionItem;
import com.conoz.singletour.CzSafetyItem;

import java.io.Serializable;
import java.util.ArrayList;

public class ResultInfo implements Serializable{
    private static final long serialVersionUID = -6032117996850679149L;
    private String statusCd;
    private String statusMssage;
    private String errorCd;
    private String errorMsg;
    private String outs;
    private String message;
    //--------------------------
    private String memberNo;
    private String userEmail;
    //--------------------------
    private ArrayList<CzMissionItem> missionItems;
    private ArrayList<CzFullItem> fullItems;
    private ArrayList<CzEnjoyItem> enjoyItems;
    private ArrayList<CzSafetyItem> safetyItems;

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public String getStatusMssage() {
        return statusMssage;
    }

    public void setStatusMssage(String statusMssage) {
        this.statusMssage = statusMssage;
    }

    public String getErrorCd() {
        return errorCd;
    }

    public void setErrorCd(String errorCd) {
        this.errorCd = errorCd;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public String getOuts() {
        return outs;
    }

    public void setOuts(String outs) {
        this.outs = outs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    //--------------------------

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
    //--------------------------

    public ArrayList<CzMissionItem> getMissionItems() {
        return missionItems;
    }

    public void setMissionItems(ArrayList<CzMissionItem> missionItems) {
        this.missionItems = missionItems;
    }

    public ArrayList<CzFullItem> getFullItems() {
        return fullItems;
    }

    public void setFullItems(ArrayList<CzFullItem> fullItems) {
        this.fullItems = fullItems;
    }
    public ArrayList<CzEnjoyItem> getEnjoyItems() {
        return enjoyItems;
    }

    public void setEnjoyItems(ArrayList<CzEnjoyItem> enjoyItems) {
        this.enjoyItems = enjoyItems;
    }
    public ArrayList<CzSafetyItem> getSafetyItems() {
        return safetyItems;
    }

    public void setSafetyItems(ArrayList<CzSafetyItem> safetyItems) {
        this.safetyItems = safetyItems;
    }

}
