package com.conoz.singletour.network;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.conoz.singletour.bean.API;
import com.conoz.singletour.bean.ResultInfo;
import com.conoz.singletour.bean.ResultResponse;
import com.conoz.singletour.config.Common;
import com.conoz.singletour.config.CzSharedPreferences;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

/**
 * Created by yunhee on 2017. 8. 24..
 */

public class VolleyResponse {
    private String TAG = "VolleyResponse";
    public VolleyListener mVolleyListener;
    public Context mContext;

    public interface VolleyListener {
        public void onResponse(ResultInfo pItem);
        public void onResponseError(ResultInfo pItem);
        public void onError();
    };

    public VolleyResponse(Context pContext, VolleyListener plistener){
        mContext = pContext;
        mVolleyListener = plistener;
    }

    public void request(final String pUri, Map<String, String> pDataParam){
        if(mContext==null)
            return;

        if (!pDataParam.containsKey(API.RequestVariables.DEVICE_ID)) {
            String deviceId = CzSharedPreferences.getInstance(mContext).getPreferences(API.RequestVariables.DEVICE_ID);
            if (deviceId != null)
                pDataParam.put(API.RequestVariables.DEVICE_ID, deviceId);
        }
        if (!pDataParam.containsKey(API.RequestVariables.USER_ID)) {
            String userId = CzSharedPreferences.getInstance(mContext).getPreferences(API.RequestVariables.USER_ID);
            if (userId != null)
                pDataParam.put(API.RequestVariables.USER_ID, userId);
        }
        pDataParam.put(API.RequestVariables.OS, "A");
        if (!pDataParam.containsKey(API.RequestVariables.MEMBER_NO)) {
            String memberNo = CzSharedPreferences.getInstance(mContext).getPreferences(API.RequestVariables.MEMBER_NO);
            if (memberNo != null)
                pDataParam.put(API.RequestVariables.MEMBER_NO, memberNo);//CzCommon.getInstance().getMemberNo()
        }
        Gson gson = new Gson();
        final String  paramStr = gson.toJson(pDataParam);
        JSONObject sendJson = null;
        try {
            sendJson = new JSONObject(paramStr.toString());
            Log.d(TAG, "  pUri : "+ pUri  + ", sendJson : " + sendJson.toString());
        } catch (JSONException e) {
            Log.d(TAG, "  sendJsonException");
            e.printStackTrace();
        }
        if(sendJson != null){
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, pUri, sendJson,createReqSuccessListener()
                    , createReqErrorListener());
            VolleyRequestQueue.getInstance(mContext).getRequestQueue().add(jsonObjectRequest);
        }else{
            Log.d(TAG, " SEND JSON null ");
        }
    }

    private Response.Listener<JSONObject> createReqSuccessListener(){
        return	new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject respons) {
                if(mVolleyListener != null && (respons != null) && (respons.length() > 0 )){
                    try {
                        Gson gson = new Gson();
                        Log.d(TAG, " onResponse ok json result : " + respons.toString());
                        ResultResponse item = gson.fromJson(respons.toString(), new TypeToken<ResultResponse>(){
                        }.getType());
                        if (item.getResult() != null && item.getResult().getOuts().equals(Common.ResponeMessage.OUTS_OK)) {
                            if (item.getResult().getMessage() != null) {
                                Log.d(TAG, " onResponse ok message result : " + item.getResult().getMessage().toString());
                            }
                            mVolleyListener.onResponse(item.getResult());
                        } else {
                            mVolleyListener.onResponseError(item.getResult());
                        }
                    }catch(IllegalStateException e){
                        e.printStackTrace();
                    }catch(JsonSyntaxException e){
                        e.printStackTrace();
                    }
                }
            }
        };
    }

    private Response.ErrorListener createReqErrorListener(){
        return new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                String message = null;
                if (error instanceof TimeoutError || error instanceof NoConnectionError) {
                    message = "network_timeout";
                } else if (error instanceof AuthFailureError) {
                    message = "AuthFailureError";
                } else if (error instanceof ServerError) {
                    message = "ServerError";
                } else if (error instanceof NetworkError) {
                    message = "NetworkError";
                } else if (error instanceof ParseError) {
                    message = "ParseError";
                }
                if(message != null){
                    Log.e(TAG, message);
                }
                if(mVolleyListener != null){
                    mVolleyListener.onError();
                }
            }
        };
    }
}