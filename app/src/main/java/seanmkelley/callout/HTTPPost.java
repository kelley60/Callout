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

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    String sendClub(String Name, String Bio, String cat1, String cat2, String cat3, String cat4) {
        return  "{" +
                "'Name':'" + Name +
                "'Bio':" + Bio +
                "'cat1:" + cat1 +
                "'cat2:" + cat2 +
                "'cat3:" + cat3 +
                "'cat4:" + cat4 +
                "}";
    }

    public static void main(String[] args) throws IOException {
        HTTPPost example = new HTTPPost();
        String json = example.sendClub("PSP Honors Frat", "Must have a 3.0 GPA to join",
            "Academic","","","");
        String response = example.post("http://www.roundsapp.com/post", json);
        System.out.println(response);
    }
}
