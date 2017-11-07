package com.example.data.combine.controller;

import com.example.data.combine.controller.intercepter.MandatoryParameterInterceptor;
import com.example.data.combine.restwebmodel.BaseResponse;
import com.example.data.combine.restwebmodel.BaseResponseBuilder;
import com.example.data.combine.restwebmodel.MandatoryParameter;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by khan on 11/1/17.
 */
public class BaseController {

  /**
   * Get mandatory parameter from http servlet request
   *
   * @param request servlet request
   * @return mandatory parameter or null
   */
  @ModelAttribute
  public MandatoryParameter getMandatoryParameter(HttpServletRequest request) {
    return (MandatoryParameter) request.getAttribute(MandatoryParameterInterceptor.PARAMETER);
  }


  /**
   * @param data data
   * @param <T> data type
   */
  public <T> BaseResponse<T> toCombineResponse(T data) {
    return new BaseResponseBuilder<T>().withValue(data).build();
  }

}
