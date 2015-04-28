package seanmkelley.callout;

import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.Response;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

public class HTTPPost {

    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public void post (String url) {
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

    private void post(String url, Callback callback) throws IOException {
//        RequestBody body = RequestBody.create(JSON, json);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(body)
//                .build();
//        Call call = client.newCall(request);
//        call.enqueue(callback);
//        return call;
        Request request = new Request.Builder()
                .url(url)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);

    }

    public String generateQuery(String Name, String Bio, String cat1, String cat2, String cat3, String cat4) {
        StringBuilder q = new StringBuilder();

        q.append("http://web.ics.purdue.edu/~awirth/db_clubs_send.php?");

        q.append("Name=");
        q.append(Name.replace(' ', '+'));

        q.append("&Bio=");
        q.append(Bio.replace(' ', '+'));

        // We can probably assume that there will be at least one category
        q.append("&cat1=");
        q.append(cat1.replace(' ', '+'));

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

        System.out.println("Sending query: " + q.toString());
        return q.toString();
    }
}
