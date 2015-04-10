package seanmkelley.callout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import ui.ClubSignIn;
import ui.StudentActivity;


public class ClubPage extends Activity {
    private Button joinButton;

    String club_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_club_view);

        joinButton = (Button) findViewById(R.id.joinButton);

        //receives intent from ClubMasterList
        club_name = (String)getIntent().getExtras().get("club_name");

        TextView t = (TextView) findViewById(R.id.clubName);
        t.setText(club_name);

        String club_bio = (String)getIntent().getExtras().get("club_bio").toString();
        t = (TextView) findViewById(R.id.clubBio);
        t.setText(club_bio);

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    modFavorite();
            }
        });
    }

    public void addFavorite()
    {
        Context context = ClubPage.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String favoritesList = sp.getString(getString(R.string.user_favorites), "");
        favoritesList = favoritesList.concat(club_name + '\n');
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(getString(R.string.user_favorites), favoritesList);
        ed.commit();
    }

    public void removeFavorite()
    {
        Context context = ClubPage.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String favoritesList = sp.getString(getString(R.string.user_favorites), "");
        int idIndex = favoritesList.indexOf(club_name);
        String preId = favoritesList.substring(0, idIndex);
        String subId = favoritesList.substring(idIndex + club_name.length() + 1);
        favoritesList = preId.concat(subId);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(getString(R.string.user_favorites), favoritesList);
        ed.commit();
    }

    public void modFavorite()
    {
        //check to see if a club is already favorited. if it is, call removeFavorite. If not, call addFavorite
        Context context = ClubPage.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String favoritesList = sp.getString(getString(R.string.user_favorites), "");
        if(favoritesList.contains(club_name))
        {
            removeFavorite();
        }
        else
        {
            addFavorite();
        }
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
