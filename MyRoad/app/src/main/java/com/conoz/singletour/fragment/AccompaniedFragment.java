package com.conoz.singletour.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.conoz.singletour.CameraActivity;
import com.conoz.singletour.R;

public class AccompaniedFragment extends BaseFragment {
    public static AccompaniedFragment newInstance(Bundle args) {
        AccompaniedFragment fragment = new AccompaniedFragment();
        if(args !=null)
            fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.honeyjamjam, container, false);
        super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    @Override
    protected void initLayout() {

    }

}