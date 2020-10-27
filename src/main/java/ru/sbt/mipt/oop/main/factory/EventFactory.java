package ru.sbt.mipt.oop.main.factory;

import ru.sbt.mipt.oop.main.events.SensorEvent;

public interface EventFactory {

    SensorEvent convertEvent();
}
