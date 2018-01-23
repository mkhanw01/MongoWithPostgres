package com.example.data.combine.eventmodel;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by khan on 1/19/18.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddUserEventModel implements Serializable {

  private static final long serialVersionUID = 8409182889242251284L;

  private String name;

  private String address;

  private String age;

}
