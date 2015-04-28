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

    public void post (String url, String json) {
        try {
            post(url, json, new Callback() {
                @Override
                public void onFailure(Request request, IOException e) {

                }

                @Override public void onResponse(Response response) throws IOException {
                    if (response.isSuccessful()) {
                        // This response doesn't really hold much useful information,
                        //  just whether or not it was successful
                        String responseStr = response.body().string();
                    } else {
                        // Request not successful
                    }
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Call post(String url, String json, Callback callback) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(callback);
        return call;
    }

    public String sendClub(String Name, String Bio, String cat1, String cat2, String cat3, String cat4) {
        /*return  "{" +
                "Name':'" + Name +
                "'Bio':" + Bio +
                "'cat1:" + cat1 +
                "'cat2:" + cat2 +
                "'cat3:" + cat3 +
                "'cat4:" + cat4 +
                "}"; */
        return  "{" +
                "\"Name\": \"" + Name + "\"," +
                "\"Bio\": \"" + Bio + "\"," +
                "\"cat1\": \"" + cat1 + "\"," +
                "\"cat2\": \"" + cat2 + "\"," +
                "\"cat3\": \"" + cat3 + "\"," +
                "\"cat4\": \"" + cat4 + "\"," +
                "}";
    }
}
