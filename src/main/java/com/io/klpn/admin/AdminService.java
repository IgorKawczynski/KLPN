package com.io.klpn.admin;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.match_date_edit.MatchDateEditService;
import com.io.klpn.match_date_edit.dtos.MatchDateEditResponseDTO;
import com.io.klpn.student.StudentService;
import com.io.klpn.team.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminService {

    final StudentService studentService;
    final TeamService teamService;
    final MatchDateEditService matchDateEditService;

    public ErrorsListDTO acceptStudentById(Long studentId) {
        var updateDto = new UpdateDto(studentId, "isAccepted", true);
        return studentService.updateStudentField(updateDto);
    }

    public ErrorsListDTO deleteStudentById(Long studentId) {
        return studentService.deleteStudentById(studentId);
    }

    public ErrorsListDTO deleteStudentsByIds(List<Long> studentsIds) {
        var errorListDto = new ErrorsListDTO();
        for (Long studentId: studentsIds) {
            var error = deleteStudentById(studentId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    public ErrorsListDTO acceptStudentsByIds(List<Long> studentsIds) {
        var errorListDto = new ErrorsListDTO();
        for (Long studentId: studentsIds) {
            var error = acceptStudentById(studentId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    public ErrorsListDTO acceptTeamsByIds(List<Long> teamsIds){
        var errorListDto = new ErrorsListDTO();
        for (Long teamId: teamsIds) {
            var error = acceptTeamById(teamId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    private ErrorsListDTO acceptTeamById(Long teamId) {
        var updateDto = new UpdateDto(teamId, "isAccepted", true);
        return teamService.updateTeamField(updateDto);
    }

    public ErrorsListDTO deleteTeamsByIds(List<Long> teamsIds) {
        var errorListDto = new ErrorsListDTO();
        for (Long teamId: teamsIds) {
            var error = deleteTeamById(teamId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    private ErrorsListDTO deleteTeamById(Long id) {
        return teamService.deleteTeamById(id);
    }

    public List<MatchDateEditResponseDTO> getMatchDateChangeDtoList() {
        return matchDateEditService.getAllRequests();
    }

    public ErrorsListDTO acceptMatchDateChangesByIds(List<Long> requestsIds) {
        var errorListDto = new ErrorsListDTO();
        for (Long requestId: requestsIds) {
            var error = acceptMatchDateChangeById(requestId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    private ErrorsListDTO acceptMatchDateChangeById(Long requestId) {
        return matchDateEditService.acceptRequest(requestId);
    }

    public ErrorsListDTO rejectMatchDateChangesByIds(List<Long> requestsIds) {
        var errorListDto = new ErrorsListDTO();
        for (Long requestId: requestsIds) {
            var error = rejectMatchDateChangeById(requestId);
            errorListDto.addError(error);
        }
        return errorListDto;
    }

    private ErrorsListDTO rejectMatchDateChangeById(Long requestId) {
        return matchDateEditService.rejectRequest(requestId);
    }

}
