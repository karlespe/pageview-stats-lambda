package com.karlespe.pageview.model.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

/**
 * Data transfer object representing the event logging write request. AWS
 * automatically marshals JSON from the request into this class.
 */

public class EventRequest {

    private String url;
    private List<Event> events;

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public String getUrl() {
        return url;
    }

    public EventRequest setUrl(String url) {
        this.url = url;
        return this;
    }

    public List<Event> getEvents() {
        return events;
    }

    public EventRequest setEvents(List<Event> events) {
        this.events = events;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventRequest that = (EventRequest) o;

        if (!url.equals(that.url)) return false;
        return events.equals(that.events);

    }

    @Override
    public int hashCode() {
        int result = url.hashCode();
        result = 31 * result + events.hashCode();
        return result;
    }

}
