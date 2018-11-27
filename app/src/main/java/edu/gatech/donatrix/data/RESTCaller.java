package edu.gatech.donatrix.data;

import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class RESTCaller {
    public static Map<String, Object> post(String urlString, Map<String, Object> body) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Accept", "application/json");

            ObjectMapper mapper = new ObjectMapper();

            mapper.writeValue(conn.getOutputStream(), body);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            return mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            return new HashMap<>();
        }
    }

    public static Map<String, Object> get(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : "
                        + conn.getResponseCode());
            }

            ObjectMapper mapper = new ObjectMapper();
            return mapper.readValue(conn.getInputStream(), new TypeReference<Map<String, Object>>(){});
        } catch (Exception e) {
            Log.d("Donatrix", "An error occurred while trying to make the API call", e);
            return new HashMap<>();
        }
    }
}
