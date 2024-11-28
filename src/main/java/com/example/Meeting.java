package com.example;

import java.time.LocalDateTime;
import java.util.List;

public class Meeting {
    private final String title;
    private final LocalDateTime dateTime;
    private final String place;
    private final List<String> participants;

    public Meeting(String title, LocalDateTime dateTime, String place, List<String> participants) {
        this.title = title;
        this.dateTime = dateTime;
        this.place = place;
        this.participants = participants;
    }

    public String getTitle() {
        return title;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getPlace() {
        return place;
    }

    public List<String> getParticipants() {
        return participants;
    }

    @Override
    public String toString() {
        return "Meeting{" + "title='" + title + '\'' + ", dateTime=" + dateTime +
                ", place='" + place + '\'' + ", participants=" + participants + '}';
    }
}
