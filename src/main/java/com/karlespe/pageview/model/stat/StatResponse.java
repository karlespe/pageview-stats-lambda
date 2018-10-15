package com.karlespe.pageview.model.stat;

import java.util.List;
import java.util.Map;
import com.google.gson.Gson;

/**
 * Data transfer object representing the event logging statistic GET response. AWS
 * automatically marshals objects of this class to JSON for the API response.
 */

public class StatResponse {

    private int statusCode;
    private Map<String, String> headers;
    private List<EventsStat> stats;

    public String toString() {
        final Gson gson = new Gson();
        return gson.toJson(this);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public List<EventsStat> getStats() {
        return stats;
    }

    public StatResponse setStatusCode(int statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public StatResponse setHeaders(Map<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public StatResponse setStats(List<EventsStat> stats) {
        this.stats = stats;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StatResponse that = (StatResponse) o;

        if (statusCode != that.statusCode) return false;
        if (!headers.equals(that.headers)) return false;
        return stats.equals(that.stats);

    }

    @Override
    public int hashCode() {
        int result = statusCode;
        result = 31 * result + headers.hashCode();
        result = 31 * result + stats.hashCode();
        return result;
    }
}
