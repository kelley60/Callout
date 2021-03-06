package calendar;

/**
 * An object used by the calendar to keep track of the time at which an event occurs,
 * as well as its title and description
 *
 * @author Will Enright
 * @see Calendar.Calendar
 */
public class CalendarEvent {
    private int year, month, day, hour, minute;
    private String title, description,club;

    /**
     * Creates a new event
     *
     * @param year The year that this event will occur on
     * @param month The month that this event will occur on
     * @param day The day that this event will occur on
     * @param hour The hour that this event will occur on
     * @param minute The minute that this event will occur on
     * @param title A title for this event
     * @param description A description for this event
     */
    public CalendarEvent(int year, int month, int day, int hour, int minute, String title, String description, String club) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.hour = hour;
        this.minute = minute;
        this.title = title;
        this.description = description;
        this.club = club;
    }

    /**
     * Determines whether or not this event occurs on a given day
     *
     * @param year  The year to check
     * @param month The month to check, starting with January at 0
     * @param day   The day to check
     * @return      True if event lies on given day, false otherwise
     */
    public boolean isOnDay(int year, int month, int day) {
        return this.year == year && this.month == month && this.day == day;
    }

    /**
     * Called from {@link CalendarArrayAdapter}, used to display the title
     *
     * @return The title of this event
     */
    public String getTitle() {
        return title;
    }

    /**
     * Called from {@link CalendarArrayAdapter}, used to display the description
     *
     * @return The description of this event
     */
    public String getDescription() {
        return description;
    }
}
