package calendar;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import seanmkelley.callout.R;


public class CalendarActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        //Intent intent = getIntent();

        //Create calendar and import all events from database
        final Calendar calendar = new Calendar();

        // TODO request all events from db then parse and fill the calendar

        // These will be hardcoded for testing purposes
        // Month starts on 0, so April is actually 3.  day/year do not
        calendar.addEvent(2015, 3, 2, 13, 30, "Something Club", "Do stuff");
        calendar.addEvent(2015, 3, 2, 13, 30, "Some other Club", "Do other stuff");
        calendar.addEvent(2015, 3, 3, 13, 30, "Some third Club", "Do more things");

        // Save the context so that we can set list view on item click later
        final Context context = this;

        // TODO Fill events list once for the current day, as it does not call the method below

        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                // Find all of the events on a certain day
                List<CalendarEvent> events = calendar.getEventsOnDay(year, month, day);

                // Setup the list view to display the club names
                ListView lv = (ListView) findViewById(R.id.listViewCalendar);

                // Use the list_item layout to display the events
                lv.setAdapter(new CalendarArrayAdapter(context, R.layout.list_item, events));

                // Choose what to do when an item is clicked on
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                        // TODO go to event page? or maybe just club page
                    }
                });
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_calendar, menu);
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
