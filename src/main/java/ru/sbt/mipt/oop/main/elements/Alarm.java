package ru.sbt.mipt.oop.main.elements;

import ru.sbt.mipt.oop.main.alarmstates.AlarmIsOffState;
import ru.sbt.mipt.oop.main.alarmstates.AlarmState;

public class Alarm implements Actionable {

    private AlarmState alarmState;
    private String password;

    public Alarm() {
        this.alarmState = new AlarmIsOffState();
        this.alarmState.setAlarm(this);
        this.password = null;
    }

    public void changeState(AlarmState state){
        alarmState = state;
        state.setAlarm(this);
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getPassword() {
        return password;
    }

    public AlarmState isActivated() {
        return alarmState;
    }

    public void activateAlarm(String word){
        alarmState.activateAlarm(word);
    }

    public void deactivateAlarm(String word){
        alarmState.deactivateAlarm(word);
    }

    public void enableAlarm(){
        alarmState.enableAlarm();
    }

    @Override
    public void execute(Action action) {
        action.doAction(this);
    }
}
