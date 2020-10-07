package ru.sbt.mipt.oop.main;

import java.util.List;

public class EventManager {

    private final EventGenerator generator;
    private final List<EventManagable> managers;

    public EventManager(EventGenerator generator, List<EventManagable> managers) {
        this.generator = generator;
        this.managers = managers;
    }

    public void startManageEvents (SmartHome smartHome){
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (EventManagable manager : managers){
                manager.manage(event, smartHome);
            }
            event = generator.getNextSensorEvent();
        }
    }
}
