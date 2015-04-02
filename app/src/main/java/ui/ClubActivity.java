package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import seanmkelley.callout.*;


/**
 * Created by Sean on 3/5/2015.
 */
public class ClubActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.club_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        Intent i = null;

        switch (item.getItemId()) {
            case R.id.action_master_club_list:
                i = new Intent("ClubMasterList");
                break;

            case R.id.action_calendar:
                i = new Intent("Calendar");
                break;

            case R.id.action_club_details:
                i = new Intent("ClubDetails");
                break;

            case R.id.action_club_members:
                i = new Intent("Club_Members");
                break;

            default:
                break;
        }

        startActivity(i);

        return false;
    }

}
