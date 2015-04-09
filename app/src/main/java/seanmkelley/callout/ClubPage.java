package seanmkelley.callout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;


public class ClubPage extends Activity {

    String club_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_club_view);

        //plz don't, i need this
        club_id = (String)getIntent().getExtras().get("club_id").toString();
        String club_name = (String)getIntent().getExtras().get("club_name");

        TextView t = (TextView) findViewById(R.id.clubName);
        t.setText(club_name);

        //t = (TextView) findViewById(R.id.clubBio);
        //t.setText(currentClub.getBio());
    }

    public void addFavorite()
    {
        Context context = ClubPage.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String favoritesList = sp.getString(getString(R.string.user_favorites), "");
        favoritesList = favoritesList.concat(club_id);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(getString(R.string.userid), favoritesList);
        ed.commit();
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
