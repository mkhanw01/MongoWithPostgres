package com.example.data.combine.restwebmodel;

/**
 * Created by khan on 10/31/17.
 */
public class ApiPath {

  public static final String BASE_PATH = "/x-combine";
  public static final String API = "/api";

  //mongo api
  public static final String MONGO = API + "/mongo";
  public static final String MONGO_USER = "/mongoUser";
  public static final String FIND_USER_BY_NAME = "/findByName";
  public static final String MIGRATE_MONGO = "/migrateMongo";
  public static final String MONGO_SEND_USER = "/mongoSendUser";

  // postgres api
  public static final String POSTGRES = API + "/postgres";
  public static final String CREATE = "/create";
  public static final String FIND = "/find";
  public static final String MIGRATE_POSTGRES = "/migratePostgres";

  // cache api
  public static final String CACHE = "cache";
  public static final String FLUSH_DB = "/flushDb";

  //
  public static final String VERSION = "/version";

  //
  public static final String INVENTORY ="/inventory";
  public static final String ADD_INVENTORY = "/addInventory";
  public static final String FIND_ALL = "/find-all";
  public static final String FIND_BY_PRODUCT_NAME = "findByProductName";
}
