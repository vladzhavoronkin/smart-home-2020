package ru.sbt.mipt.oop.main.adapter;

import com.coolcompany.smarthome.events.CCSensorEvent;
import com.coolcompany.smarthome.events.EventHandler;
import ru.sbt.mipt.oop.main.elements.SmartHome;
import ru.sbt.mipt.oop.main.factory.CCSensorEventFactory;
import ru.sbt.mipt.oop.main.managers.EventManagable;

public class EventAdapter implements EventHandler {

    private EventManagable manager;
    private SmartHome smartHome;

    public EventAdapter(EventManagable manager, SmartHome smartHome) {
        this.manager = manager;
        this.smartHome = smartHome;
    }

    @Override
    public void handleEvent(CCSensorEvent event) {
        manager.manage(new CCSensorEventFactory(event).convertEvent(), smartHome);
    }
}
