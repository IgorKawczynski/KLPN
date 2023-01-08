package com.io.klpn.admin;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.student.StudentService;
import com.io.klpn.team.TeamService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminService {

    final StudentService studentService;
    final TeamService teamService;

    public ErrorsListDTO acceptStudentById(Long studentId) {
        var updateDto = new UpdateDto(studentId, "isAccepted", true);
        return studentService.updateStudentField(updateDto);
    }

    public ErrorsListDTO acceptTeamById(Long teamId) {
        var updateDto = new UpdateDto(teamId, "isAccepted", true);
        return teamService.updateTeamField(updateDto);
    }

}
