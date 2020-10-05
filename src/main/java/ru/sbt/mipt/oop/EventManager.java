package ru.sbt.mipt.oop;

import java.util.List;

import static ru.sbt.mipt.oop.SensorEventType.*;
import static ru.sbt.mipt.oop.SensorEventType.DOOR_CLOSED;

public class EventManager {

    private final EventGeneratable generator;
    private final List<Managable> managers;

    public EventManager(EventGeneratable generator, List<Managable> managers) {
        this.generator = generator;
        this.managers = managers;
    }

    public void startManageEvents (SmartHome smartHome){
        SensorEvent event = generator.getNextSensorEvent();
        while (event != null) {
            System.out.println("Got event: " + event);
            for (Managable manager : managers){
                manager.manage(event, smartHome);
            }
            event = generator.getNextSensorEvent();
        }
    }
}
