package ru.sbt.mipt.oop.main;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable{
    
    private Collection<Room> rooms;
    private Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
    }

    public Alarm getAlarm() {
        return alarm;
    }

    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public Collection<Room> getRooms() {
        return rooms;
    }

    @Override
    public void execute(Action action) {
        for(Room room : rooms){
            room.execute(action);
        }
    }
}
