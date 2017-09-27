package com.conoz.singletour.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.conoz.singletour.CzEnjoyItem;
import com.conoz.singletour.R;
import com.conoz.singletour.bean.API;
import com.conoz.singletour.bean.ResultInfo;
import com.conoz.singletour.config.CzSharedPreferences;
import com.conoz.singletour.network.VolleyResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AmusedFragment extends BaseFragment {


    public static AmusedFragment newInstance(Bundle args) {
        AmusedFragment fragment = new AmusedFragment();
        if(args !=null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.hac_deli, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    protected void initLayout() {

    }

}