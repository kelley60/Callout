package seanmkelley.callout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.IOException;


public class AddClub extends Activity {

    public static final String TAG = AddClub.class.getSimpleName();
    private Button mBackButton;
    private Button mMakeClubButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_add);
        mBackButton = (Button) findViewById(R.id.clubAddBackButtonId);
        mMakeClubButton = (Button) findViewById(R.id.addClubButtonId);

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mMakeClubButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                try {
                    makeClub();
                }
                catch (IOException e) {
                    Log.e(TAG, "Exception caught : ", e);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_club, menu);
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

    public void makeClub() throws IOException {
        HTTPPost example = new HTTPPost();
        String json = example.sendClub("PSP Honors Frat", "Must have a 3.0 GPA to join",
                "Academic","","","");
        String response = example.post("http://www.roundsapp.com/post", json);
    }
}
