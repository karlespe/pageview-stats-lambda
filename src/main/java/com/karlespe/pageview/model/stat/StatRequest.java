package com.karlespe.pageview.model.stat;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * A mostly-empty data transfer object representing the event statistic GET request. AWS
 * automatically marshals JSON from the request into this class.
 */

public class StatRequest {

    public String toString() {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}
