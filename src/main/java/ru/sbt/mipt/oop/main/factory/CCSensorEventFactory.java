package ru.sbt.mipt.oop.main.factory;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.events.SensorEventType;

import java.util.Map;


public class CCSensorEventFactory implements EventFactory {

    private final CCSensorEvent event;
    private final Map<String, SensorEventType> eventTypes;

    public CCSensorEventFactory(CCSensorEvent event, Map<String, SensorEventType> eventTypes) {
        this.event = event;
        this.eventTypes = eventTypes;
    }

    @Override
    public SensorEvent convertEvent() {
        if (!eventTypes.containsKey(event.getEventType())) {
           throw new IllegalArgumentException("Command does not exist");
        }
        return new SensorEvent(eventTypes.get(event.getEventType()), event.getObjectId());
    }
}
