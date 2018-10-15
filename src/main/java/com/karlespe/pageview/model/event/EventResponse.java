package com.karlespe.pageview.model.event;

import com.google.gson.Gson;

import java.util.Map;

/**
 * Data transfer object representing the event logging write response. AWS
 * automatically marshals objects of this class to JSON for the API response.
 */

public class EventResponse {

    private int statusCode;
    private Map<String, String> headers;
    private String body;

    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public String getBody() {
        return body;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public EventResponse setBody(String body) {
        this.body = body;
        return this;
    }

    public EventResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public EventResponse setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventResponse that = (EventResponse) o;

        if (statusCode != that.statusCode) return false;
        if (!headers.equals(that.headers)) return false;
        return body.equals(that.body);

    }

    @Override
    public int hashCode() {
        int result = statusCode;
        result = 31 * result + headers.hashCode();
        result = 31 * result + body.hashCode();
        return result;
    }
}
