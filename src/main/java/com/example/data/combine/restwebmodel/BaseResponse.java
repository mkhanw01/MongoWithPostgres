package com.example.data.combine.restwebmodel;

import com.example.data.combine.mastermodel.ObjectHelper;
import com.example.data.combine.mastermodel.SubStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModelProperty;
import net.karneim.pojobuilder.GeneratePojoBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.Serializable;

/**
 * Created by khan on 10/31/17.
 */

@GeneratePojoBuilder
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseResponse<T> implements Serializable {

  private static final long serialVersionUID = 391091946332637978L;

  @ApiModelProperty(notes = "Status code of response")
  private Integer code = HttpStatus.OK.value();

  @ApiModelProperty(notes = "Status sub code of response")
  private String subCode = SubStatus.OK.getSubCode();
  @ApiModelProperty(notes = "Message of response")
  private String message = HttpStatus.OK.getReasonPhrase();
  @ApiModelProperty(notes = "Object value of response")
  private T value;

  @ApiModelProperty(notes = "Total pages")
  private int totalPages;

  @ApiModelProperty(notes = "Total elements")
  private long totalElements;

  @Override
  public boolean equals(Object object) {
    return ObjectHelper.equals(this, object);
  }

  @Override
  public int hashCode() {
    return ObjectHelper.hashCode(this);
  }

  public Integer getCode() {
    return code;
  }

  public void setCode(Integer code) {
    this.code = code;
  }

  public String getSubCode() {
    return subCode;
  }

  public void setSubCode(String subCode) {
    this.subCode = subCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public T getValue() {
    return value;
  }

  public void setValue(T value) {
    this.value = value;
  }

  public int getTotalPages() {
    return totalPages;
  }

  public void setTotalPages(int totalPages) {
    this.totalPages = totalPages;
  }

  public long getTotalElements() {
    return totalElements;
  }

  public void setTotalElements(long totalElements) {
    this.totalElements = totalElements;
  }

  /**
   * Convert from VoucherBaseResponse to Spring Response Entity
   *
   * @return Spring Response Entity
   */
  public ResponseEntity<BaseResponse<T>> toResponse() {
    return new ResponseEntity<>(this, HttpStatus.valueOf(this.getCode()));
  }

  @Override
  public String toString() {
    return new ToStringBuilder(this).append("code", code).append("subCode", subCode)
        .append("message", message).append("value", value).append("totalPages", totalPages)
        .append("totalElements", totalElements).toString();
  }
}
