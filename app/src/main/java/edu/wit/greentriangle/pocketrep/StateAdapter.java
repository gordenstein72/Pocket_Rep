package edu.wit.greentriangle.pocketrep;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class StateAdapter extends ArrayAdapter<StateElement> {

    private LayoutInflater mInflater;

    public StateAdapter(Context context, int rid, List<StateElement> list)
    {
        super(context, rid, list);

        mInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent)
    {
        StateElement item = (StateElement) getItem(position);

        View view = mInflater.inflate(R.layout.state_f, null);

        ImageView image;
        image = (ImageView)view.findViewById(R.id.image);
        image.setImageBitmap(item.image);

        TextView name = (TextView) view.findViewById(R.id.name);
        name.setText(item.name);
/*

        TextView descrip = (TextView) view.findViewById(R.id.descrip);
        descrip.setText(item.descrip);
*/

        return view;
    }
}
