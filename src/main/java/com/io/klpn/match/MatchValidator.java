package com.io.klpn.match;

import com.io.klpn.basic.ValidatorService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchValidator {

    final ValidatorService validatorService;

    final MatchRepository matchRepository;

    public Match createMatch(Match match) {
        validatorService.validateIdsEquality(match.getFirstTeamId(), match.getSecondTeamId());
        return new Match (match.getFirstTeamId(), match.getSecondTeamId(), match.getRefereeId(), match.getFirstTeamGoals(), match.getSecondTeamGoals(), match.getReservation());
    }

    public void deleteMatchById(Long id) {
        if (!matchRepository.existsById(id))
            throw new NoSuchElementException(String.format("Match with id: %d does not exist. ", id));
        matchRepository.deleteById(id);
    }
}
