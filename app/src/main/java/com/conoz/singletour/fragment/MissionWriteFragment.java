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

public class MissionWriteFragment extends BaseFragment {

    public static MissionWriteFragment newInstance(Bundle args) {
        MissionWriteFragment fragment = new MissionWriteFragment();
        if(args !=null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.blank, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    protected void initLayout() {

    }

}
