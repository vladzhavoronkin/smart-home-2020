package ru.sbt.mipt.oop.main.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.main.elements.SmartHome;
import ru.sbt.mipt.oop.main.events.SensorEventType;
import ru.sbt.mipt.oop.main.factory.CCSensorEventFactory;
import ru.sbt.mipt.oop.main.managers.EventManagable;

import java.util.Map;

public class EventAdapter implements EventHandler {

    private EventManagable manager;
    private SmartHome smartHome;
    private final Map<String, SensorEventType> eventTypes;

    public EventAdapter(EventManagable manager, SmartHome smartHome, Map<String, SensorEventType> eventTypes) {
        this.manager = manager;
        this.smartHome = smartHome;
        this.eventTypes = eventTypes;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        manager.manage(new CCSensorEventFactory(event, eventTypes).convertEvent(), smartHome);
    }
}
