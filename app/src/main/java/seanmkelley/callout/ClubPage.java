package seanmkelley.callout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ClubPage extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_club_view);

        // I get a warning saying this is redundant, but without it, the app crashes (cannot cast Integer to String)
        String club_id = (String)getIntent().getExtras().get("club_id").toString();
        String club_name = (String)getIntent().getExtras().get("club_name");

        TextView t = (TextView) findViewById(R.id.clubName);
        t.setText(club_name);

        //t = (TextView) findViewById(R.id.clubBio);
        //t.setText(currentClub.getBio());
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_club_page, menu);
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
