package com.io.klpn.admin;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.match_date_edit.dtos.MatchDateEditDTO;
import com.io.klpn.match_date_edit.dtos.MatchDateEditResponseDTO;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminController {

    final AdminService adminService;

    @PatchMapping("/student/{studentId}")
    public ErrorsListDTO acceptStudentById(@PathVariable Long studentId) {
        return adminService.acceptStudentById(studentId);
    }

    @PatchMapping("/update-to-students")
    public ErrorsListDTO acceptStudentsByIds(@RequestBody List<Long> studentsIds) {
        return adminService.acceptStudentsByIds(studentsIds);
    }

    @DeleteMapping("/student/{studentId}")
    public ErrorsListDTO rejectStudentById(@PathVariable Long studentId) {
        return adminService.deleteStudentById(studentId);
    }

    @PostMapping("/reject-students")
    public ErrorsListDTO rejectStudentsByIds(@RequestBody List<Long> studentsIds) {
        return adminService.deleteStudentsByIds(studentsIds);
    }

    @PatchMapping("/accept-teams")
    public ErrorsListDTO acceptTeamsByIds(@RequestBody List<Long> teamsIds) {
        return adminService.acceptTeamsByIds(teamsIds);
    }

    @PostMapping("/reject-teams")
    public ErrorsListDTO deleteTeamsByIds(@RequestBody List<Long> teamsIds) {
        return adminService.deleteTeamsByIds(teamsIds);
    }

    @PatchMapping("/accept-match-date-changes")
    public ErrorsListDTO acceptMatchDateChangesByIds(@RequestBody List<Long> requestsIds) {
        return adminService.acceptMatchDateChangesByIds(requestsIds);
    }

    @PostMapping("/reject-match-date-changes")
    public ErrorsListDTO rejectMatchDateChangesByIds(@RequestBody List<Long> requestsIds) {
        return adminService.rejectMatchDateChangesByIds(requestsIds);
    }

    @GetMapping("/matches-date-change-requests")
    public List<MatchDateEditResponseDTO> getMatchDateChangeRequests() {
        return adminService.getMatchDateChangeDtoList();
    }

    @PatchMapping("/reservation/{reservationId}")
    public ErrorsListDTO acceptReservationDateChange(@RequestBody ReservationRequestDto reservationRequestDto) {
        throw new NotYetImplementedException();
    }

    @GetMapping("/notification")
    public List<String> getNotifications() {
        throw new NotYetImplementedException();
    }

}
