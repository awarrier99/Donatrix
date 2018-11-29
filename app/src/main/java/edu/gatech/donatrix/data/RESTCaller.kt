package edu.gatech.donatrix.data

import android.util.Log

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper

import java.net.HttpURLConnection
import java.net.URL
import java.util.HashMap

object RESTCaller {
    fun post(urlString: String, body: Map<String, Any>): Map<String, Any> {
        try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "POST"
            conn.setRequestProperty("Content-Type", "application/json")
            conn.setRequestProperty("Accept", "application/json")

            val mapper = ObjectMapper()

            mapper.writeValue(conn.outputStream, body)

            if (conn.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.responseCode)
            }

            return mapper.readValue(conn.inputStream, object : TypeReference<Map<String, Any>>() {

            })
        } catch (e: Exception) {
            return HashMap()
        }

    }

    operator fun get(urlString: String): Map<String, Any> {
        try {
            val url = URL(urlString)
            val conn = url.openConnection() as HttpURLConnection
            conn.requestMethod = "GET"
            conn.setRequestProperty("Accept", "application/json")

            if (conn.responseCode != 200) {
                throw RuntimeException("Failed : HTTP error code : " + conn.responseCode)
            }

            val mapper = ObjectMapper()
            return mapper.readValue(conn.inputStream, object : TypeReference<Map<String, Any>>() {

            })
        } catch (e: Exception) {
            Log.d("Donatrix", "An error occurred while trying to make the API call", e)
            return HashMap()
        }

    }
}
