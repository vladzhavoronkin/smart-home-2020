package ru.sbt.mipt.oop.main.alarmstates;

import ru.sbt.mipt.oop.main.elements.Alarm;

public interface AlarmState{

    void setAlarm(Alarm alarm);
    void activateAlarm(String password);
    void deactivateAlarm(String password);
    void enableAlarm();
}