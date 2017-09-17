package kr.ac.jejunu.ux.myload;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by dbstj on 2017-09-17.
 */

public class CustomList extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] web; //텍스트
    private final Integer[] imageId; //이미지

    public CustomList(Activity context, String[] web, Integer[] imageId){
        super(context, R.layout.multi_list, web);
        this.context = context;
        this.web = web;
        this.imageId = imageId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.multi_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.txt);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
