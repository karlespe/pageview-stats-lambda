package com.karlespe.pageview.dao;

import com.karlespe.pageview.model.event.Event;
import com.karlespe.pageview.model.event.EventType;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public class EventDaoTest {

    private static boolean valuesAreZero(final Map<String, Object> values) {
        final AtomicBoolean zero = new AtomicBoolean(true);
        Arrays.stream(EventType.values()).forEach(type -> {
            if ((Integer)values.get(":" + type.getValueKey()) != 0) {
                zero.set(false);
            }
        });
        return zero.get();
    }

    @Test
    public void testParseEvents_Empty() throws Exception {
        assert valuesAreZero(EventDao.parseEvents(null));
        assert valuesAreZero(EventDao.parseEvents(new ArrayList<>()));
    }

    @Test
    public void testParseEvents() throws Exception {
        final Map<String, Object> values = EventDao.parseEvents(Arrays.asList(
                Event.getInstance(EventType.READ, 1),
                Event.getInstance(EventType.READ, 1),
                Event.getInstance(EventType.DURATION, 9)
        ));
        assert !valuesAreZero(values);
        assert 2 == (Integer)values.get(":" + EventType.READ.getValueKey());
        assert 9 == (Integer)values.get(":" + EventType.DURATION.getValueKey());
        assert 0 == (Integer)values.get(":" + EventType.INFORMATIVE.getValueKey());
    }

}