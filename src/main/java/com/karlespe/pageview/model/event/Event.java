package com.karlespe.pageview.model.event;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Data transfer object representing an event type (READ, INFORMATIVE, DWELL-TIME (DURATION)
 * and a numeric value for the type. Encapsulated in `EventRequest` when writing
 * events to the data store.
 */

public class Event {

    private EventType type;
    private int value;

    public static Event getInstance(EventType type, int value) {
        final Event event = new Event();
        event.setType(type);
        event.setValue(value);
        return event;
    }

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

    public EventType getType() {
        return type;
    }

    public Event setType(EventType type) {
        this.type = type;
        return this;
    }

    public int getValue() {
        return value;
    }

    public Event setValue(int value) {
        this.value = value;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (value != event.value) return false;
        return type == event.type;

    }

    @Override
    public int hashCode() {
        int result = type.hashCode();
        result = 31 * result + value;
        return result;
    }

}


