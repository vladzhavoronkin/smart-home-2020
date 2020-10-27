package ru.sbt.mipt.oop.main.managers;

import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.elements.SmartHome;

public interface EventManagable {

    void manage(SensorEvent event, SmartHome smartHome);
}
