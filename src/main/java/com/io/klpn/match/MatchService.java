package com.io.klpn.match;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.exceptions.IntegerValidatorException;
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
public class MatchService {

    final MatchRepository matchRepository;
    final MatchValidator matchValidator;

    public ErrorsListDTO createMatch (Match matchToCreate) {
        var errorsList = new ErrorsListDTO();

        try {
            var match = matchValidator.createMatch(matchToCreate);
            matchRepository.save(match);
        }
        catch (IntegerValidatorException exception) {
            errorsList.addError(exception.getMessage());
        }
        return errorsList;
    }

    public Match getMatchById(Long matchId) {
        return matchRepository.findById(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Match with given id doesn't exists!"));
    }

    public Page<Match> getAllMatches(Integer page){
        Pageable paginated = PageRequest.of(page, 20);
        return matchRepository.findAll(paginated);
    }

    public ErrorsListDTO deleteMatchById(Long id){
        var errorsListDto = new ErrorsListDTO();
        try {
            matchValidator.deleteMatchById(id);
        }
        catch (NoSuchElementException exception) {
            errorsListDto.addError(exception.getMessage());
        }
        return errorsListDto;
    }
}
