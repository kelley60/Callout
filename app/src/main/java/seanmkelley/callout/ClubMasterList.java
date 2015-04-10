package seanmkelley.callout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ClubMasterList extends Activity {
    private List<Club> masterClubList;
    private List<Club> clubList;
    private ClubArrayAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_master_list);

        clubList = new ArrayList<Club>();

        ListView lv = (ListView) findViewById(R.id.masterClubListView);
        adapter = new ClubArrayAdapter(this, R.layout.list_item, clubList);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent i = new Intent("ClubPage");
                i.putExtra("club_id", getItemIdAtIndex(position));
                i.putExtra("club_name", getClubName(position));
                i.putExtra("club_bio", getClubBio(position));
                startActivity(i);
            }
        });

        HTTPGet.getClubList("http://web.ics.purdue.edu/~awirth/db_clubs.php", clubList, this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_club_master_list, menu);
        return true;
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

    public int getItemIdAtIndex (int position) {
        // Eventually, when we are just taking parts of the total group of clubs,
        // this will need to actually return the clubs id, but just returns the
        // position for now.
        return position;
    }

    private String getClubName(int position) {
        return clubList.get(position).getName();
    }

    private String getClubBio(int position) {
        return clubList.get(position).getBio();
    }

}
