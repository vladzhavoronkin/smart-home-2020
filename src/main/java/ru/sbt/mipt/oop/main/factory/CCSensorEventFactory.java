package ru.sbt.mipt.oop.main.factory;

import com.coolcompany.smarthome.events.CCSensorEvent;
import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.events.SensorEventType;

import java.util.HashMap;
import java.util.Map;


public class CCSensorEventFactory implements EventFactory {

    private CCSensorEvent event;

    public CCSensorEventFactory(CCSensorEvent event) {
        this.event = event;
    }

    @Override
    public SensorEvent convertEvent() {
        Map<String, SensorEventType> eventTypes = new HashMap<>();
        eventTypes.put("LightIsOn", SensorEventType.LIGHT_ON);
        eventTypes.put("LightIsOff", SensorEventType.LIGHT_OFF);
        eventTypes.put("DoorIsOpen", SensorEventType.DOOR_OPEN);
        eventTypes.put("DoorIsClosed", SensorEventType.DOOR_CLOSED);
        eventTypes.put("DoorIsLocked", SensorEventType.ALARM_ACTIVATE);
        eventTypes.put("DoorIsUnlocked", SensorEventType.ALARM_DEACTIVATE);
        return new SensorEvent(eventTypes.get(event.getEventType()), event.getObjectId());
    }
}
