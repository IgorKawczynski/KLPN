package com.io.klpn.match_statistic;

import com.io.klpn.basic.ValidatorService;
import com.io.klpn.match.MatchRepository;
import com.io.klpn.student.StudentRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

import static com.io.klpn.basic.ValidatorService.MAX_MINUTE;
import static com.io.klpn.basic.ValidatorService.MIN_MINUTE;

@Component
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchStatisticValidator {

    final ValidatorService validatorService;

    final MatchStatisticRepository matchStatisticRepository;

    final MatchRepository matchRepository;

    final StudentRepository studentRepository;

    public MatchStatistic createMatchStatistic(MatchStatistic matchStatistic) {
        validatorService.validateIntegerBiggerThan(matchStatistic.getMinute(), MIN_MINUTE);
        validatorService.validateIntegerLessThan(matchStatistic.getMinute(), MAX_MINUTE);
//        if (!matchRepository.existsById(matchStatistic.getMatch().getId()))
//            throw new NoSuchElementException(String.format("Match with id: %d does not exist. ", matchStatistic.getMatch().getId()));
        return new MatchStatistic(matchStatistic.getEvent(), matchStatistic.getMinute(), matchStatistic.getStudent(), matchStatistic.getMatch());
    }

    public void deleteMatchStatisticById(Long id){
        if (!matchStatisticRepository.existsById(id))
            throw new NoSuchElementException(String.format("Match statistic with id: %d does not exist. ", id));
        matchStatisticRepository.deleteById(id);
    }

}
