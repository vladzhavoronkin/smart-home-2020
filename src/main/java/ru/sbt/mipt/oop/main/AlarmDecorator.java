package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_DEACTIVATE;

public class AlarmDecorator implements EventManagable {

    private EventManagable wrappee;

    public AlarmDecorator(EventManagable wrappee) {
        this.wrappee = wrappee;
    }

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (((event.getType() != ALARM_ACTIVATE) || event.getType() != ALARM_DEACTIVATE)
            && (smartHome.getAlarm().isActivated() instanceof AlarmIsOnState
                || smartHome.getAlarm().isActivated() instanceof AlarmIsSignallingState)){
            smartHome.getAlarm().enableAlarm();
            System.out.println("Sending sms");
        } else {
            wrappee.manage(event, smartHome);
        }
    }
}
