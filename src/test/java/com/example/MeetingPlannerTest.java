package com.example;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MeetingPlannerTest {

    @Test
    public void testAddMeeting() {
        MeetingPlanner planner = new MeetingPlanner();
        planner.addMeeting("Test Meeting", LocalDateTime.now(), "Office", List.of("John", "Doe"));
        assertEquals(1, planner.getAllMeetings().size());
    }

    @Test
    public void testFindMeetingsByDate() {
        MeetingPlanner planner = new MeetingPlanner();
        LocalDateTime now = LocalDateTime.now();
        planner.addMeeting("Test Meeting", now, "Office", List.of("John", "Doe"));

        List<Meeting> foundMeetings = planner.findMeetingsByDate(now);
        assertEquals(1, foundMeetings.size());
    }

    @Test
    public void testNoMeetingsFound() {
        MeetingPlanner planner = new MeetingPlanner();
        LocalDateTime date = LocalDateTime.now();
        List<Meeting> foundMeetings = planner.findMeetingsByDate(date);
        assertEquals(0, foundMeetings.size());
    }
}