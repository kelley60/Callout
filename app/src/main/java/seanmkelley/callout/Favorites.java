package seanmkelley.callout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;


public class Favorites extends Activity {

    private List<String> clubNames;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_favorites);

    }


    public void populate(){
        DbMailer db = new DbMailer("http://web.ics.purdue.edu/~awirth/db_clubs.php", getApplicationContext());
        List<Club> clubNames = db.ClubList();
        /*clubNames = new ArrayList<String>(20);
        for (int i = 1; i <= 20; i++)
            clubNames.add("Club " + i);*/

        // Setup the list view to display the club names
        ListView lv = (ListView) findViewById(R.id.masterClubListView);
        lv.setAdapter(new ClubArrayAdapter(this, R.layout.list_item, clubNames));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent i = new Intent("ClubPage");
                i.putExtra("club_id", getItemIdAtIndex(position));
                i.putExtra("club_name", getClubName(position));
                startActivity(i);
            }
        });
    }

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

    public String getClubName(int index) {
        return clubNames.get(index);
    }

    public int getItemIdAtIndex (int position) {
        // Eventually, when we are just taking parts of the total group of clubs,
        // this will need to actually return the clubs id, but just returns the
        // position for now.
        return position;
    }
}
