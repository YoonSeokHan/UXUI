package com.conoz.singletour.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.conoz.singletour.R;
import com.conoz.singletour.config.Common;

public class MainFragment extends BaseFragment {
    public static MainFragment newInstance(Bundle args) {
        MainFragment fragment = new MainFragment();
        if(args !=null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_main, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    protected void initLayout() {
        if(getActivity()!=null){
            mContext = getActivity();
        }else{
            return;
        }

        //btnFull btnAmused btnSafe btnAccompany btnMission btnRecommend
        Button btnFull = (Button)mView.findViewById(R.id.btnFull);
        btnFull.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://map.naver.com/index.nhn?menu=route"));
            }
        });
        Button btnAmused = (Button)mView.findViewById(R.id.btnAmused);
        btnAmused.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragement(Common.FRAGMENT_AMUSED, null);
            }
        });
        Button btnSafe = (Button)mView.findViewById(R.id.btnSafe);
        btnSafe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragement(Common.FRAGMENT_SAFE, null);
            }
        });
        Button btnAccompany = (Button)mView.findViewById(R.id.btnAccompany);
        btnAccompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragement(Common.FRAGMENT_ACCOMPANY, null);
            }
        });
       /* Button btnMission = (Button)mView.findViewById(R.id.btnMission);
        btnMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragement(Common.FRAGMENT_MISSION, null);
            }
        });
        Button btnRecommend = (Button)mView.findViewById(R.id.btnRecommend);
        btnRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragement(Common.FRAGMENT_RECOMMEND, null);
            }
        });*/
    }
}
