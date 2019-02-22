package com.leetcode.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class CacheEvictJob {
    private static final Logger LOGGER = LoggerFactory.getLogger(CacheEvictJob.class);

    @Scheduled(fixedRateString = "${cache.evict.milliseconds}")
    @CacheEvict(
            cacheNames = {"problem"},
            allEntries = true,
            beforeInvocation = true)
    public void evictCache() {
        LOGGER.info("Clear problem cache");
    }
}
