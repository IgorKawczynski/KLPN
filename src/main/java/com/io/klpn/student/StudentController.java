package com.io.klpn.student;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.basic.UpdateDto;
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

    @PatchMapping("")
    public ErrorsListDTO updateStudentField(@RequestBody UpdateDto updateDto) {
        return studentService.updateStudentField(updateDto);
    }

    @DeleteMapping("/{id}")
    public ErrorsListDTO deleteStudentById(@PathVariable Long id) {
        return studentService.deleteStudentById(id);
    }

}
