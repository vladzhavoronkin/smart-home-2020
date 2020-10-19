package ru.sbt.mipt.oop.main;

public class AlarmIsOffState implements AlarmState {

    private Alarm alarm;

    @Override
    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activateAlarm(String password) {
        alarm.setPassword(password);
        alarm.changeState(new AlarmIsOnState());
        System.out.println("Alarm is activated");
    }

    @Override
    public void deactivateAlarm(String password) {
        System.out.println("Alarm is deactivated");
    }

    @Override
    public void enableAlarm() {
        alarm.changeState(new AlarmIsSignallingState());
        System.out.println("Alarm is signalling");
    }
}
