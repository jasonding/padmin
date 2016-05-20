package com.hrin.admin.service.pool;

import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jason on 14-12-13.
 */
@Service("threadPoolManager")
public class ThreadPoolManager {

    private static final int init_size = 1;
    private static final int MAX_FIXED_POOL_SIZE = 1;
    private static final int max_fixed_queue_size = 1;
    private static final int max_cached_pool_size = 1;
    private static final int max_cached_queue_size = 1;

    private ExecutorService singleThreadExecutor = null ;//Executors.newSingleThreadExecutor();
    private ExecutorService fixedThreadPoolService = null;//Executors.newFixedThreadPool(MAX_FIXED_POOL_SIZE);

    public ExecutorService  getFixedThreadPool() {
        synchronized (this) {
            if(fixedThreadPoolService == null) {
                synchronized (this) {
                    fixedThreadPoolService = Executors.newFixedThreadPool(MAX_FIXED_POOL_SIZE);
                }
            }
        }
        return fixedThreadPoolService;
    }

}
