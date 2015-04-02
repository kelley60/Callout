package calendar;

import java.util.ArrayList;

/**
 * This object represents a calendar which is different than that of {@link java.util.Calendar}
 * It is mainly comprised of a list of events.
 * The data for events will be taken from the database once that is set up.
 *
 * @author Will Enright
 * @see calendar.CalendarActivity
 */
public class Calendar {
    private java.util.Calendar calendar;
    private ArrayList<CalendarEvent> events;

    public Calendar() {
        this.calendar = java.util.Calendar.getInstance();
        this.events = new ArrayList<CalendarEvent>();
    }

    public void addEvent(int year, int month, int day, int hour, int minute, String title, String description) {
        events.add(new CalendarEvent(year, month, day, hour, minute, title, description));
    }

    public ArrayList<CalendarEvent> getEvents() {
        return events;
    }

    /**
     * This function searches for all events that occur on a given day
     *
     * @param year  The year that is to be searched for events
     * @param month The month that is to be searched for events, starting with January at 0
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
}
