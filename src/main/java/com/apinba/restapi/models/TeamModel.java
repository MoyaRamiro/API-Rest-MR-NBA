package com.apinba.restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "team")
public class TeamModel {
  @Id private UUID id = UUID.randomUUID();
  private String abbreviation;

  private String city;

  private String conference;

  private String division;

  private String full_name;

  private String name;

  @OneToMany(mappedBy = "team", orphanRemoval = true)
  private Set<PlayerModel> players = new LinkedHashSet<>();

  public Set<PlayerModel> getPlayers() {
    return players;
  }

  public void setPlayers(Set<PlayerModel> players) {
    this.players = players;
  }

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
