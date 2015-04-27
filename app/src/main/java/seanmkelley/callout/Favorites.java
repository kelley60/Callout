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
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Favorites extends Activity{

    private ArrayList<Club> masterClubList;
    private ArrayList<Club> favoritesList;
    private ClubArrayAdapter adapter;
    private Button mBackButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_favorites);
        favoritesList = new ArrayList<Club>();

        mBackButton = (Button) findViewById(R.id.favoritesBackButtonId);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        ListView lv = (ListView) findViewById(R.id.listView2);
        adapter = new ClubArrayAdapter(this, R.layout.list_item, favoritesList);
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

        //Retrieve favorites list, separate into array
        Context context = Favorites.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String userFavorites = sp.getString(getString(R.string.user_favorites), "");

        //do they even have favorites?
        if(userFavorites.equals(""))
        {
            TextView t = (TextView) findViewById(R.id.errorView);
            t.setText("No Favorites Found");
        }
       else
        {
            TextView t = (TextView) findViewById(R.id.errorView);
            t.setText(userFavorites);
            /*String[] favoritesNames = new String[userFavorites.length() / 2];
            int c = 0;
            int i = 0;
            while (c < userFavorites.length())
            {
                favoritesNames[i].concat(userFavorites.substring(c, userFavorites.indexOf('\n', c) - 1));
                c += favoritesNames[i].length() + 1;
                i++;
            }*/
        }
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
        return false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private String getClubName(int position) {
        return favoritesList.get(position).getName();
    }

    private String getClubBio(int position) {
        return favoritesList.get(position).getBio();
    }
}
