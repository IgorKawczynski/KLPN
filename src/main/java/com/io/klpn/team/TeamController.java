package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
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

    @GetMapping("")
    @ResponseStatus(HttpStatus.FOUND)
    public Team getTeamById(@RequestParam("id") Long id) {
        return teamService.getTeamById(id);
    }

    @PostMapping(path = "", produces = "application/json", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createTeam(@RequestBody Team team) {
        return teamService.createTeam(team);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteTransferById(@PathVariable Long id) {
        return teamService.deleteTeamById(id);
    }

}
