package com.io.klpn.team;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.transfer.Transfer;
import com.io.klpn.transfer.dto.TransferRequestDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TeamService {

    final TeamRepository teamRepository;
    final TeamValidator teamValidator;

    public ErrorsListDTO createTeam(Team team){
        var errorsListDTO = new ErrorsListDTO();
        try {
            teamRepository.save(teamValidator.createTeam(team));
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }

    public Team getTeamById(Long id){
        return teamRepository
                .findById(id)
                .orElseThrow(() -> new NoSuchElementException(String.format("Team with id: %d does not exist. ", id)));
    }

    public List<Team> generateTable(){
        return teamRepository
                .findAll(Sort.by(Sort.Direction.DESC, "points"));
    }

    public ErrorsListDTO deleteTeamById(Long id){
        var errorsListDTO = new ErrorsListDTO();
        try {
            teamValidator.deleteTeamById(id);
        }
        catch(NoSuchElementException exception) {
            errorsListDTO.addError(exception.getMessage());
        }
        return errorsListDTO;
    }
}
