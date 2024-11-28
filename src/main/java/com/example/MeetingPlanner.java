package com.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MeetingPlanner {
    private final List<Meeting> meetings = new ArrayList<>();

    public void addMeeting(String title, LocalDateTime dateTime, String place, List<String> participants) {
        meetings.add(new Meeting(title, dateTime, place, participants));
    }

    public List<Meeting> findMeetingsByDate(LocalDateTime date) {
        return meetings.stream()
                .filter(m -> m.getDateTime().toLocalDate().equals(date.toLocalDate()))
                .collect(Collectors.toList());
    }

    public List<Meeting> getAllMeetings() {
        return new ArrayList<>(meetings);
    }
}
