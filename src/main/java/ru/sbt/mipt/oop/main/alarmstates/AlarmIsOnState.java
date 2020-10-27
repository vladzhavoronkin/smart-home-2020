package ru.sbt.mipt.oop.main.alarmstates;

import ru.sbt.mipt.oop.main.elements.Alarm;

public class AlarmIsOnState implements AlarmState {

    private Alarm alarm;

    @Override
    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activateAlarm(String password) {
        System.out.println("Alarm is activated");
    }

    @Override
    public void deactivateAlarm(String password) {
        if (password.equals(alarm.getPassword()) || alarm.getPassword() == null){
            alarm.changeState(new AlarmIsOffState());
            alarm.setPassword(null);
            System.out.println("Alarm is deactivated");
        } else {
            alarm.changeState(new AlarmIsSignallingState());
            System.out.println("Alarm is signalling");
        }
    }

    @Override
    public void enableAlarm() {
        alarm.changeState(new AlarmIsSignallingState());
        System.out.println("Alarm is signalling");
    }
}
