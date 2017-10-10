package com.example.juheee.myroad_final;

import android.content.Context;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link FullFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link FullFragment#newInstance} factory method to
 * create an instance of this fragment.
 */


public class FullFragment extends Fragment {
    private static final String TAG = "FullFragment";
    private RelativeLayout mainLayout;
    private CzSharedPreferences pref = null;
    private CzFullListAdapter fullListAdapter;
    private ArrayList<CzFullitem> arrListData;
    private int nPageNo=0;
    private int listCntPerPage=4;
    private boolean bLoadingMore = false;
    private int nCurrentListCount=0;
    private ListView lvFullList;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public FullFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FullFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FullFragment newInstance(String param1, String param2) {
        FullFragment fragment = new FullFragment();
       if(args !=null)
           fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        mView = inflater.inflate(R.layout.fragment_full, container, false);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    protected  void initLayout(){
        if(getActivity()!=null){
            mContext = getActivity();
            initCompleteUI();
            Button btnMap = (Button)mView.findViewById(R.id.btnMap);
            btnMap.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    changeFragement(Common.FRAGMENT_FULL_MAP,null);
                }
            });}
            else{
                return;
            }
        }

        private void initCompleteUI(){
            arrListData = new ArrayList<CzFullitem>();
            nPageNo=0;
            pref=new CzSharedPreferences(mContext);
            mainLayout=(RelativeLayout)mView.findViewById(R.id.rlFull);
            lvFullList=(ListView)mView.findViewById(R.id.lvFullList);
            fullListAdapter=new FullFragment.CzFullListAdapter(this, mContext, R.layout.full_list_item, arrListData);
            lvFullList.setAdapter(fullListAdapter);
            listFull();
        }

    private void listFull() {
        nPageNo++;
        bLoadingMore=true;
        VolleyResponse res = new VolleyResponse(mContext, new VolleyResponse.VolleyListener(){
            @Override
            public void onResponse(ResultInfo pItem){
                bLoadingMore=false;
                if(pItem!=null&&pItem.getFullItems()!=null){
                    nCurrentListCount = pItem.getFullItems().size();
                    for(int i =0; i < pItem.getFullItems().size(); i++) {
                        arrListData.add(pItem.getFullItems().get(i));
                    }
                    drawFullList();
                    }else{
//                    alertMsg("");
                }
            }
            @Override
            public void onResponseError(ResultInfo pItem){
            }
            @Override
            public void onError(){
            }
        });
        Map<String, String> param = new HashMap<String, String>();
        param.put("pageNo", nPageNo+"");
        param.put("listCntPerPage",listCntPerPage+"");
        res.request(API.URL_FULLY_LIST, param);
    }

    private void drawFullList(){
        if(nCurrentListCount > 0) {
            try {
                fullListAdapter.notifyDataSetChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private void goViewFull(final CzFullItem fullItem){
        try{
            if(!fullItem.getFullyNo().equals(null)&&!fullItem.getFullyNo().equals("")){
//                alertMsg(fullItem.getFullyNo());
                Bundle b = new Bundle();
                b.putString("param1",fullItem.getTitle()+"/"+fullItem.getLatitude()+"/"+fullItem.getLongitude());
                changeFragment(Common.FRAGMENT_FULL_MAP, b);
            }
        }catch (NullPointerException e){
            e.printStackTrace();
        }
    }

    private alertMsg(String msg){
        Toast t = Toast.makeText(mContext, msg, Toast.LENGTH_SHORT);
        t.setGravity(Gravity.CENTER_HORIZONTAL|Gravity.CENTER_VERTICAL, 0, 0);
        t.show();
    }

    public class CzFullListAdapter extends ArrayAdapter<CzFullItem>{
        private static final String TAG = "CzFullListAdapter";
        private Context mContext;
        private FullFragment mFull;
        private ArrayList<CzFullItem> arrData;
        private ImageLoader imageLoader;
        public CzFullListAdapter(Context context, int textViewResourceId){
            super(context, textViewResourceId);
        }
        public CzFullListAdapter(FullFragment caller, Context contextm int resourece, ArrayList<CzFullItem> ovjexts){
            super(context, resource, objects);
            mFull=caller;
            arrData=objects;
            imageLoader = new Imageloader(Volley.newRequestQueue(context), new BitmapLruCache());
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent){
            View v = convertView;
            if(v == null){
                LayoutInflater vi;
                vi = LayoutInflater.from(getContext());
                v = vi.inflate(R.layout.full_list_item, null);
            }
            final int idx = position*2;
            if(idx<arrData.size()){
                final CzFullItem folder = arrData.get(idx);
                if (folder != null) {
                    TextView tvTitle = (TextView) v.findViewById(R.id.tvTitle);
                    TextView tvAddressVal = (TextView) v.findViewById(R.id.tvAddressVal);
                    TextView tvTelVal = (TextView) v.findViewById(R.id.tvTelVal);
                    TextView tvBusinessTimeVal = (TextView) v.findViewById(R.id.tvBusinessTimeVal);
                    Button btnItem1 = (Button) v.findViewById(R.id.btnItem1);
                    NetworkImageView imgAvatar = (NetworkImageView) v.findViewById(R.id.ivMainImg);
                    imgAvatar.setImageUrl(folder.getImgPath(), imageLoader);
                    if (tvTitle != null) {
                        tvTitle.setText(folder.getTitle());
                    }
                    if (tvAddressVal != null) {
                        tvAddressVal.setText(folder.getAddress());
                    }
                    if (tvTelVal != null) {
                        tvTelVal.setText(folder.getTelNo());
                    }
                    if (tvBusinessTimeVal != null) {
                        tvBusinessTimeVal.setText(folder.getBusinessHours());
                    }
                    btnItem1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            mFull.goViewFull(folder);
                        }
                    });
                }
                else {
                    return convertView;
                }
                final int idx2=idx+1;
                if(idx2<arrData.size()) {
                    final CzFullItem folder2 = arrData.get(idx2);
                    if (folder2 != null) {
                        TextView tvTitle2 = (TextView) v.findViewById(R.id.tvTitle2);
                        TextView tvAddressVal2 = (TextView) v.findViewById(R.id.tvAddressVal2);
                        TextView tvTelVal2 = (TextView) v.findViewById(R.id.tvTelVal2);
                        TextView tvBusinessTimeVal2 = (TextView) v.findViewById(R.id.tvBusinessTimeVal2);
                        Button btnItem2 = (Button) v.findViewById(R.id.btnItem2);
                        NetworkImageView imgAvatar2 = (NetworkImageView) v.findViewById(R.id.ivMainImg2);
                        imgAvatar2.setImageUrl(folder2.getImgPath(), imageLoader);
                        if (tvTitle2 != null) {
                            tvTitle2.setText(folder2.getTitle());
                        }
                        if (tvAddressVal2 != null) {
                            tvAddressVal2.setText(folder2.getAddress());
                        }
                        if (tvTelVal2 != null) {
                            tvTelVal2.setText(folder2.getTelNo());
                        }
                        if (tvBusinessTimeVal2 != null) {
                            tvBusinessTimeVal2.setText(folder2.getBusinessHours());
                        }
                        btnItem2.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                mFull.goViewFull(folder2);
                            }
                        });
                    }
                }else {
                    RelativeLayout rl2 = (RelativeLayout) v.findViewById(R.id.rlItem2);
                    rl2.setVisibility(View.GONE);
                }
                return v;
                    }
        }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
