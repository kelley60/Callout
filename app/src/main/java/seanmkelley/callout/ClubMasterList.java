package seanmkelley.callout;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ClubMasterList extends Activity {
    public static final String TAG = ClubMasterList.class.getSimpleName();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_club_master_list);
        //the following was in some example code I found online
        //not quite sure what it does but it's probably important, seems to be error trapping
         /*try {
         Class.forName("org.apache.derby.jdbc.ClientDriver"); //driver? not sure what's supposed to go here
        }
        catch(ClassNotFoundException e) {
         System.out.println("Class not found "+ e);
         }
        // TODO Get String list of club names, descriptions, and photos from database
        clubNames = new ArrayList<String>(20);
        //commenting all of my revisions for now so I don't break anything right before the demo
        //clubBios = new ArrayList<String>(20);
        try {
            Connection con = DriverManager.getConnection("jdbc:mydb.ics.purdue.edu","awirth","alaina007");
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM clubs");
            //System.out.println("id  Name    Bio");

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("Name");
                String bio = rs.getString("Bio");
                //System.out.println(id+"   "+Name+"    "+Bio);
                clubNames.add("Name");
                //clubBios.add("Bio");
            }
        }
        catch(SQLException e){
            System.out.println("SQL exception occurred" + e);
        }*/


        DbMailer db = new DbMailer("http://web.ics.purdue.edu/~awirth/db_clubs.php", getApplicationContext());

        List<Club> clubNames = db.ClubList();
        clubNames = new ArrayList<Club>();

        //Toast.makeText(this, clubNames.get(0).getName(), Toast.LENGTH_LONG).show();
        //for (int i = 0; i < clubNames.size(); i++){

        //}


        // Set to display club master list xml page



        // Setup the list view to display the club names
        /*
        ListView lv = (ListView) findViewById(R.id.masterClubListView);
        lv.setAdapter(new ClubArrayAdapter(this, R.layout.list_item, clubNames));
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapter, View v, int position, long arg3) {
                Intent i = new Intent("ClubPage");
                i.putExtra("club_id", getItemIdAtIndex(position));
                i.putExtra("club_name", getClubName(position));
                startActivity(i);
            }
        });
        */
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_club_master_list, menu);
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



    public int getItemIdAtIndex (int position) {
        // Eventually, when we are just taking parts of the total group of clubs,
        // this will need to actually return the clubs id, but just returns the
        // position for now.
        return position;
    }
}
