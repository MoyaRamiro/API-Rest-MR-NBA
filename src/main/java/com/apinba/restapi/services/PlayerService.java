package com.apinba.restapi.services;

import com.apinba.restapi.models.PlayerModel;
import com.apinba.restapi.repositories.IPlayerRepository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PlayerService {
  private final IPlayerRepository playerRepository;

  public PlayerService(IPlayerRepository playerRepository) {
    this.playerRepository = playerRepository;
  }

  public Page<PlayerModel> getPlayers(Pageable pageable, String search, UUID teamId) {
    if (teamId != null) {
      return playerRepository.findByTeamId(teamId, pageable);
    }
    if (search != null) {
      return playerRepository.searchByName(search, pageable);
    }
    return playerRepository.findAll(pageable);
  }

  public PlayerModel savePlayer(PlayerModel player) {
    return playerRepository.save(player);
  }

  public List<PlayerModel> savePlayersList(List<PlayerModel> players) {
    return playerRepository.saveAll(players);
  }
}
