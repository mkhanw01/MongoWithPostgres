package com.example.data.combine.controller;

import com.example.data.combine.restwebmodel.ApiPath;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by khan on 11/10/17.
 */
@RestController
@Api(value = "Version Controller",
    description = "Version api")
public class VersionController {

  @Value("${maven.project.groupId}")
  private String groupId;
  @Value("${maven.project.artifactId}")
  private String artifactId;
  @Value("${maven.project.version}")
  private String version;
  @Value("${maven.build.time}")
  private String buildTime;

  @GetMapping(value = ApiPath.VERSION,
      produces = MediaType.TEXT_PLAIN_VALUE)
  @ApiOperation("Vesion service ")
  public String getVersion() {
    return String.format("maven.project.groupId=%s\n maven.project.artifactId=%s\n"
            + "maven.project.version=%s\n maven.build.time=%s", groupId, artifactId, version,
        buildTime);
  }
}
