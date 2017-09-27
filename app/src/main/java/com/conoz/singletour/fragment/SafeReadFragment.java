package com.conoz.singletour.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conoz.singletour.R;

public class SafeReadFragment extends BaseFragment {
    public static SafeReadFragment newInstance(Bundle args) {
        SafeReadFragment fragment = new SafeReadFragment();
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