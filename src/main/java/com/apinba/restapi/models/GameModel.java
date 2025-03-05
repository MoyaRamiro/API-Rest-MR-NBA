package com.apinba.restapi.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.util.Objects;
import java.util.UUID;
import org.hibernate.proxy.HibernateProxy;

@Entity
@Table(name = "game")
public class GameModel {
  @Id private UUID id = UUID.randomUUID();
  private String date;
  private int season;
  private String status;
  private int period;
  private String time;
  private boolean postseason;
  private int home_team_score;
  private int visitor_team_score;

  @ManyToOne
  @JoinColumn(name = "visitor_team_id")
  private TeamModel visitor_team;

  @ManyToOne
  @JoinColumn(name = "home_team_id")
  private TeamModel home_team;

  public GameModel() {}

  @Override
  public final boolean equals(Object o) {
    if (this == o) return true;
    if (o == null) return false;
    Class<?> oEffectiveClass =
        o instanceof HibernateProxy
            ? ((HibernateProxy) o).getHibernateLazyInitializer().getPersistentClass()
            : o.getClass();
    Class<?> thisEffectiveClass =
        this instanceof HibernateProxy
            ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass()
            : this.getClass();
    if (thisEffectiveClass != oEffectiveClass) return false;
    GameModel gameModel = (GameModel) o;
    return getId() != null && Objects.equals(getId(), gameModel.getId());
  }

  @Override
  public final int hashCode() {
    return this instanceof HibernateProxy
        ? ((HibernateProxy) this).getHibernateLazyInitializer().getPersistentClass().hashCode()
        : getClass().hashCode();
  }

  public TeamModel getHome_team() {
    return home_team;
  }

  public void setHome_team(TeamModel home_team) {
    this.home_team = home_team;
  }

  public UUID getId() {
    return id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  public int getSeason() {
    return season;
  }

  public void setSeason(int season) {
    this.season = season;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public int getPeriod() {
    return period;
  }

  public void setPeriod(int period) {
    this.period = period;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

  public boolean isPostseason() {
    return postseason;
  }

  public void setPostseason(boolean postseason) {
    this.postseason = postseason;
  }

  public int getHome_team_score() {
    return home_team_score;
  }

  public void setHome_team_score(int home_team_score) {
    this.home_team_score = home_team_score;
  }

  public int getVisitor_team_score() {
    return visitor_team_score;
  }

  public void setVisitor_team_score(int visitor_team_score) {
    this.visitor_team_score = visitor_team_score;
  }

  public TeamModel getVisitor_team() {
    return visitor_team;
  }

  public void setVisitor_team(TeamModel visitor_team) {
    this.visitor_team = visitor_team;
  }
}
