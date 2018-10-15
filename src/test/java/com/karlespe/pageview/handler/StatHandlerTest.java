package com.karlespe.pageview.handler;

import com.karlespe.pageview.dao.StatDao;
import com.karlespe.pageview.model.stat.EventsStat;
import com.karlespe.pageview.model.stat.StatResponse;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class StatHandlerTest {

    private static StatDao getInstance() {
        return getInstance(false);
    }

    private static StatDao getInstance(final boolean failure) {
        return new StatDao() {
            @Override
            public List<EventsStat> get() {
                if (failure) {
                    throw new RuntimeException("fail");
                }
                return new ArrayList<>();
            }
        };
    }

    @Test
    public void testSuccess() throws Exception {
        final StatHandler handler = new StatHandler();
        handler.setStatDao(getInstance());
        final StatResponse response = handler.handleRequest(null, null);
        assert 200 == response.getStatusCode();
    }

    @Test
    public void testFailure() throws Exception {
        final StatHandler handler = new StatHandler();
        handler.setStatDao(getInstance(true));
        final StatResponse response = handler.handleRequest(null, null);
        assert 400 == response.getStatusCode();
    }

}