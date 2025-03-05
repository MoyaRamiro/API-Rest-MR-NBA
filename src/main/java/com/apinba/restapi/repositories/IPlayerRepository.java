package com.apinba.restapi.repositories;

import com.apinba.restapi.models.PlayerModel;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IPlayerRepository extends JpaRepository<PlayerModel, UUID> {
  @Query(
      """
            select p from PlayerModel p
            where upper(p.first_name) like upper(concat('%', :name, '%')) or upper(p.last_name) like upper(concat('%', :name, '%'))""")
  Page<PlayerModel> searchByName(String name, Pageable pageable);

  @Query(
      """
            select p from PlayerModel p
            where p.team.id = :teamId""")
  Page<PlayerModel> findByTeamId(UUID teamId, Pageable pageable);
}
