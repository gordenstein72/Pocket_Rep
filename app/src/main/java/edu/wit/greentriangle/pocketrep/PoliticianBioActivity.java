package edu.wit.greentriangle.pocketrep;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;

public class PoliticianBioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_politician_bio);


        Bitmap demo, rep, other;


        final Intent f_politician = getIntent();
        String fname = f_politician.getStringExtra("fname");
        String lname = f_politician.getStringExtra("lname");
        String office = f_politician.getStringExtra("office");
        String party = f_politician.getStringExtra("party");
        String state = f_politician.getStringExtra("state");


        String phone = f_politician.getStringExtra("phone");




        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeCall(f_politician.getStringExtra("phone"));
            }
        });


        Politician politician = new Politician(office, fname, lname, party, phone);
        String query = state+lname+fname;
        query = query.toLowerCase();
        politician.setPic(setActPic(query, party));

        // Set image
        ImageView image;
        image = (ImageView) findViewById(R.id.pic_b);
        image.setImageBitmap(politician.getPic());

        // Set user name
        TextView name;
        name = (TextView) findViewById(R.id.name_b);
        name.setText(politician.getFname() + " " + politician.getLname());

        //set party
        TextView party_v;
        party_v = (TextView) findViewById(R.id.party_b);
        party_v.setText(politician.getParty());

        //set office
        TextView office_v;
        office_v = (TextView) findViewById(R.id.office_b);
        office_v.setText(politician.getOffice());

        // Set phone
        TextView phone_v;
        phone_v = (TextView) findViewById(R.id.phone_b);
        phone_v.setText(String.valueOf(politician.getPhone()));

        // Set phone
        WebView bio;
        bio = (WebView) findViewById(R.id.bio);
        bio.loadUrl("http://www.wikipedia.org/wiki/"+fname+"_"+lname);
        bio.setWebViewClient(new WebViewClient());
        bio.getSettings().setLoadWithOverviewMode(true);
        bio.getSettings().setUseWideViewPort(true);
    }

    private void makeCall(String number){

        Log.v("Appo", "click");
        Intent phoneIntent = new Intent(Intent.ACTION_DIAL);
        phoneIntent.setData(Uri.parse("tel:"+number));

        startActivity(phoneIntent);

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
