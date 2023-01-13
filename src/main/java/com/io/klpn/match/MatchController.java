package com.io.klpn.match;

import com.io.klpn.basic.ErrorsListDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchController {

    final MatchService matchService;

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<Match> getAllMatches(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return matchService.getAllMatches(page);
    }

    @GetMapping("/dates")
    public List<LocalDate> getDatesReservedForAdmin() {
        return matchService.getDatesReservedForAdmin();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public Match getMatchById(@PathVariable Long id){
        return matchService.getMatchById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createMatch(@RequestBody Match match) {
        return matchService.createMatch(match);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteMatchById(@PathVariable Long id) {
        return matchService.deleteMatchById(id);
    }

}
