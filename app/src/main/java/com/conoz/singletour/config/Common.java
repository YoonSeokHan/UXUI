package com.conoz.singletour.config;

/**
 * Created by Yongkyu on 2017. 8. 24..
 */

public class Common {
    // main pages - 0 홈, 1 미션, 2 배부른 혼행, 3 즐기는 혼행, 4 안전한 혼행, 5 동행같은 혼행, 6 추천사이트, 7 회원정보 변경/회원 가입, 8 로그아웃/로그인
    public static final int FRAGMENT_MAIN      =   0;
    public static final int FRAGMENT_MISSION   =   1;
    public static final int FRAGMENT_FULL      =   2;
    public static final int FRAGMENT_AMUSED    =   3;
    public static final int FRAGMENT_SAFE      =   4;
    public static final int FRAGMENT_ACCOMPANY =   5;
    public static final int FRAGMENT_RECOMMEND =   6;
    public static final int FRAGMENT_MEMBER    =   7;
    public static final int FRAGMENT_LOGIN     =   8;

    //sub pages
    public static final int FRAGMENT_FULL_MAP          =   9;
    public static final int FRAGMENT_FULL_MAP_NEAR     =   10;
    public static final int FRAGMENT_MISSION_WRITE     =   11;
    public static final int FRAGMENT_MISSION_READ      =   12;
    public static final int FRAGMENT_SAFE_READ         =   13;

    public static String MEMBER_ID  =   null;

    //url
    private static final String URL_PREFIX              =   "http://singletour.conoz.com/rest/";
    public static final String MEMBER_USER_LOGIN_URI    =   URL_PREFIX + "member/";

    public class ResponeMessage {
        public static final String OUTS_OK = "OK";
        public static final String OUTS_DEVICE_ERROR = "DEVICE_ERROR";
    }
}
