package calendar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ListView;

import java.util.List;

import seanmkelley.callout.HTTPGet;
import seanmkelley.callout.R;


public class CalendarActivity extends Activity {
    private CalendarArrayAdapter calendarArrayAdapter;

    private Button mBackButton;
    final Calendar calendar = new Calendar();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_calendar);

        /*mBackButton = (Button) findViewById(R.id.calendarBackButtonId);
        mBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });*/


        //Create calendar and import all events from database

        // Request all events from db then parse and fill the calendar
        HTTPGet.getCalendarEvents(calendar, this);

        // These will be hardcoded for testing purposes
        // Month starts on 0, so April is actually 3.  day/year do not
        //calendar.addEvent(2015, 3, 2, 13, 30, "Something Club", "Do stuff");

        // Save the context so that we can set list view on item click later
        final Context context = this;

        calendarArrayAdapter = new CalendarArrayAdapter(context, R.layout.list_item, null);

        // TODO Fill events list once for the current day, as it does not call the method below

        // Use the list_item layout to display the events
        java.util.Calendar alice = java.util.Calendar.getInstance();
        List<CalendarEvent> events = calendar.getEventsOnDay(alice.get(alice.YEAR), alice.get(alice.MONTH), alice.get(alice.DAY_OF_MONTH));
    System.out.println(""+alice.get(alice.MONTH));
        ListView lv = (ListView) findViewById(R.id.listViewCalendar);
        calendarArrayAdapter = new CalendarArrayAdapter(context, R.layout.list_item, events);
        lv.setAdapter(calendarArrayAdapter);


        CalendarView cv = (CalendarView) findViewById(R.id.calendarView);
        cv.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(CalendarView calendarView, int year, int month, int day) {
                // Find all of the events on a certain day
                List<CalendarEvent> events = calendar.getEventsOnDay(year, month, day);

                // Setup the list view to display the club names
                ListView lv = (ListView) findViewById(R.id.listViewCalendar);

                // Use the list_item layout to display the events
                calendarArrayAdapter = new CalendarArrayAdapter(context, R.layout.list_item, events);
                lv.setAdapter(calendarArrayAdapter);

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
        if (getIntent().hasExtra("fromClub"))
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
            startActivity(new Intent("AddCalendarEvent"));
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * This updates the array adapter, letting it know that the data it displays has changed
     * It will be called from {@link HTTPGet} once it has finished downloading all of the
     *  events from the cal table
     */
    public void updateEventList() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                calendarArrayAdapter.notifyDataSetChanged();
            }
        });
    }
    @Override
    protected void onResume()
    {
        super.onResume();
        HTTPGet.getCalendarEvents(calendar, this);

    }
}
