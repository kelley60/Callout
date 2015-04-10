package seanmkelley.callout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.RadioButton;


public class PersonalDetails extends Activity {

    private CheckBox mSportsBox;
    private CheckBox mAcademicBox;
    private CheckBox mHobbyBox;
    private CheckBox mOtherBox;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_personal_details);

        mSportsBox = (CheckBox) findViewById(R.id.personalDetailsSportsCheckId);
        mAcademicBox = (CheckBox) findViewById(R.id.personalDetailsAcademicCheckId);
        mHobbyBox = (CheckBox) findViewById(R.id.personalDetailsHobbyId);
        mOtherBox = (CheckBox) findViewById(R.id.personalDetailsOtherCheckId);
    }

    private boolean isSportsChecked(){
        if (mSportsBox.isChecked())
            return true;
        else
            return false;
    }
    private boolean isAcademicChecked(){
        if (mAcademicBox.isChecked())
            return true;
        else
            return false;
    }
    private boolean isHobbyChecked(){
        if (mHobbyBox.isChecked())
            return true;
        else
            return false;
    }
    private boolean isOtherChecked(){
        if (mOtherBox.isChecked())
            return true;
        else
            return false;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_personal_details, menu);
        return false;
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
