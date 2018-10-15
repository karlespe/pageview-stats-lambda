package com.karlespe.pageview.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.karlespe.pageview.dao.StatDao;
import com.karlespe.pageview.model.stat.EventsStat;
import com.karlespe.pageview.model.stat.StatRequest;
import com.karlespe.pageview.model.stat.StatResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * AWS Lambda function implementation. Orchestrates request logic to the
 * event logging statistics read DAO.
 */

public class StatHandler implements RequestHandler <StatRequest, StatResponse> {

    private StatDao dao;

    public StatResponse handleRequest(StatRequest statRequest, Context context) {

        final List<EventsStat> stats = new ArrayList<>();

        int statusCode = 200;

        // todo: handle persist failures and subsequent get retry more elegantly
        try {
            stats.addAll(getStatDao().get());
        } catch (Exception e) {
            statusCode = 400;
        }

        return new StatResponse().setStatusCode(statusCode).setStats(stats);

    }

    void setStatDao(StatDao dao) {
        this.dao = dao;
    }

    private StatDao getStatDao() {
        if (dao == null) {
            dao = new StatDao();
        }
        return dao;
    }

}
