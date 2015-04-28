package seanmkelley.callout;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import java.io.IOException;


public class AddClub extends Activity {

    public static final String TAG = AddClub.class.getSimpleName();
    private Button mBackButton;
    private Button mMakeClubButton;
    private CheckBox mSportsBox;
    private CheckBox mAcademicBox;
    private CheckBox mHobbyBox;
    private CheckBox mOtherBox;
    private EditText mClubNameText;
    private EditText mClubBioText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.club_add);
        mBackButton = (Button) findViewById(R.id.clubAddBackButtonId);
        mSportsBox = (CheckBox) findViewById(R.id.addClubSportsButtonId);
        mAcademicBox = (CheckBox) findViewById(R.id.clubAddAcademicButton);
        mHobbyBox = (CheckBox) findViewById(R.id.addClubHobbyButtonId);
        mOtherBox = (CheckBox) findViewById(R.id.addClubOtherButtonId);
        mMakeClubButton = (Button) findViewById(R.id.addClubButtonId);
        mClubNameText = (EditText) findViewById(R.id.addClubNameEditTextId);
        mClubBioText = (EditText) findViewById(R.id.addClubEditTextBioId);

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
        String clubName = mClubNameText.getText().toString();
        String clubBio = mClubBioText.getText().toString();
        String isSport = "";
        if (mSportsBox.isChecked())
            isSport = "Sports";
        String isAcademic = "";
        if (mAcademicBox.isChecked())
            isAcademic = "Academic";
        String isHobby = "";
        if (mHobbyBox.isChecked())
            isHobby = "Hobby";
        String isOther = "";
        if (mOtherBox.isChecked())
            isOther = "Other";

        Log.v(TAG, "" + clubName + " " + clubBio);


        String json = example.sendClub(clubName, clubBio,isSport,isAcademic,isHobby,isOther);
        example.post("http://www.roundsapp.com/post", json);
    }
}
