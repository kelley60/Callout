package calendar;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import seanmkelley.callout.R;

/**
 * This is an adapter which extends {@link android.widget.ArrayAdapter}
 * It is used to display calendar events by printing their title,
 * description, and start times
 *
 * @author Will Enright
 */
public class CalendarArrayAdapter extends ArrayAdapter {
    private List<CalendarEvent> events;
    private Context context;

    public CalendarArrayAdapter(Context context, int resource, List<CalendarEvent> events) {
        super(context, resource, events);
        this.events = events;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Setup inflater and view
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view;
        if (convertView == null)
            view = inflater.inflate(R.layout.list_item, null);
        else
            view = convertView;

        // Setup text view
        TextView text = (TextView) view.findViewById(R.id.text);

        // Assign the text to be the toString of the specified event
        text.setText(events.get(position).toString());

        return view;
    }
}
