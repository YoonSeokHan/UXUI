package com.conoz.singletour;

/**
 * Created by yunhee on 2017. 8. 25..
 */

public class CzMissionItem {
    private String missionClearNo;
    private String memberNo;
    private String nickName;
    private String missionContents;
    private String deadline;
    private String promiseContents;
    private String onePicCnt;


    // Constructor.
    public CzMissionItem(String missionClearNo, String memberNo, String nickName, String missionContents, String deadline, String promiseContents, String onePicCnt) {
        this.missionClearNo = missionClearNo;
        this.memberNo = memberNo;
        this.nickName = nickName;
        this.missionContents = missionContents;
        this.deadline = deadline;
        this.promiseContents = promiseContents;
        this.onePicCnt = onePicCnt;
    }

    public CzMissionItem() {
    }

    public String getMissionClearNo() {
        return missionClearNo;
    }

    public void setMissionClearNo(String missionClearNo) {
        this.missionClearNo = missionClearNo;
    }

    public String getMemberNo() {
        return memberNo;
    }

    public void setMemberNo(String memberNo) {
        this.memberNo = memberNo;
    }
    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    public String getMissionContents() {
        return missionContents;
    }

    public void setMissionContents(String missionContents) {
        this.missionContents = missionContents;
    }
    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public String getPromiseContents() {
        return promiseContents;
    }

    public void setPromiseContents(String promiseContents) {
        this.promiseContents = promiseContents;
    }

    public String getOnePicCnt() {
        return onePicCnt;
    }

    public void setOnePicCnt(String onePicCnt) {
        this.onePicCnt = onePicCnt;
    }

}
