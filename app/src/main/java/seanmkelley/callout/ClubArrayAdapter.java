package seanmkelley.callout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

/**
 * Created by William on 3/5/2015.
 *
 * Adapter to be used in the club list
 */
public class ClubArrayAdapter extends ArrayAdapter {
    private List<String> clubNames;
    private Context context;

    public ClubArrayAdapter(Context context, int resource, List<String> clubNames) {
        super(context, resource, clubNames);
        this.clubNames = clubNames;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Setup inflater and view
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = null == convertView ? inflater.inflate(R.layout.list_item, null) : convertView;

        // Setup text
        TextView text = (TextView) view.findViewById(R.id.text);
        text.setText(clubNames.get(position));

        return view;
    }
}
