package edu.wit.greentriangle.pocketrep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.io.InputStream;
import java.util.List;
import java.util.Scanner;

public class PoliticianListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_list);
        Bitmap demo, rep, other;
        Intent f_state = getIntent();


        final String state_id = f_state.getStringExtra("State");
        int res_id = getResources().getIdentifier(state_id,"raw", getPackageName());

        InputStream is = getResources().openRawResource(res_id);
        Scanner state_in = new Scanner(is);

        //List of Politicians
        final List<Politician> list = Politician.queryFile(state_in);

        demo = BitmapFactory.decodeResource(getResources(), R.drawable.demo);
        rep = BitmapFactory.decodeResource(getResources(), R.drawable.rep);
        other = BitmapFactory.decodeResource(getResources(), R.drawable.flag);

        for(int i =0; i < list.size(); i++){
            String party = list.get(i).getParty();
            String query = state_id+list.get(i).getLname()+list.get(i).getFname();

            list.get(i).setPic(setActPic(query.toLowerCase(), party));
        }

        Log.v("Appo", String.valueOf(list.get(0)));

        PoliticianAdapter adapter;
        adapter = new PoliticianAdapter(this,0,list);

        //getResources().getIdentifier("ak.txt", "raw", getPackageName());

        //Assign Politician to ListView
        ListView listView = (ListView)findViewById(R.id.PoliticianList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id){

                Intent intent_1 = new Intent(PoliticianListActivity.this,  PoliticianBioActivity.class);

                Bundle b = new Bundle();

                b.putString("fname", list.get(position).getFname() );
                b.putString("lname",list.get(position).getLname());
                b.putString("party", list.get(position).getParty());
                b.putString("office", list.get(position).getOffice());
                b.putString("phone", list.get(position).getPhone());
                b.putString("state", state_id);

                intent_1.putExtras(b);

                startActivity(intent_1);
            }
        });

    }

    public Bitmap setActPic(String q, String party){
        int res_id = getResources().getIdentifier(q,"drawable", getPackageName());

        if(res_id != 0){
            return BitmapFactory.decodeResource(getResources(), res_id);

        }else {

            if (party.compareTo("Democratic") == 0) {
                return BitmapFactory.decodeResource(getResources(), R.drawable.demo);
            } else if (party.compareTo("Republican") == 0) {
                return BitmapFactory.decodeResource(getResources(), R.drawable.rep);
            } else {
                return BitmapFactory.decodeResource(getResources(), R.drawable.flag);
            }
        }
    }

}
