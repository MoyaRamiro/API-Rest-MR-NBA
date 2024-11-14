package com.apinba.restapi.repositories;

import com.apinba.restapi.models.PlayerModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IPlayerRepository extends JpaRepository<PlayerModel, UUID> {
    @Query("""
            select p from PlayerModel p
            where upper(p.first_name) like upper(concat('%', :name, '%')) or upper(p.last_name) like upper(concat('%', :name, '%'))""")
    Page<PlayerModel> searchByName(String name, Pageable pageable);


}
