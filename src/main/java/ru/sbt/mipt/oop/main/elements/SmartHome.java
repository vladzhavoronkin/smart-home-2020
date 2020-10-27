package ru.sbt.mipt.oop.main.elements;

import java.util.ArrayList;
import java.util.Collection;

public class SmartHome implements Actionable {
    
    private final Collection<Room> rooms;
    private final Alarm alarm;

    public SmartHome() {
        rooms = new ArrayList<>();
        alarm = new Alarm();
    }



    public SmartHome(Collection<Room> rooms, Alarm alarm) {
        this.rooms = rooms;
        this.alarm = alarm;
    }

    public Alarm getAlarm() {
        return alarm;
    }

    void addRoom(Room room) {
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
