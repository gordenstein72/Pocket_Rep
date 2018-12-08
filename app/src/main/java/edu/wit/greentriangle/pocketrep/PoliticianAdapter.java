package edu.wit.greentriangle.pocketrep;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class PoliticianAdapter extends ArrayAdapter<Politician> {
    private LayoutInflater mInflater;
    public PoliticianAdapter(Context context, int rid, List<Politician> list){
        super(context, rid, list);
        mInflater =
                (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        // Retrieve data
        Politician politician = (Politician)getItem(position);

        // Use layout file to generate View
        View view = mInflater.inflate(R.layout.politician_f, null);

       // Set image
        ImageView image;
        image = (ImageView)view.findViewById(R.id.pic);
        image.setImageBitmap(politician.getPic());

        // Set user name
        TextView name;
        name = (TextView)view.findViewById(R.id.name);
        name.setText(politician.getFname() +" " + politician.getLname());
/*
        //set age
        TextView age;
        age = (TextView)view.findViewById(R.id_.age);
        age.setText("Age: "+Integer.toString(politician.age));*/

        //set party
        TextView party;
        party = (TextView)view.findViewById(R.id.party);
        party.setText(politician.getParty());

        //set office
        TextView office;
        office = (TextView)view.findViewById(R.id.office);
        office.setText(politician.getOffice());

        return view;
    }

}
