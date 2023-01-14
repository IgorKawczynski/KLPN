package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
import com.io.klpn.team.dtos.TeamDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/student")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class StudentController {

    final StudentService studentService;

    @GetMapping("/{id}")
    public Student getStudentById(@RequestParam("id") Long id) {
        return studentService.getStudentById(id);
    }

    // Pościk :)
    @PostMapping("/my-team/{studentId}")
    public TeamDto getTeamDetailsByStudentId(@PathVariable Long studentId) {
        return studentService.getTeamByStudentId(studentId);
    }

    @PatchMapping("")
    public ErrorsListDTO updateStudentField(@RequestBody UpdateDto updateDto) {
        return studentService.updateStudentField(updateDto);
    }

    @PatchMapping("/my-team/edit/{playerId}")
    public ErrorsListDTO deleteStudentFromTeam(@PathVariable Long playerId) {
        return studentService.deleteStudentFromTeam(playerId);
    }

    @DeleteMapping("/{id}")
    public ErrorsListDTO deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

}
