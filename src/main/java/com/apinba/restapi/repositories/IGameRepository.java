package com.apinba.restapi.repositories;

import com.apinba.restapi.models.GameModel;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IGameRepository extends JpaRepository<GameModel, UUID> {
  @Query(
      """
            select g from GameModel g
            where g.visitor_team.id = :id or g.home_team.id = :id""")
  Page<GameModel> findByTeamId(UUID id, Pageable pageable);

  @Query(
      """
            select g from GameModel g
            where upper(g.visitor_team.full_name) like upper(concat('%', :name, '%')) or upper(g.home_team.full_name) like upper(concat('%', :name, '%'))""")
  Page<GameModel> searchByName(String name, Pageable pageable);
}
