package ru.sbt.mipt.oop.main.remotecontrol;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.main.elements.Alarm;
import ru.sbt.mipt.oop.main.elements.SmartHome;

@Component
public class ActivateAlarmCommand implements RemoteControlCommand{

    private final SmartHome smartHome;
    private final String defaultPassword = "0000";

    public ActivateAlarmCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Alarm){
                Alarm alarm = (Alarm) object;
                alarm.activateAlarm(defaultPassword);
            }
        });
    }
}
