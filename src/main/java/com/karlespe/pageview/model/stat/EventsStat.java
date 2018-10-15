package com.karlespe.pageview.model.stat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Data transfer object representing a page-level event statistic
 * Encapsulated in `StateRequest` when reading events statistics from the data store.
 */

public class EventsStat {

    private String url;
    private long read;
    private long informative;
    private long duration;

    public static EventsStat getInstance(String url, long read, long informative, long duration) {
        final EventsStat stat = new EventsStat();
        stat.setUrl(url);
        stat.setRead(read);
        stat.setInformative(informative);
        stat.setDuration(duration);
        return stat;
    }

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public String getUrl() {
        return url;
    }

    public EventsStat setUrl(String url) {
        this.url = url;
        return this;
    }

    public long getRead() {
        return read;
    }

    public EventsStat setRead(long read) {
        this.read = read;
        return this;
    }

    public long getInformative() {
        return informative;
    }

    public EventsStat setInformative(long informative) {
        this.informative = informative;
        return this;
    }

    public long getDuration() {
        return duration;
    }

    public EventsStat setDuration(long duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventsStat that = (EventsStat) o;

        if (read != that.read) return false;
        if (informative != that.informative) return false;
        if (duration != that.duration) return false;
        return url.equals(that.url);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + (int) (read ^ (read >>> 32));
        result = 31 * result + (int) (informative ^ (informative >>> 32));
        result = 31 * result + (int) (duration ^ (duration >>> 32));
        return result;
    }
}
