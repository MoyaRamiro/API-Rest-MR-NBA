package com.apinba.restapi.controllers;

import com.apinba.restapi.controllers.model.PlayerWithTeamDto;
import com.apinba.restapi.models.PlayerModel;
import com.apinba.restapi.services.PlayerService;
import java.util.List;
import java.util.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/players")
public class PlayerController {
  private final PlayerService playerService;

  public PlayerController(PlayerService playerService) {
    this.playerService = playerService;
  }

  @GetMapping
  public Page<PlayerWithTeamDto> getPlayers(
      Pageable pageable,
      @RequestParam(required = false) String search,
      @RequestParam(required = false) UUID teamId) {
    return playerService.getPlayers(pageable, search, teamId).map(PlayerWithTeamDto::from);
  }

  @PostMapping
  public PlayerWithTeamDto savePlayer(@RequestBody PlayerModel player) {
    var newPlayer = playerService.savePlayer(player);
    return PlayerWithTeamDto.from(newPlayer);
  }

  @PostMapping("/batch")
  public List<PlayerWithTeamDto> savePlayersList(@RequestBody List<PlayerModel> players) {
    return playerService.savePlayersList(players).stream().map(PlayerWithTeamDto::from).toList();
  }

  /*@PostMapping
  public List<PlayerWithTeamDto> savePlayerorPlayers(@RequestBody(required = false) PlayerModel player, @RequestBody(required = false) List<PlayerModel> players) {
      if(player != null){
          var newPlayer = playerService.savePlayer(player);
          return List.of(PlayerWithTeamDto.from(newPlayer));
      } else if(players != null){
          return playerService.savePlayersList(players).stream().map(PlayerWithTeamDto::from).toList();
      }
      throw new IllegalArgumentException("Invalid request body");
  }*/

  /* @GetMapping("/search")
  public Page<PlayerWithTeamDto> searchPlayers(Pageable pageable, @RequestParam(required = false) String search) {
      return playerService.searchPlayers(pageable, search).map(PlayerWithTeamDto::from);
  }*/
}
