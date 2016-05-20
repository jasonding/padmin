package com.hrin.admin.task.callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by jason on 14-12-13.
 */
public abstract class AbstractHrinTask<T> implements HrinTask {
    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract T doTask() throws Exception;

    @Override
    public T call() throws Exception {
        T t = null;
        try {
            return doTask();
        }catch (Exception e) {
            logger.error(e.getMessage(),e);
            return t;
        }
    }
}
