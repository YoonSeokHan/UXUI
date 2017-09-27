package com.conoz.singletour.fragment;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.conoz.singletour.BaseActivity;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {
    protected View mView;
    protected Context mContext;
    protected abstract void initLayout();

    protected static final String ARG_PARAM1 = "param1";
    protected String mParam1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        initLayout();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }
    }

    protected void changeFragement(int fragmentId, Bundle bundle){
        ((BaseActivity)getActivity()).selectItem(fragmentId, bundle);
    }
}
