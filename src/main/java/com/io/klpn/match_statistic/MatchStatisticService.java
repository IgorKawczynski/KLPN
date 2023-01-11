package com.io.klpn.match_statistic;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
import com.io.klpn.match_statistic.dtos.MatchStatisticCreateDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchStatisticService {

    final MatchStatisticRepository matchStatisticRepository;

    final MatchStatisticValidator matchStatisticValidator;
    public ErrorsListDTO createMatchStatistic(MatchStatisticCreateDTO dto){
        var errorsList = new ErrorsListDTO();

        try {
            var matchStatistic = matchStatisticValidator.createMatchStatistic(dto);
            matchStatisticRepository.save(matchStatistic);
        }
        catch (IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public MatchStatistic getMatchStatisticById(Long id){
        return matchStatisticRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format(
                        "Statystyka meczowa z podanym id id: %d nie istnieje. ", id))
                );
    }

    public Page<MatchStatistic> getAllMatchStatistics(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return matchStatisticRepository.findAll(paginated);
    }

    public ErrorsListDTO deleteMatchStatisticById(Long id) {
        var errorsListDTO = new ErrorsListDTO();
        try {
            matchStatisticValidator.deleteMatchStatisticById(id);
        }
        catch (NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }
}
