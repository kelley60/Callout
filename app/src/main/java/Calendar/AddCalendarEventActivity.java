package calendar;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import seanmkelley.callout.R;


public class AddCalendarEventActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_calendar_event);

        final EditText title = (EditText) findViewById(R.id.TitleField);
        final EditText description = (EditText) findViewById(R.id.DescField);
        final DatePicker datePicker = (DatePicker) findViewById(R.id.datePicker);
        final TimePicker timePicker = (TimePicker) findViewById(R.id.timePicker);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // First, save the date/time/title/description and send it to
                //  an asynchronous function to send to the database
                // TODO add this event to the database
                System.out.println("Adding event:\n" + "Title: " + title.getText().toString() + "\nDescription: " + description.getText().toString());
                System.out.println("This event will occur on: " + datePicker.getDayOfMonth() + "\nAt: " + timePicker.getCurrentMinute());

                // Add it directly to local calendar, or update from database?

                // Finish this intent and return to the previous one (calendar)
                finish();
            }
        });
    }
}
