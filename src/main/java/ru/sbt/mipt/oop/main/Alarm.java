package ru.sbt.mipt.oop.main;

public class Alarm implements Actionable {

    private AlarmState alarmState;

    public Alarm(AlarmState alarmState) {
        this.alarmState = alarmState;
    }

    public AlarmState isActivated() {
        return alarmState;
    }

    public void setActivated(AlarmState state) {
        alarmState = state;
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
    }
}
