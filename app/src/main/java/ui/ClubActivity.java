package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import seanmkelley.callout.*;


/**
 * Created by Sean on 3/5/2015.
 */
public class ClubActivity extends Activity{

    private Button mClubListButton;
    private Button mCalendarButton;
    private Button mClubDetailsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_home);

        mClubListButton = (Button) findViewById(R.id.clubHomeClubListButtonId);
        mCalendarButton = (Button) findViewById(R.id.clubHomeCalendarButtonId);
        mClubDetailsButton = (Button) findViewById(R.id.clubHomeClubDetailsButtonId);

        mClubListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent;
                userIntent = new Intent("ClubMasterList");
                startActivity(userIntent);
            }
        });

        mCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent;
                userIntent = new Intent("Calendar");
                startActivity(userIntent);
            }
        });

        mClubDetailsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent;
                userIntent = new Intent(ClubActivity.this,ClubClubView.class);
                startActivity(userIntent);
            }
        });

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
                i = new Intent(ClubActivity.this,ClubClubView.class);
                break;

            /*
            may add later?
            case R.id.action_club_members:
                i = new Intent("Club_Members");
                break;
                */

            default:
                i = new Intent(ClubActivity.this,ClubSignIn.class);
                break;
        }

        startActivity(i);

        return false;
    }

}
