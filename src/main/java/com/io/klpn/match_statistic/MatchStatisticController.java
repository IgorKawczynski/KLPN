package com.io.klpn.match_statistic;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/match-statistics")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MatchStatisticController {
}
