package seanmkelley.callout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.Toast;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Austin on 3/24/2015.
 */
public class DbMailer {

    private String jsonResult;
    private String url;//;"http://web.ics.purdue.edu/~awirth/db_clubs.php";
    private Context con;
    public DbMailer(String s, Context c)
    {
        url = s;
        con = c;
    }
    private class JsonReadTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            HttpClient httpclient = new DefaultHttpClient();
            HttpPost httppost = new HttpPost(params[0]);
            try {
                HttpResponse response = httpclient.execute(httppost);
                jsonResult = inputStreamToString(response.getEntity().getContent()).toString();
            }

            catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        private StringBuilder inputStreamToString(InputStream is) {
            String rLine = "";
            StringBuilder answer = new StringBuilder();
            BufferedReader rd = new BufferedReader(new InputStreamReader(is));

            try {
                while ((rLine = rd.readLine()) != null) {
                    answer.append(rLine);
                }
            }

            catch (IOException e) {
                // e.printStackTrace();
                Toast.makeText(con, "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }
    }

    public void accessDatabase()
    {
        JsonReadTask task = new JsonReadTask();
        task.execute(new String[] { url });
    }

}
    //FeedReaderDbHelper mDbHelper = new FeedReaderDbHelper(getContext());
/*
    return output;
    }

    //probably gonna want to delete this.
    //will be useful for debugging thought 
    protected void onPostExecute()
    {
        //showNotification("Database interaction complete.");
    }
}

/*
public void write()
{
    // Gets the data repository in write mode
    SQLiteDatabase db = mDbHelper.getWritableDatabase();

// Create a new map of values, where column names are the keys
    ContentValues values = new ContentValues();
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_ENTRY_ID, id);
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_TITLE, title);
    values.put(FeedReaderContract.FeedEntry.COLUMN_NAME_CONTENT, content);

// Insert the new row, returning the primary key value of the new row
    long newRowId;
    newRowId = db.insert(
            FeedReaderContract.FeedEntry.TABLE_NAME,
            FeedReaderContract.FeedEntry.COLUMN_NAME_NULLABLE,
            values);
}

public void read()
{

    SQLiteDatabase db = mDbHelper.getReadableDatabase();

// Define a projection that specifies which columns from the database
// you will actually use after this query.
    String[] projection = {
            FeedEntry._ID,
            FeedEntry.COLUMN_NAME_TITLE,
            FeedEntry.COLUMN_NAME_UPDATED,

    };

// How you want the results sorted in the resulting Cursor
    String sortOrder =
            FeedEntry.COLUMN_NAME_UPDATED + " DESC";

    Cursor c = db.query(
            FeedEntry.TABLE_NAME,  // The table to query
            projection,                               // The columns to return
            selection,                                // The columns for the WHERE clause
            selectionArgs,                            // The values for the WHERE clause
            null,                                     // don't group the rows
            null,                                     // don't filter by row groups
            sortOrder                                 // The sort order
    );
}*/