package com.example.data.combine.restwebmodel;

/**
 * Created by khan on 10/31/17.
 */
public class ApiPath {

  public static final String BASE_PATH = "/x-combine";
  public static final String API = "/api";

  //mongo api

  public static final String MONGO = API+"/mongo";
  public static final String MONGO_USER = "/mongoUser";
  public static final String FIND_USER_BY_NAME = "/findByName";
  public static final String MIGRATE_MONGO ="/migrateMongo";

  // postgres api

  public static final String POSTGRES = API+"/postgres";
  public static final String CREATE = "/create";
  public static final String FIND = "/find";
  public static final String MIGRATE_POSTGRES = "/migratePostgres";

}
