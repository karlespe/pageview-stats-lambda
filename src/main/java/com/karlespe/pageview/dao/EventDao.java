package com.karlespe.pageview.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.*;
import com.google.common.collect.ImmutableMap;
import com.karlespe.pageview.model.event.Event;
import com.karlespe.pageview.model.event.EventRequest;
import com.karlespe.pageview.model.event.EventType;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Data access object for writing event logging data to DynamoDb. Makes use of
 * `EventType` for writing update statements. Key use of `Table.updateItem` atomic
 * increment API for concurrent updating of page-level statistics.
 */

public class EventDao {

    // todo: read values from Lambda configuration properties instead of hard-coding.
    private static String DYNAMODB_TABLE_NAME = "Event";
    private static Regions REGION = Regions.US_EAST_1;

    private final static Map<String, String> EXPRESSION_ATTRIBUTE_NAMES =
            Arrays.stream(EventType.values())
                    .collect(ImmutableMap.toImmutableMap(EventType::getColumnKey, EventType::getColumnName));
    private final static String SET_STATEMENT = "set " +
            Arrays.stream(EventType.values())
                    .map(EventType::getSetStatement)
                    .collect(Collectors.joining(", "));

    private DynamoDB dynamoDb;

    public UpdateItemOutcome persist(EventRequest event) {

        if (event == null) {
            return null;
        }

        final Table table = getDynamoDb().getTable(DYNAMODB_TABLE_NAME);

        final Map<String, Object> expressionAttributeValues = parseEvents(event.getEvents());

        // todo: try/catch here for update failure and retry
        return table.updateItem(
                "Url", event.getUrl(),
                SET_STATEMENT,
                EXPRESSION_ATTRIBUTE_NAMES,
                expressionAttributeValues);
    }

    private DynamoDB getDynamoDb() {
        if (dynamoDb == null) {
            dynamoDb = DynamoDbFactory.getInstance(REGION);
        }
        return dynamoDb;
    }

    static Map<String, Object> parseEvents(List<Event> events) {
        final Map<String, Object> eventValues = new HashMap<>();
        Arrays.stream(EventType.values()).forEach(type -> eventValues.put(":" + type.getValueKey(), 0));
        if (events != null) {
            events.stream().forEach(event -> {
                if (event.getValue() > 0) {
                    final String key = ":" + event.getType().getValueKey();
                    eventValues.put(key, (Integer)eventValues.get(key) + event.getValue());
                }
            });
        }
        return eventValues;
    }

}
