package edu.gatech.donatrix.data;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Makes API calls
 */
public class RESTCaller {
    private static final int TARGET = 200;

    /**
     * Post to the api
     *
     * @param urlString database url
     * @param body information to post
     * @return response
     */
    public static Map<String, Object> post(String urlString, Map<String, Object> body) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(conn.getOutputStream(), body);

            if (conn.getResponseCode() != TARGET) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            return mapper.readValue(conn.getInputStream(),
                    new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    /**
     * Makes a get request
     *
     * @param urlString database url
     *
     * @return response
     */
    public static Map<String, Object> get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != TARGET) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(conn.getInputStream(),
                    new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            Log.d("Donatrix", "An error occurred while trying to make the API call", e);
            return new HashMap<>();
        }
    }
}
