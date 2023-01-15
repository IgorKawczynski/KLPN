package com.io.klpn.match_statistic;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.match_statistic.dtos.MatchStatisticCreateByIndexDTO;
import com.io.klpn.match_statistic.dtos.MatchStatisticCreateDTO;
import com.io.klpn.match_statistic.dtos.MatchStatisticResponseDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match-statistics")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchStatisticController {

    final MatchStatisticService matchStatisticService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.FOUND)
    public MatchStatistic getMatchStatisticById(@PathVariable Long id){
        return matchStatisticService.getMatchStatisticById(id);
    }

    @GetMapping("/list")
    @ResponseStatus(HttpStatus.OK)
    public Page<MatchStatistic> getAllMatchStatistics(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page) {
        return matchStatisticService.getAllMatchStatistics(page);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createMatchStatistic(@RequestBody MatchStatisticCreateDTO dto) {
        return matchStatisticService.createMatchStatistic(dto);
    }

    @PostMapping("/create-by-index")
    @ResponseStatus(HttpStatus.CREATED)
    public ErrorsListDTO createMatchStatisticByIndex(@RequestBody MatchStatisticCreateByIndexDTO dto) {
        return matchStatisticService.createMatchStatisticForStudentIndex(dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public ErrorsListDTO deleteMatchStatisticById(@PathVariable Long id){
        return matchStatisticService.deleteMatchStatisticById(id);
    }

    @GetMapping("/list-for-match/{id}")
    public List<MatchStatisticResponseDTO> getMatchStatisticsForMatchId(@PathVariable Long id) {
        return matchStatisticService.getMatchStatisticsForMatchId(id);
    }
}
