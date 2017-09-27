package com.conoz.singletour.config;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by yunhee on 2017. 8. 24..
 */

public class CzSharedPreferences {
    private static Context mContext;
    private static CzSharedPreferences _instance = null;
    public static CzSharedPreferences getInstance(Context context) {
        if (_instance == null) {
            _instance = new CzSharedPreferences(context);
        }
        return _instance;
    }

    public CzSharedPreferences(Context c) {
        mContext = c;
    }
    // 값 불러오기
    public String getPreferences(String name) {
        return getPreferencesString(name);
    }

    public String getPreferencesString(String name) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        try {
            return pref.getString(name, null);
        } catch (Exception e) {
            return name;
        }
    }

    public int getPreferencesInt(String name) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        try {
            return pref.getInt(name, 0);
        } catch (Exception e) {
            return 0;
        }
    }

    public boolean getPreferencesBoolean(String name) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        try {
            return pref.getBoolean(name, false);
        } catch (Exception e) {
            return false;
        }
    }

    public float getPreferencesFloat(String name) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        try {
            return pref.getFloat(name, 0);
        } catch (Exception e) {
            return 0.0f;
        }
    }

    public long getPreferencesLong(String name) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        try {
            return pref.getLong(name, 0L);
        } catch (Exception e) {
            return 0L;
        }
    }

    public String getPreferences(String name, String def) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);

        try {
            return pref.getString(name, def);
        } catch (Exception e) {
            return name;
        }

    }

    // 값 저장하기
    public void savePreferences(String name, String value) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putString(name, value);
        editor.commit();
    }

    // 값 저장하기
    public void savePreferences(String name, int value) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putInt(name, value);
        editor.commit();
    }

    // 값 저장하기
    public void savePreferences(String name, float value) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putFloat(name, value);
        editor.commit();
    }

    // 값 저장하기
    public void savePreferences(String name, boolean value) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putBoolean(name, value);
        editor.commit();
    }

    // 값 저장하기
    public void savePreferences(String name, long value) {
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();

        editor.putLong(name, value);
        editor.commit();
    }

    // 값(Key Data) 삭제하기
    public void removePreferences(String name){
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(name);
        editor.commit();
    }

    public void removeAllPreferences(){
        SharedPreferences pref = mContext.getSharedPreferences("pref", Activity.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
    }
}
