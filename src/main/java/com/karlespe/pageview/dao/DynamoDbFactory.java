package com.karlespe.pageview.dao;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;

/**
 * Utility class for creating DynamocDB client instances.
 */

public enum DynamoDbFactory {

    INSTANCE;

    public static DynamoDB getInstance(Regions region) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient();
        client.setRegion(Region.getRegion(region));
        return new DynamoDB(client);
    }

}
