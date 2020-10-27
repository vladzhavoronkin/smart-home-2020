package ru.sbt.mipt.oop.main.managers;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.main.events.SensorEvent;
import ru.sbt.mipt.oop.main.elements.Alarm;
import ru.sbt.mipt.oop.main.elements.SmartHome;

import static ru.sbt.mipt.oop.main.events.SensorEventType.ALARM_ACTIVATE;
import static ru.sbt.mipt.oop.main.events.SensorEventType.ALARM_DEACTIVATE;

@Component
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
