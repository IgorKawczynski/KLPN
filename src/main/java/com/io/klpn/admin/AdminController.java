package com.io.klpn.admin;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.reservation.dtos.ReservationRequestDto;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/team/{teamId}")
    public ErrorsListDTO acceptTeamById(@PathVariable Long teamId) {
        return adminService.acceptTeamById(teamId);
    }

    @PatchMapping("/reservation/{reservationId}")
    public ErrorsListDTO acceptReservationDateChange(@RequestBody ReservationRequestDto reservationRequestDto) {
        return null;
    }

}
