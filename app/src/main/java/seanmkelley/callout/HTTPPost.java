package seanmkelley.callout;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;

public class HTTPPost {
    public static void execute (String url) {
        try {
            post(url, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {
                     e.printStackTrace();
                }

                @Override public void onResponse(Response response) throws IOException {

                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void post(String url, Callback callback) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public static String generateClubQuery(String Name, String Bio, String cat1, String cat2, String cat3, String cat4) {
        StringBuilder q = new StringBuilder();

        q.append("http://web.ics.purdue.edu/~awirth/db_clubs_send.php?");

        q.append("Name=");
        q.append(Name.replace(' ', '+'));

        q.append("&Bio=");
        q.append(Bio.replace(' ', '+'));

        // We can probably assume that there will be at least one category
        if (!cat1.isEmpty()) {
            q.append("&cat1=");
            q.append(cat1.replace(' ', '+'));
        }

        if (!cat2.isEmpty()) {
            q.append("&cat2=");
            q.append(cat2.replace(' ', '+'));
        }

        if (!cat3.isEmpty()) {
            q.append("&cat3=");
            q.append(cat3.replace(' ', '+'));
        }

        if (!cat4.isEmpty()) {
            q.append("&cat4=");
            q.append(cat4.replace(' ', '+'));
        }

        return q.toString();
    }

    public static String generateCalendarQuery (String title, int month, int day, int year, int hour, int minute, String description, String clubName) {
        StringBuilder q = new StringBuilder();

        q.append("http://web.ics.purdue.edu/~awirth/db_cal_send.php?");

        q.append("title=");
        q.append(title.replace(' ', '+'));

        q.append("&month=");
        q.append(month);

        q.append("&day=");
        q.append(day);

        q.append("&year=");
        q.append(year);

        q.append("&hour=");
        q.append(hour);

        q.append("&minute=");
        q.append(minute);

        q.append("&description=");
        q.append(description.replace(' ', '+'));

        q.append("&club=");
        q.append(clubName.replace(' ', '+'));

        return q.toString();
    }
}
