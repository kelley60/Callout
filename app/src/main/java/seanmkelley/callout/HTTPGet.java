package seanmkelley.callout;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import calendar.Calendar;
import calendar.CalendarActivity;

/**
 * Created by William on 4/8/2015
 */
public class HTTPGet {
    public static void getClubList(final List<Club> list, final ClubMasterList clubMasterList) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://web.ics.purdue.edu/~awirth/db_clubs.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    if (response.isSuccessful()) {
                        //access internet
                        JSONObject data = new JSONObject(jsonData);
                        JSONArray array = data.getJSONArray("clubs");

                        for (int i = 0; i < array.length(); i++) {
                            JSONObject jsonChildNode = array.getJSONObject(i);

                            String name = jsonChildNode.optString("Name");
                            String bio = jsonChildNode.optString("Bio");
                            ArrayList<String> Cat = new ArrayList<String>();
                            String cat1 = jsonChildNode.optString("cat1");
                            String cat2 = jsonChildNode.optString("cat2");
                            String cat3 = jsonChildNode.optString("cat3");
                            String cat4 = jsonChildNode.optString("cat4");
                            if(cat1!=null){
                                Cat.add(cat1);
                                if(cat2!=null){
                                    Cat.add(cat2);
                                    if(cat3!=null){
                                        Cat.add(cat3);
                                        if(cat4!=null){
                                            Cat.add(cat4);
                                        }
                                    }
                                }
                            }
                            System.out.println(name + "\nDescription: " + bio);
                            Club thisItem = new Club(name, bio, Cat);
                            list.add(thisItem);
                        }
                    } else {
                        System.out.println("Response unsuccessful");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                // Let the master list know that we have finished downloading
                // and parsing the clubs from the database
                clubMasterList.setMasterClubList();
                clubMasterList.updateClubList();
            }
        });
    }

    /**
     * Retrieve events from the database and enter them into the calendar
     * @param calendar The calendar that the events will be added to
     */
    public static void getCalendarEvents(final Calendar calendar, final CalendarActivity calendarActivity) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://web.ics.purdue.edu/~awirth/db_cal.php")
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Response response) throws IOException {
                try {
                    String jsonData = response.body().string();
                    System.out.println("JSON data: " + jsonData);
                    if (response.isSuccessful()) {
                        JSONArray jsonArray = new JSONObject(jsonData).getJSONArray("cal");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject obj = jsonArray.getJSONObject(i);
                            calendar.addEvent(
                                    obj.optInt("year"),
                                    obj.optInt("month") - 1,
                                    obj.optInt("day"),
                                    obj.optInt("hour"),
                                    obj.optInt("minute"),
                                    obj.optString("title"),
                                    obj.optString("description"));
                        }
                    }
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }

                calendarActivity.updateEventList();
            }
        });
    }
}
