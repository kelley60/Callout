package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;


import seanmkelley.callout.*;

//AKA homepage

public class MyActivity extends Activity {

    private Button mGoButton;
    private RadioButton mStudentButton;
    private RadioButton mClubButton;
    //loginStudentButtonId
    private boolean isStudent;
    private boolean isClub;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mStudentButton = (RadioButton)findViewById(R.id.loginStudentButtonId);
        mClubButton = (RadioButton)findViewById(R.id.loginClubButtonId);
        mGoButton = (Button)findViewById(R.id.loginButtonId);

        if (mStudentButton.isChecked())
                isStudent = true;
        if (mClubButton.isChecked())
                isClub = true;

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent;
                /*

        Intent intent = new Intent(this, StoryActivity.class);
        intent.putExtra(getString(R.string.key_name), name);
        startActivity(intent);
        */
                if (isStudent){
                    userIntent = new Intent(this,StudentActivity.class);
                    startActivity(userIntent);
                }

                if (isClub){
                    userIntent = new Intent(this,ClubActivity.class);
                    startActivity(userIntent);
                }
            }
        });
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
