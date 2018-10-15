package com.karlespe.pageview.dao;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.document.*;
import com.karlespe.pageview.model.stat.EventsStat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Data access object for reading page-level statistics from DynamoDB table.
 */

public class StatDao {

    // todo: read values from Lambda configuration properties instead of hard-coding.
    private static String DYNAMODB_TABLE_NAME = "Event";
    private static Regions REGION = Regions.US_EAST_1;

    private DynamoDB dynamoDb;

    private DynamoDB getDynamoDb() {
        if (dynamoDb == null) {
            dynamoDb = DynamoDbFactory.getInstance(REGION);
        }
        return dynamoDb;
    }

    public List<EventsStat> get() {
        final List<EventsStat> stats = new ArrayList<>();
        final Iterator<Item> iterator = getAllItems();
        while (iterator.hasNext()) {
            final Item item = iterator.next();
            stats.add(new EventsStat()
                    .setUrl((String)item.get("Url"))
                    .setRead(((BigDecimal)item.get("Read")).longValue())
                    .setInformative(((BigDecimal)item.get("Informative")).longValue())
                    .setDuration(((BigDecimal)item.get("Duration")).longValue())
            );
        }
        return stats;
    }

    Iterator<Item> getAllItems() {
        final Table table = getDynamoDb().getTable(DYNAMODB_TABLE_NAME);
        final ItemCollection<ScanOutcome> result = table.scan();
        return result.iterator();
    }

}
