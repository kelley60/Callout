package ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import seanmkelley.callout.*;

import static android.app.PendingIntent.getActivity;

/**
 * Created by Sean on 3/5/2015.
 */
public class StudentActivity extends Activity{

    public static final String TAG = StudentActivity.class.getSimpleName();
    private Button mClubListButton;
    private Button mCalendarButton;
    private Button mPersonalDetailsButton;
    private Button mFavoritesButton;
    private boolean previouslyStarted;
    private Button mBackButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_home);
        //mBackButton = (Button) findViewById(R.id.studentHomeBackButtonId);
        previouslyStarted = checkPreviouslyStarted();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (previouslyStarted == false) {
            Log.v(TAG, "Should only be here on first visit");
            Intent userIntent;
            userIntent = new Intent("PersonalDetails");
            startActivity(userIntent);
            //First visit to app as student, go to preferences page
        }

        //otherwise view menu normally
        else {
            mClubListButton = (Button) findViewById(R.id.studentHomeClubListButtonId);
            mCalendarButton = (Button) findViewById(R.id.studentHomeCalendarButtonId);
            mPersonalDetailsButton = (Button) findViewById(R.id.studentHomePersonalDetailsButtonId);
            mFavoritesButton = (Button) findViewById(R.id.studentHomeFavoritesButtonId);

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
                    userIntent = new Intent("calendar");
                    startActivity(userIntent);
                }
            });

            mPersonalDetailsButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userIntent;
                    userIntent = new Intent("PersonalDetails");
                    startActivity(userIntent);
                }
            });

            mFavoritesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent userIntent;
                    userIntent = new Intent("Favorites");
                    startActivity(userIntent);
                }
            });
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
                i = new Intent("calendar");
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

    public boolean checkPreviouslyStarted(){
        Context context = StudentActivity.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String visitedBefore = sp.getString(getString(R.string.pref_previously_started), "FALSE");
        if (visitedBefore.equals("TRUE")){
            //set to pref to true
            Log.v(TAG, "var name is  " + visitedBefore);
            return true;
        }
        else{
            SharedPreferences.Editor ed = sp.edit();
            ed.putString(getString(R.string.pref_previously_started), "TRUE");
            ed.commit();
            Log.v(TAG, "var name is  " + visitedBefore);
            return false;
        }
    }

}
