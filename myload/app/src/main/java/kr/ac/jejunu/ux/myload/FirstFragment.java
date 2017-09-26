package kr.ac.jejunu.ux.myload;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by dbstj on 2017-09-17.
 */

public class FirstFragment extends Fragment {
    ListView list;
    private String[] web = {"A", "B", "C", "D", "E"};
    private Integer[] imageId = {R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e};

    public static FirstFragment newInstance(Bundle args){
        FirstFragment newFragment = new FirstFragment();
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //Activity setContentsView 메소드와 동일한 기능
        View rootView = inflater.inflate(R.layout.main, container, false);
        CustomList adapter = new CustomList(getActivity(), web, imageId);
        list=(ListView) rootView.findViewById(R.id.list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), "눌렀어요. " + web[+ position], Toast.LENGTH_SHORT).show();
                ((MainActivity)getActivity()).selectItem(0);
            }
        });

        return rootView;
    }
}
