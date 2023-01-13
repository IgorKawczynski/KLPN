package com.io.klpn.match_statistic.dtos;

import com.io.klpn.match_statistic.enums.Event;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Predicate;

@Component
public class MatchStatisticMapper {

    Predicate<Event> isGoal = Event::isGoal;
    Predicate<Event> isAssist = Event::isAssist;
    Predicate<Event> isYellowCard = Event::isYellowCard;
    Predicate<Event> isRedCard = Event::isRedCard;
    Predicate<Event> isOwnGoal = Event::isOwnGoal;

    public PlayerStatisticsSummaryDTO toMatchStatisticSummaryDTO(List<MatchStatisticCountDTO> dtoList) {

        var goals = dtoList.stream()
                .filter(statistic -> isGoal.test(statistic.event()))
                .mapToInt(statistic -> statistic.quantity().intValue())
                .sum();

        var assists = dtoList.stream()
                .filter(statistic -> isAssist.test(statistic.event()))
                .mapToInt(statistic -> statistic.quantity().intValue())
                .sum();

        var yellowCards = dtoList.stream()
                .filter(statistic -> isYellowCard.test(statistic.event()))
                .mapToInt(statistic -> statistic.quantity().intValue())
                .sum();

        var redCards = dtoList.stream()
                .filter(statistic -> isRedCard.test(statistic.event()))
                .mapToInt(statistic -> statistic.quantity().intValue())
                .sum();

        var ownGoals = dtoList.stream()
                .filter(statistic -> isOwnGoal.test(statistic.event()))
                .mapToInt(statistic -> statistic.quantity().intValue())
                .sum();

        return new PlayerStatisticsSummaryDTO(goals, assists, yellowCards, redCards, ownGoals);
    }
}
