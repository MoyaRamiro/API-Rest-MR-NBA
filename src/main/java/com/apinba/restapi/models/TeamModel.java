package com.apinba.restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
@Table(name = "team")
public class TeamModel {
  @Id @Column private UUID id = UUID.randomUUID();

  @Column private String abbreviation;
  @Column private String city;
  @Column private String conference;
  @Column private String division;
  @Column private String full_name;
  @Column private String name;

  public String getAbbreviation() {
    return abbreviation;
  }

  public void setAbbreviation(String abbreviation) {
    this.abbreviation = abbreviation;
  }

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public String getConference() {
    return conference;
  }

  public void setConference(String conference) {
    this.conference = conference;
  }

  public String getDivision() {
    return division;
  }

  public void setDivision(String division) {
    this.division = division;
  }

  public String getFull_name() {
    return full_name;
  }

  public void setFull_name(String full_name) {
    this.full_name = full_name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public UUID getId() {
    return id;
  }
}
