package ru.sbt.mipt.oop.main;

interface AlarmState{

    void setAlarm(Alarm alarm);
    void activateAlarm(String password);
    void deactivateAlarm(String password);
    void enableAlarm();
}