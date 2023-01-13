package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.team.dtos.TeamCreateDTO;
import com.io.klpn.team.dtos.TeamDto;
import com.io.klpn.team.dtos.TeamToAcceptDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/teams")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamController {

    final TeamService teamService;

    @GetMapping("/table")
    @ResponseStatus(HttpStatus.OK)
    public List<Team> generateTable() {
        return teamService.generateTable();
    }

    @GetMapping("/details/{teamId}")
    public TeamDto getTeamDetails(@PathVariable Long teamId){
        return teamService.getTeamDetails(teamId);
    }

    @GetMapping("/to-accept")
    public List<TeamToAcceptDTO> getTeamsToAccept() {
        return teamService.getTeamsToAccept();
    }

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public Team getTeamById(@RequestParam("id") Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping(path = "", produces = "application/json", consumes = "application/json")
    public ErrorsListDTO createTeam(@RequestBody TeamCreateDTO team) {
        return teamService.createTeam(team);
    }

}
