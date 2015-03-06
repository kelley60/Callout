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

/**
 * Created by Sean on 3/5/2015.
 */
public class StudentActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_home);
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

            default:
                break;
        }

        startActivity(i);

        return false;
    }

}
