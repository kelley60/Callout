package seanmkelley.callout;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;


public class PersonalDetails extends Activity {

    private CheckBox mSportsBox;
    private CheckBox mAcademicBox;
    private CheckBox mHobbyBox;
    private CheckBox mOtherBox;
    private Button mBackButton;
    public static final String TAG = PersonalDetails.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_personal_details);

        mSportsBox = (CheckBox) findViewById(R.id.personalDetailsSportsCheckId);
        mAcademicBox = (CheckBox) findViewById(R.id.personalDetailsAcademicCheckId);
        mHobbyBox = (CheckBox) findViewById(R.id.personalDetailsHobbyId);
        mOtherBox = (CheckBox) findViewById(R.id.personalDetailsOtherCheckId);
        mBackButton = (Button) findViewById(R.id.personDetailsBackButtonId);

        /*
        mAcademicBox.setChecked(true);
        mHobbyBox.setChecked(true);
        mOtherBox.setChecked(true);
        mSportsBox.setChecked(true);
        */
        //checks previous state of boxes and sets them
        loadPersonalDetails();

        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mSportsBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isSportsChecked() == true) {
                    //add preferences string
                    addPreference("sports");

                } else {
                    //delete preferences string
                    removePreference("sports");

                }
            }
        });

        mAcademicBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAcademicChecked() == true) {
                    //add preferences string
                    addPreference("academic");
                } else {
                    //delete preferences string
                    removePreference("academic");
                }
            }
        });

        mHobbyBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHobbyChecked() == true) {
                    //add preferences string
                    addPreference("hobby");
                } else {
                    //delete preferences string
                    removePreference("hobby");
                }
            }
        });

        mOtherBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isOtherChecked() == true) {
                    //add preferences string
                    addPreference("other");
                } else {
                    //delete preferences string
                    removePreference("other");
                }
            }
        });

    }

    private void loadPersonalDetails() {
        if (containsPreference("sports") == true) {
            Log.v(TAG, "favorites list has sports");
            mSportsBox.setChecked(true);
        } else {
            mSportsBox.setChecked(false);
        }
        if (containsPreference("academic") == true) {
            mAcademicBox.setChecked(true);
        } else {
            mAcademicBox.setChecked(false);
        }
        if (containsPreference("hobby") == true) {
            mHobbyBox.setChecked(true);
        } else {
            mHobbyBox.setChecked(false);
        }
        if (containsPreference("other") == true) {
            mOtherBox.setChecked(true);
        } else {
            mOtherBox.setChecked(false);
        }
    }

    private boolean isSportsChecked() {
        if (mSportsBox.isChecked())
            return true;
        else
            return false;
    }

    private boolean isAcademicChecked() {
        if (mAcademicBox.isChecked())
            return true;
        else
            return false;
    }

    private boolean isHobbyChecked() {
        if (mHobbyBox.isChecked())
            return true;
        else
            return false;
    }

    private boolean isOtherChecked() {
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

    public void addPreference(String p) {
        Context context = PersonalDetails.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String prefList = sp.getString(getString(R.string.user_preferences), "");
        prefList = prefList.concat(p + '\n');
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(getString(R.string.user_preferences), prefList);
        ed.commit();
    }

    public void removePreference(String p) {
        Context context = PersonalDetails.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String prefList = sp.getString(getString(R.string.user_preferences), "");
        int idIndex = prefList.indexOf(p);
        String preId = prefList.substring(0, idIndex);
        String subId = prefList.substring(idIndex + p.length() + 1);
        prefList = preId.concat(subId);
        SharedPreferences.Editor ed = sp.edit();
        ed.putString(getString(R.string.user_preferences), prefList);
        ed.commit();
    }

    public boolean containsPreference(String p) {
        Context context = PersonalDetails.this;
        SharedPreferences sp = context.getSharedPreferences(getString(R.string.callout_file_key), Context.MODE_PRIVATE);
        String favoritesList = sp.getString(getString(R.string.user_preferences), "");

        if (favoritesList.contains(p)) {
            return true;
        } else {
            return false;
        }
    }
}
