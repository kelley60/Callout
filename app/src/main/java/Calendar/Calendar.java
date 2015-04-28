package calendar;

import java.util.ArrayList;

/**
 * This object represents a calendar which is different than that of {@link java.util.Calendar}
 * It is mainly comprised of a list of events.
 * The data for events will be taken from the database once that is set up.
 *
 * @author Will Enright
 * @see CalendarActivity
 */
public class Calendar {
    private java.util.Calendar calendar;
    private ArrayList<CalendarEvent> events;

    /**
     * Instantiate a new calendar with no events
     */
    public Calendar() {
        this.calendar = java.util.Calendar.getInstance();
        this.events = new ArrayList<CalendarEvent>();
    }

    /**
     * Add an event to the calendar
     * This function will most likely get its parameters from a database
     *
     * @param year  The year that this event will occur on
     * @param month The month that this event will occur on.  Note that this is 0 indexed
     * @param day   The day that this event will occur on
     * @param hour  The hour that this event will occur on
     * @param minute The minute that this event will occur on
     * @param title A title for this event
     * @param description A description for this event
     */
    public void addEvent(int year, int month, int day, int hour, int minute, String title, String description,String club) {
        events.add(new CalendarEvent(year, month, day, hour, minute, title, description,club));
    }

    /**
     * @return All of the current events on the calendar
     */
    public ArrayList<CalendarEvent> getEvents() {
        return events;
    }

    /**
     * @return The number of events this calendar has
     */
    public int getNumberOfEvents () {
        return events.size();
    }

    /**
     * This function searches for all events that occur on a given day
     *
     * @param year  The year that is to be searched for events
     * @param month The month that is to be searched for events
     * @param day   The day that is to be searched for events
     * @return      An ArrayList of all of the events on the given day
     */
    public ArrayList<CalendarEvent> getEventsOnDay(int year, int month, int day) {
        ArrayList<CalendarEvent> result = new ArrayList<CalendarEvent>();
        for (CalendarEvent e : events)
            if (e.isOnDay(year, month, day))
                result.add(e);

        return result;
    }
    public void clear()
    {
        events.clear();
    }
}
