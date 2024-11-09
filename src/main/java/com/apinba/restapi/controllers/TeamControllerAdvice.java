package com.apinba.restapi.controllers;

import com.apinba.restapi.exceptions.TeamNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TeamControllerAdvice extends ResponseEntityExceptionHandler {

  @ExceptionHandler(value = TeamNotFoundException.class)
  public ProblemDetail teamNotFound(TeamNotFoundException ex) {
    var problemDetails = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    problemDetails.setTitle("Team Not Found");
    problemDetails.setProperty("teamId", ex.getTeamId());
    return problemDetails;
  }
}
