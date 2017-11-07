package com.example.data.combine.controller.intercepter;

import com.example.data.combine.mastermodel.ErrorCode;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import com.example.data.combine.restwebmodel.MandatoryParameterBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.google.common.base.Preconditions.checkArgument;

/**
 * Created by khan on 10/31/17.
 */
public class MandatoryParameterInterceptor extends HandlerInterceptorAdapter {

  /**
   * HttpServletRequest attribute name
   */
  public static String PARAMETER = "mandatory";

  /**
   * Get mandatory parameter from servlet request
   *
   * @param request servlet request
   * @return mandatory parameter
   */
  protected MandatoryParameter validateAndGetMandatoryParameter(HttpServletRequest request) {
    String CHANNEL_ID = request.getHeader("channelId");
    String CLIENT_ID = request.getHeader("clientId");
    String REQUEST_ID = request.getHeader("requestId");
    String STORE_ID = request.getHeader("storeId");
    String USER_NAME = request.getHeader("username");

    checkArgument(StringUtils.isNotBlank(CHANNEL_ID),
        ErrorCode.CHANNEL_ID_MUST_NOT_BE_BLANK_OR_NULL);
    checkArgument(StringUtils.isNotBlank(CLIENT_ID), ErrorCode.CLIENT_ID_MUST_NOT_BE_BLANK_OR_NULL);
    checkArgument(StringUtils.isNotBlank(REQUEST_ID),
        ErrorCode.REQUEST_ID_MUST_NOT_BE_BLANK_OR_NULL);
    checkArgument(StringUtils.isNotBlank(STORE_ID), ErrorCode.STORE_ID_MUST_NOT_BE_BLANK_OR_NULL);

    return new MandatoryParameterBuilder().withChannelId(CHANNEL_ID).withClientId(CLIENT_ID)
        .withRequestId(REQUEST_ID).withStoreId(STORE_ID).withUsername(USER_NAME).build();
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    MandatoryParameter parameter = this.validateAndGetMandatoryParameter(request);
    request.setAttribute(PARAMETER, parameter);
    return true;
  }
}
