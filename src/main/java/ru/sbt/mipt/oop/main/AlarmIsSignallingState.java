package ru.sbt.mipt.oop.main;

public class AlarmIsSignallingState implements AlarmState {

    private Alarm alarm;

    @Override
    public void setAlarm(Alarm alarm) {
        this.alarm = alarm;
    }

    @Override
    public void activateAlarm(String password) {}

    @Override
    public void deactivateAlarm(String password) {
        if (password.equals(alarm.getPassword()) || alarm.getPassword() == null){
            alarm.changeState(new AlarmIsOffState());
            alarm.setPassword(null);
            System.out.println("Alarm is activated");
        } else {
            System.out.println("Alarm is signalling");
        }
    }

    @Override
    public void enableAlarm() {
        System.out.println("Alarm is signalling");
    }
}
