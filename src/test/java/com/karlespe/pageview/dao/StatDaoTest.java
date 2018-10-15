package com.karlespe.pageview.dao;

import com.amazonaws.services.dynamodbv2.document.Item;
import com.karlespe.pageview.model.stat.EventsStat;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class StatDaoTest {

    private Item getInstance(String url, long read, long informative, long duration) {
        final Item item = new Item();
        item.withString("Url", url);
        item.withNumber("Read", read);
        item.withNumber("Informative", informative);
        item.withNumber("Duration", duration);
        return item;
    }

    @Test
    public void testGetNoItems() throws Exception {
        StatDao dao = new StatDao() {
            @Override
            Iterator<Item> getAllItems() {
                return Collections.emptyIterator();
            }
        };
        assert dao.get().isEmpty();
    }

    @Test
    public void testGetOneItem() throws Exception {
        StatDao dao = new StatDao() {
            @Override
            Iterator<Item> getAllItems() {
                return Arrays.asList(getInstance("test", 1, 0, 10)).iterator();
            }
        };
        final List<EventsStat> stats = dao.get();
        assert 1 == stats.size();
        EventsStat stat = stats.get(0);
        assert "test".equals(stat.getUrl());
        assert 1 == stat.getRead();
        assert 0 == stat.getInformative();
        assert 10 == stat.getDuration();
    }

}