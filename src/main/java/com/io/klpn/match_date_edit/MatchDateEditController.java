package com.io.klpn.match_date_edit;

import com.io.klpn.basic.ErrorsListDTO;
import com.io.klpn.match_date_edit.dtos.MatchDateEditDTO;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("/api/match-date-edit")
public class MatchDateEditController {

    final MatchDateEditService service;

    @PostMapping("/request")
    public ErrorsListDTO matchDateEditRequest(@RequestBody MatchDateEditDTO dto) {
        return service.createMatchDateEditRequest(dto);
    }

}
