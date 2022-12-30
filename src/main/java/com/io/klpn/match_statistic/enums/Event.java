package com.io.klpn.match_statistic.enums;

import java.util.Objects;
import java.util.stream.Stream;

public enum Event {

    GOAL("goal"),
    ASSIST("assist"),
    OWN_GOAL("own goal"),
    RED_CARD("red card"),
    YELLOW_CARD("yellow card");

    private String eventType;

    Event(String eventType) {
        this.eventType = eventType;
    }

    public String getEventType() {
        return this.eventType;
    }

    public static Event getEvent(String eventType) {
        return Stream.of(values())
                .filter(event -> event.eventType.equals(eventType))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        String.format("eventType must be one of '%s', '%s', '%s', '%s' or '%s'",
                        GOAL.getEventType(), ASSIST.getEventType(), OWN_GOAL.getEventType(),
                                RED_CARD.getEventType(), YELLOW_CARD.getEventType())));
    }

    public boolean isGoal() {
        return Objects.equals(this.eventType, GOAL.getEventType());
    }

    public boolean isAssist() {
        return Objects.equals(this.eventType, ASSIST.getEventType());
    }

    public boolean isOwnGoal() {
        return Objects.equals(this.eventType, OWN_GOAL.getEventType());
    }

    public boolean isRedCard() {
        return Objects.equals(this.eventType, RED_CARD.getEventType());
    }

    public boolean isYellowCard() {
        return Objects.equals(this.eventType, YELLOW_CARD.getEventType());
    }

}
