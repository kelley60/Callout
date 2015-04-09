package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import seanmkelley.callout.*;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Sean on 3/5/2015.
 */
public class StudentActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_home);

        //get user data
        Context context = StudentActivity.this;
        SharedPreferences sharedPref = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        //does this user have an id?
        int userid = sharedPref.getInt(getString(R.string.userid), 0);
        if(userid == 0) //does not exist
        {
            //we'll need to assign a new one from a (counter?) in the database, and set up the database to assign the next user's id
            SharedPreferences.Editor ed = sharedPref.edit();
            ed.putInt(getString(R.string.userid), 1);
            ed.commit();
        }
        else //exists
        {
            //Great!
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.student_menu, menu);
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

            case R.id.action_favorites:
                i = new Intent("Favorites");
                break;

            case R.id.action_calendar:
                i = new Intent("Calendar");
                break;

            case R.id.action_personal_details:
                i = new Intent("PersonalDetails");
                break;

            case R.id.action_club_details:
                i = new Intent("ClubDetails");
                break;

            //when the up button is pushed
            default:
                i = new Intent(StudentActivity.this,MyActivity.class);
                break;
        }

        startActivity(i);

        return false;
    }

}
