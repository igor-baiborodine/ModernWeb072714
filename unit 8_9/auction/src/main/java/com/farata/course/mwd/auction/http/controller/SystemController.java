package com.farata.course.mwd.auction.http.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


/**
 * Serves system diagnostic information.
 */
@RestController
public class SystemController {

  /**
   * Helps external tools to monitor health of the service.
   */
  @RequestMapping(method = RequestMethod.GET, value = "/health")
  public String health() {
    return "OK";
  }
}
