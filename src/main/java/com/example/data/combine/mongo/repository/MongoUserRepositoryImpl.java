package com.example.data.combine.mongo.repository;

import com.example.data.combine.mongo.model.MongoUser;
import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Date;
import java.util.List;

import static com.example.data.combine.mastermodel.FieldsName.COUPON_END_DATE;
import static org.springframework.data.mongodb.core.query.Criteria.where;

/**
 * Created by khan on 12/21/17.
 */
public class MongoUserRepositoryImpl implements CustomMongoUserRepository{
  private static final Logger LOGGER = LoggerFactory.getLogger(MongoUserRepositoryImpl.class);

  @Autowired
  private MongoTemplate mongoTemplate;

  @Override
  public List<MongoUser> findByCouponStartDateAndEndDateAndStoreId(Date currentDate,
      Date endDate) {

    LOGGER.info("#MongoUserRepositoryImpl fetching values based on currentDate : {}, and endDate :{}", currentDate, endDate);
    Query query = new Query(where(COUPON_END_DATE).gte(currentDate).lte(endDate));
  /*  BasicDBObject query = new BasicDBObject();
    query.put(COUPON_END_DATE, BasicDBObjectBuilder.start("$gte",currentDate).add("$lte",endDate).get());*/
    return this.mongoTemplate.find(query,MongoUser.class);

  }
}
