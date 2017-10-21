package com.example.data.combine.postgres.repository;

import com.example.data.combine.postgres.model.PostgresUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by khan on 10/20/17.
 */
public interface PostgresUserRepository extends JpaRepository<PostgresUser,String>{
  PostgresUser findByName(String name);
}
