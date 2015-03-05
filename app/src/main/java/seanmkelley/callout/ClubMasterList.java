package seanmkelley.callout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ClubMasterList extends Activity {
    //These will be obtained from the database
    private String[] club_names = {
            "Club 1",
            "Club 2",
            "Club 3",
            "Club 4",
            "Club 5",
            "Club 6",
            "Club 7",
            "Club 8",
            "Club 9"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_master_list);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(new ArrayAdapter<String>(this, R.layout.list_item, R.id.text, club_names));
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
}
