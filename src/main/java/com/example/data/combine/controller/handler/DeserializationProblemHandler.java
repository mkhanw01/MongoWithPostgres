package com.example.data.combine.controller.handler;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by khan on 10/31/17.
 */

public class DeserializationProblemHandler extends com.fasterxml.jackson.databind.deser.DeserializationProblemHandler{

  private static final Logger log = LoggerFactory.getLogger(DeserializationProblemHandler.class);

  @Override
  public boolean handleUnknownProperty(DeserializationContext ctxt, JsonParser p,
      JsonDeserializer<?> deserializer, Object beanOrClass, String propertyName)
      throws IOException {
    DeserializationProblemHandler.log.warn("unknown field : {}", propertyName);
    return true;
  }
}
