package ru.sbt.mipt.oop.main;

import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.main.SensorEventType.ALARM_DEACTIVATE;

public class AlarmEventManager implements EventManagable {

    @Override
    public void manage(SensorEvent event, SmartHome smartHome) {
        if (event.getType() == ALARM_ACTIVATE || event.getType() == ALARM_DEACTIVATE){
            smartHome.execute(object -> {
                if (object instanceof Alarm){
                    Alarm alarm = (Alarm) object;
                    if(event.getType() == ALARM_ACTIVATE){
                        alarm.activateAlarm(event.getPassword());
                    }
                    if(event.getType() == ALARM_DEACTIVATE){
                        alarm.deactivateAlarm(event.getPassword());
                    }
                }

            });
        }
    }
}
