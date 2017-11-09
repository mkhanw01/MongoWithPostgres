package com.example.data.combine.controller.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.Cache;

/**
 * Created by khan on 11/9/17.
 */
public class CacheErrorHandler implements org.springframework.cache.interceptor.CacheErrorHandler {

  private static final Logger log =
      LoggerFactory.getLogger(org.springframework.cache.interceptor.CacheErrorHandler.class);

  @Override
  public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
    log.error("\n#error CacheErrorHandler-handleCacheGetError\n" + "errorMesssage = {}\n"
        + "exception= = {}", e.getMessage(), e);
  }

  @Override
  public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
    log.error("\n#error CacheErrorHandler-handleCachePutError\n" + "errorMessage = {}\n"
        + "exception = {}", e.getMessage(), e);
  }

  @Override
  public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
    log.error("\n#error CacheErrorHandler-handleCacheEvictError\n" + "errorMessage = {}\n"
        + "exception = {}", e.getMessage(), e);
  }

  @Override
  public void handleCacheClearError(RuntimeException e, Cache cache) {
    log.error("\n#error CacheErrorHandler-handleCacherClearError\n" + "errorMessage = {}\n"
        + "exception = {}", e.getMessage(), e);
  }
}
