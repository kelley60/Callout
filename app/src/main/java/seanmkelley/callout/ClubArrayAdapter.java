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
    private List<Club> clubNames;
    private Context context;

    public ClubArrayAdapter(Context context, int resource, List<Club> clubNames) {
        super(context, resource, clubNames);
        this.clubNames = clubNames;
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

        // Setup text
        TextView title = (TextView) view.findViewById(R.id.title);
        title.setText(clubNames.get(position).getName());

        TextView description = (TextView) view.findViewById(R.id.description);
        description.setText(clubNames.get(position).getBio());

        return view;
    }
}
