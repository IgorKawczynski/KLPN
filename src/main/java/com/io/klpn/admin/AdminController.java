package com.io.klpn.admin;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.cfg.NotYetImplementedException;
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

    @PatchMapping("/team/{teamId}")
    public ErrorsListDTO acceptTeamById(@PathVariable Long teamId) {
        return adminService.acceptTeamById(teamId);
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