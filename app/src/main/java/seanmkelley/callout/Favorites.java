package seanmkelley.callout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ui.MyActivity;
import ui.StudentActivity;


public class Favorites extends ClubMasterList{

    private ArrayList<Club> masterClubList;
    private ArrayList<Club> clubList;
    private ClubArrayAdapter adapter;
    private String userFavorites;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_favorites);
        clubList = new ArrayList<Club>();
        //Retrieve favorites list, separate into array
        Context context = Favorites.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        userFavorites = sp.getString(getString(R.string.user_favorites), "");

        //do they even have favorites?
        if(userFavorites.equals(""))
        {
            TextView t = (TextView) findViewById(R.id.errorView);
            t.setText("No Favorites Found");
        }
        else {
            ListView lv = (ListView) findViewById(R.id.listView2);
            adapter = new ClubArrayAdapter(this, R.layout.list_item, clubList);
            lv.setAdapter(adapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                    Intent i = new Intent("ClubPage");
                    i.putExtra("club_name", getClubName(position));
                    i.putExtra("club_bio", getClubBio(position));
                    startActivity(i);
                }
            });

            HTTPGet.getClubList(clubList, this);
        }
    }

    public void setMasterClubList() {
        masterClubList = new ArrayList<Club>(clubList);
    }

    public void updateClubList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }
//    public void populate(){
//        DbMailer db = new DbMailer("http://web.ics.purdue.edu/~awirth/db_clubs.php", getApplicationContext());
//        List<Club> clubNames = db.ClubList();
//        /*clubNames = new ArrayList<String>(20);
//        for (int i = 1; i <= 20; i++)
//            clubNames.add("Club " + i);*/
//
//        // Setup the list view to display the club names
//        ListView lv = (ListView) findViewById(R.id.masterClubListView);
//        lv.setAdapter(new ClubArrayAdapter(this, R.layout.list_item, clubNames));
//        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
//                Intent i = new Intent("ClubPage");
//                i.putExtra("club_id", getItemIdAtIndex(position));
//                i.putExtra("club_name", getClubName(position));
//                startActivity(i);
//            }
//        });
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_favorites, menu);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (int i = clubList.size() - 1; i >= 0; i--)
            if (!userFavorites.contains(clubList.get(i).getName()))
                clubList.remove(i);
        updateClubList();
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        Intent i = null;

        switch (item.getItemId()) {
            default:
                i = new Intent(Favorites.this, StudentActivity.class);
                break;
        }
        startActivity(i);
        return false;
    }

    private String getClubName(int position) {
        return clubList.get(position).getName();
    }

    private String getClubBio(int position) {
        return clubList.get(position).getBio();
    }
}
