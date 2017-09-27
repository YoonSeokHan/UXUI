package com.conoz.singletour.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.conoz.singletour.R;
import com.conoz.singletour.bean.API;
import com.conoz.singletour.bean.ResultInfo;
import com.conoz.singletour.config.Common;
import com.conoz.singletour.config.CzSharedPreferences;
import com.conoz.singletour.network.VolleyResponse;

import java.util.HashMap;
import java.util.Map;

public class LoginFragment extends BaseFragment {
    private static final String TAG = "LoginFragment";
    private RelativeLayout mainLayout;
    private CzSharedPreferences pref = null;

    private EditText etUserEmail;
    private EditText etUserPwd;
    private Button btnLogin;

    public static LoginFragment newInstance(Bundle args) {
        LoginFragment fragment = new LoginFragment();
        if(args !=null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    protected void initLayout() {
        if(getActivity()!=null){
            mContext = getActivity();
            initCompleteUI();
        }else{
            return;
        }
    }

    private void initCompleteUI() {
        pref=new CzSharedPreferences(mContext);
        mainLayout = (RelativeLayout)mView.findViewById(R.id.rlLogin);
        etUserEmail = (EditText)mView.findViewById(R.id.etUserEmail);
        etUserEmail.setText("pandora@conoz.net");
        etUserPwd = (EditText)mView.findViewById(R.id.etUserPwd);
        btnLogin = (Button)mView.findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                goLogin();
            }
        });
    }

    private String strUserEmail;
    private String strUserPwd;
    private void goLogin(){
        try{
            strUserEmail=etUserEmail.getText().toString().trim();
            strUserPwd=etUserPwd.getText().toString().trim();
            if(strUserEmail.length()<=0){
                alertMsg("이메일을 입력하세요.");
                etUserEmail.setFocusable(true);
            }else if(strUserPwd.length()<=0){
                alertMsg("비밀번호를 입력하세요.");
            }else{
                //로그인 처리.
                login();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    private void login(){
        VolleyResponse res = new VolleyResponse(mContext, new VolleyResponse.VolleyListener(){

            @Override
            public void onResponse(ResultInfo pItem) {
                Log.d(TAG, "login response:"+pItem.getUserEmail());
                //키보드 닫기.
                InputMethodManager imm = (InputMethodManager)mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(etUserPwd.getWindowToken(), 0);

                //로그인정보 저장
                pref.savePreferences("memberNo", pItem.getMemberNo());
                pref.savePreferences("userEmail", pItem.getUserEmail());

                etUserEmail.setText("");
                etUserPwd.setText("");

                changeFragement(Common.FRAGMENT_MAIN,null);
            }

            @Override
            public void onResponseError(ResultInfo pItem) {
                alertMsg(pItem.getMessage());
                etUserPwd.setText("");
            }

            @Override
            public void onError() {
                Log.d(TAG, "Volley Error.");
            }
        });
        Map<String, String> param = new HashMap<String, String>();
        param.put(API.RequestVariables.USER_EMAIL, strUserEmail);
        param.put(API.RequestVariables.USER_PWD, strUserPwd);
        res.request(API.URI_MEMBER_LOGIN, param);
    }

    public void alertMsg(String msg){
        Toast t = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
        t.show();
    }
}
