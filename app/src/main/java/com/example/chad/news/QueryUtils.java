package com.example.chad.news;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chad on 1/14/2017.
 */

public final class QueryUtils {

    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils(){
    }

    public static List<Article> fetchArticleData(String requestURL){

        URL url = createUrl(requestURL);

        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e){
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }

        List<Article> articles = extractFeatureFromJson(jsonResponse);

        return articles;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the URL ", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the Guardian JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                // Closing the input stream could throw an IOException, which is why
                // the makeHttpRequest(URL url) method signature specifies than an IOException
                // could be thrown.
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Article> extractFeatureFromJson(String articleJson){

        if (TextUtils.isEmpty(articleJson)){
            return null;
        }

        List<Article> articles = new ArrayList<>();

        try {
            JSONObject baseJsonObject = new JSONObject(articleJson);

            JSONObject response = baseJsonObject.getJSONObject("response");

            JSONArray result = response.getJSONArray("results");

            for (int i = 0; i < result.length(); i++) {
                JSONObject currentArticle = result.getJSONObject(i);
                String webTitle = currentArticle.getString("webTitle");
                String sectionName = currentArticle.getString("sectionName");
                String webPubDate = currentArticle.getString("webPublicationDate");
                String webUrl = currentArticle.getString("webUrl");

                }
            } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the news JSON results", e);
        }
        return articles;
    }

}
