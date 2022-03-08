package org.robovikes.frost.DataManager;

import java.util.ArrayList;
import java.util.HashMap;

public class Event extends Team {

    private static Event currentEvent;
    private String name;
    private final ArrayList<Event> events = new ArrayList<>();
    private final HashMap<String,Event> eventsMap = new HashMap<>();
    private final HashMap<Event, ArrayList<Team>> teams = new HashMap<>();

    public Event() {

    }

    private Event(String name, String startDate, String endDate) {
        super();
    }

    public void createTeam(Integer i) {
        Event current = getCurrentEvent();
        current.createTeam(i);
    }
    public void createEvent(String name, String startDate, String endDate) {
        events.add(new Event(name, startDate, endDate));
        eventsMap.put(name, new Event(name, startDate, endDate));
    }
    public void setEvent(Event event) {
        currentEvent = event;
    }
    public static Event getCurrentEvent() {
        return currentEvent;
    }
    public Event getEvent(String name) {
        if (eventsMap.containsKey(name)) {
            return eventsMap.get(name);
        } else {
            return null;
        }
    }
    public String getName() {
        return name;
    }
    public ArrayList<Event> getEvents() {
        return events;
    }
    public ArrayList<Team> getTeams(String event) {
        return teams.get(getEvent(event));
    }
    public Team getTeam(Integer number) {
        return Team.getTeam(number);
    }
}
