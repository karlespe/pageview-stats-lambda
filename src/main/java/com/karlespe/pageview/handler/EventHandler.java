package com.karlespe.pageview.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.karlespe.pageview.dao.EventDao;
import com.karlespe.pageview.model.event.EventRequest;
import com.karlespe.pageview.model.event.EventResponse;

/**
 * AWS Lambda function implementation. Orchestrates request logic to the
 * event logging write DAO.
 */

public class EventHandler implements RequestHandler <EventRequest, EventResponse> {

    private EventDao dao;

    public EventResponse handleRequest(EventRequest eventRequest, Context context) {

        String responseMessage = "Success";

        // todo: handle persist failures and subsequent persist retry more elegantly
        try {
            getEventDao().persist(eventRequest);
        } catch (Exception e) {
            responseMessage = "Failure";
        }

        return new EventResponse().setBody(responseMessage);

    }

    void setEventDao(EventDao dao) {
        this.dao = dao;
    }

    private EventDao getEventDao() {
        if (dao == null) {
            dao = new EventDao();
        }
        return dao;
    }

}
