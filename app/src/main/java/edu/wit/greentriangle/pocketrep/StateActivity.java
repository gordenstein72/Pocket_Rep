package edu.wit.greentriangle.pocketrep;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.List;

public class StateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        Log.v("Appo", "here");

        Bitmap stateImage;
        String states[] = {"ak","al","ar","az","ca","co","ct","de","fl","ga","hi","ia","id_","il",
                "in","ks","ky","la","ma","md","me","mi","mn","mo","ms","mt","nc","nd",
                "ne","nh","nj","nm","nv","ny","oh","ok","or","pa","ri","sc","sd","tn",
                "tx","ut","va","vt","wa","wi","wv","wy"};

        String stateNames[] = {"Alaska","Alabama","Arkansas","Arizona","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Iowa","Idaho","Illinois",
                "Indiana","Kansas","Kentucky","Louisiana","Massachusetts","Maryland","Maine","Michigan","Minnesota","Missouri","Mississippi","Montana","North Carolia","North Dakota",
                "Nebraska","New Hampshire","New Jersey","New Mexico","Nevada","New York","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee",
                "Texas","Utah","Virginia","Vermont","Washington","Wisconsin","West Virginia","Wyoming"};



        final List<StateElement> list = new ArrayList<StateElement>();



        Log.v("Appo", "before population");

        //for loop to populate grid view
        for(int i = 0; i < 50; i++){
            StateElement ele = new StateElement();
            int imid = getResources().getIdentifier(states[i],"drawable",getPackageName());
            stateImage = BitmapFactory.decodeResource(getResources(), imid);
            ele.image = stateImage;
            ele.name = stateNames[i];
            ele.descrip = states[i];
            list.add(ele);
        }

        Log.v("Appo", String.valueOf(list.get(0)));

        StateAdapter adapt;
        adapt = new StateAdapter(this, 0, list);
        GridView view = (GridView)findViewById(R.id.GridView01);
        view.setAdapter(adapt);


        view.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapter, View view, int position, long id){
                Intent intent = new Intent(StateActivity.this,  PoliticianListActivity.class);
                String state_name = list.get(position).getName();
                Bundle b = new Bundle();
                b.putString("State", state_name);
                intent.putExtras(b);
                startActivity(intent);
            }
        });
    }
}
