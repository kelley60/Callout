package seanmkelley.callout;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.Response;

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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Austin on 3/24/2015.
 */

//create the DbMailer with DbMailer db = new DbMailer(<string with the url you want to access here>, getApplicationContext());
    //List<Club> allClubs = db.ClubList();
    //"http://web.ics.purdue.edu/~awirth/db_clubs.php"
    //"http://web.ics.purdue.edu/~awirth/db_login.php"
    //"http://web.ics.purdue.edu/~awirth/db_cal.php"



public class DbMailer {
    public static final String TAG = DbMailer.class.getSimpleName();

    private String jsonResult;
    private String url;//;"http://web.ics.purdue.edu/~awirth/db_clubs.php";
    private Context con;
    private List<Club> ClubList = new ArrayList<Club>();

    public DbMailer(String s, Context c) {
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
            } catch (ClientProtocolException e) {
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
            } catch (IOException e) {
                // e.printStackTrace();
                Toast.makeText(con, "Error..." + e.toString(), Toast.LENGTH_LONG).show();
            }
            return answer;
        }
    }

    public void accessDatabase() {
        JsonReadTask task = new JsonReadTask();
        task.execute(new String[]{url});
    }


    /*
    public List<Club> ClubList() {

        accessDatabase();
        //List<Map<String, String>> DataList = new ArrayList<Map<String, String>>();
        List<Club> ClubList = new ArrayList<Club>();
        Toast.makeText(con, jsonResult, Toast.LENGTH_LONG).show();
        /*
        try {
            Toast.makeText(con, jsonResult, Toast.LENGTH_LONG).show();
            //JSONObject jsonResponse = new JSONObject(jsonResult);
            //JSONArray jsonMainNode = jsonResponse.optJSONArray("clubs");
            //int length = jsonMainNode.length();
            //Toast.makeText(con, "number of clubs is " + length, Toast.LENGTH_LONG).show();

            for (int i = 0; i < jsonMainNode.length(); i++) {
                JSONObject jsonChildNode = jsonMainNode.getJSONObject(i);
                String name = jsonChildNode.optString("name");
                String bio = jsonChildNode.optString("bio");
                if (i == 0){
                    Toast.makeText(con, name, Toast.LENGTH_LONG).show();
                }
                //String outPut = name + "-" + bio;
                //DataList.add(createEmployee("employees", outPut));
                Club thisItem = new Club(name, bio, null);
                ClubList.add(thisItem);
            }

        } catch (JSONException e) {
            Toast.makeText(con, "Error" + e.toString(),
                    Toast.LENGTH_SHORT).show();
        }

        return ClubList;

    }

    */


    public List<Club> ClubList() {



        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                Toast.makeText(con, "failure", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onResponse(Response response) throws IOException {

                try {
                    String jsonData = response.body().string();


                    Log.v(TAG, jsonData);
                    if (response.isSuccessful()) {
                        //access internet

                        getData(jsonData);


                    } else {
                        Toast.makeText(con, "failure", Toast.LENGTH_LONG).show();
                        //alertUserAboutError();
                    }
                } catch (IOException e) {
                    Log.e(TAG, "Exception caught : ", e);
                }
                catch (JSONException e){
                    Log.e(TAG, "Exception caught : ", e);
                }
            }
        });

           //returns 0 for some reason?
        Log.v(TAG,"size is " + ClubList.size());
        return ClubList;

    }

    private void getData(String jsonData) throws JSONException {
        JSONObject data = new JSONObject(jsonData);
        JSONArray array = data.getJSONArray("clubs");

        for (int i = 0; i < array.length(); i++) {
            JSONObject jsonChildNode = array.getJSONObject(i);

            String name = jsonChildNode.optString("Name");
            String bio = jsonChildNode.optString("Bio");
            Log.v(TAG, name + "\nDescription: " + bio);
            Club thisItem = new Club(name, bio, null);
            ClubList.add(thisItem);
            //returns correct size
            Log.v(TAG,"new size is " + ClubList.size());
        }

    }

}
