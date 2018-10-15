package com.karlespe.pageview.handler;

import com.amazonaws.services.dynamodbv2.document.UpdateItemOutcome;
import com.karlespe.pageview.dao.EventDao;
import com.karlespe.pageview.model.event.EventRequest;
import com.karlespe.pageview.model.event.EventResponse;
import org.junit.Test;

public class EventHandlerTest {

    private static EventDao getInstance() {
        return getInstance(false);
    }

    private static EventDao getInstance(final boolean failure) {
        return new EventDao() {
            @Override
            public UpdateItemOutcome persist(EventRequest event) {
                if (failure) {
                    throw new RuntimeException("fail");
                }
                return null;
            }
        };
    }

    @Test
    public void testSuccess() throws Exception {
        final EventHandler handler = new EventHandler();
        handler.setEventDao(getInstance());
        final EventResponse response = handler.handleRequest(null, null);
        assert "Success".equals(response.getBody());
    }

    @Test
    public void testFailure() throws Exception {
        final EventHandler handler = new EventHandler();
        handler.setEventDao(getInstance(true));
        final EventResponse response = handler.handleRequest(null, null);
        assert "Failure".equals(response.getBody());
    }

}