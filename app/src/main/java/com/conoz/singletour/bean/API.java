package com.conoz.singletour.bean;

/**
 * Created by yunhee on 2017. 8. 24..
 */

public class API {
    public static final String FILE_UPLOAD_URL              =   "http://singletour.conoz.com/fileUpload.php";
    public static final String HOST_URL                     =   "http://singletour.conoz.com/rest/";
    //public static final String URI_META_EV                  =   HOST_URL+"meta/ev/";

    //for member
    public static final String URI_MEMBER_LOGIN             =   HOST_URL+"if001/IF-ST-001/";
    public static final String URI_MEMBER_REGIST            =   HOST_URL+"if001/IF-ST-002/";
    public static final String URI_MEMBER_UPDATE            =   HOST_URL+"if001/IF-ST-003/";

    //for mission
    public static final String URI_MISSION_LIST             =   HOST_URL+"if002/IF-ST-001/";
    public static final String URI_MISSION_VIEW             =   HOST_URL+"if002/IF-ST-002/";
    public static final String URI_MISSION_REGIST           =   HOST_URL+"if002/IF-ST-003/";
    public static final String URI_MISSION_UPDATE           =   HOST_URL+"if002/IF-ST-004/";
    public static final String URI_MISSION_DELETE           =   HOST_URL+"if002/IF-ST-005/";
    public static final String URI_MISSION_COMMENT_LIST     =   HOST_URL+"if002/IF-ST-006/";
    public static final String URI_MISSION_COMMENT_REGIST   =   HOST_URL+"if002/IF-ST-007/";
    public static final String URI_MISSION_COMMENT_DELETE   =   HOST_URL+"if002/IF-ST-008/";
    public static final String URI_MISSION_ONEPIC           =   HOST_URL+"if002/IF-ST-009/";

    //for companion
    public static final String URI_COMPANION_VIEW           =   HOST_URL+"if003/IF-ST-001/";
    public static final String URI_COMPANION_REGIST         =   HOST_URL+"if003/IF-ST-002/";

    //for safety
    public static final String URI_SAFETY_LIST              =   HOST_URL+"if004/IF-ST-001/";
    public static final String URI_SAFETY_VIEW              =   HOST_URL+"if004/IF-ST-002/";

    //for fully
    public static final String URI_FULLY_LIST               =   HOST_URL+"if005/IF-ST-001/";

    //for enjoy
    public static final String URI_ENJOY_LIST               =   HOST_URL+"if006/IF-ST-001/";
    public static final String URI_ENJOY_STAMP              =   HOST_URL+"if006/IF-ST-002/";




    //API Variables
    public class RequestVariables{
        public static final String DEVICE_ID = "deviceId";
        public static final String USER_ID = "userId";
        public static final String OS = "os";
        public static final String APP_OS = "app_os";
        public static final String DEVICE_PUSH_TOKEN = "pushToken";
        public static final String MEMBER_NO = "memberNo";

        //Board
        public static final String TITLE    = "title";
        public static final String CONTENTS = "contents";
        public static final String FILE1    = "filePath1";
        public static final String FILE2    = "filePath2";
        public static final String FILE3    = "filePath3";
        public static final String FILE4    = "filePath4";
        public static final String PAGE_NO  = "pageNo";


        //CzLoginFragment
        public static final String USER_EMAIL = "userEmail";
        public static final String USER_PWD   = "userPwd";
        public static final String NICKNAME   = "nickName";


        public static final String APPID = "appId";
        public static final String MISSIONCONTENTS = "missionContents";
        public static final String DEADLINE = "deadline";
        public static final String PROMISECONTENTS = "promiseContents";


    }
}
