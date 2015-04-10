package ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.CompoundButton;
import android.view.View.OnClickListener;

import seanmkelley.callout.*;

//AKA homepage

public class MyActivity extends Activity {

    private Button mGoButton;
    private RadioButton mStudentButton;
    private RadioButton mClubButton;
    private RadioGroup mRadioGroup;
    //loginStudentButtonId
    private boolean isStudent;
    private boolean isClub;
    private boolean checked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mStudentButton = (RadioButton) findViewById(R.id.loginStudentButtonId);
        mClubButton = (RadioButton) findViewById(R.id.loginClubButtonId);
        mGoButton = (Button) findViewById(R.id.loginButtonId);
        mRadioGroup = (RadioGroup) findViewById(R.id.radioGroupId);

        OnClickListener radioClickListener = new OnClickListener()
        {

            public void onClick(View v)
            {       //The first condition check if we have clicked on an already selected radioButton
                if (v.getId() == mRadioGroup.getCheckedRadioButtonId() && checked)
                {
                    mRadioGroup.clearCheck();
                }
                else
                {
                    checked = true;
                    if (v.getId() == R.id.loginClubButtonId){
                        isClub = true;
                        isStudent = false;
                    }
                    else {
                        isClub = false;
                        isStudent = true;
                    }
                }
            }
        };

        OnCheckedChangeListener radioCheckChangeListener = new OnCheckedChangeListener()
        {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                checked = false;
            }
        };

        mClubButton.setOnCheckedChangeListener(radioCheckChangeListener);
        mStudentButton.setOnCheckedChangeListener(radioCheckChangeListener);

        mClubButton.setOnClickListener(radioClickListener);
        mStudentButton.setOnClickListener(radioClickListener);

        mGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent userIntent;

                if (isStudent){
                userIntent = new Intent(MyActivity.this,StudentActivity.class);
                startActivity(userIntent);
            }

            if (isClub){
                userIntent = new Intent(MyActivity.this,ClubSignIn.class);
                startActivity(userIntent);
            }
            }
        });
    }




}
