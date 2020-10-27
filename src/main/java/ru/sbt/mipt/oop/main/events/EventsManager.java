package ru.sbt.mipt.oop.main.events;

import ru.sbt.mipt.oop.main.elements.SmartHome;
import ru.sbt.mipt.oop.main.managers.EventManagable;

import java.util.List;

public class EventsManager {

    private final EventGenerator generator;
    private final List<EventManagable> managers;

    public EventsManager(EventGenerator generator, List<EventManagable> managers) {
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
