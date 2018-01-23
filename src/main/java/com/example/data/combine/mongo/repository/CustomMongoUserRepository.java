package com.example.data.combine.mongo.repository;

import com.example.data.combine.mongo.model.MongoUser;
import org.joda.time.DateTime;


import java.util.Date;
import java.util.List;

/**
 * Created by khan on 12/21/17.
 */
public interface CustomMongoUserRepository {
  List<MongoUser> findByCouponStartDateAndEndDateAndStoreId(Date currentDate, Date
      endDate);
}
