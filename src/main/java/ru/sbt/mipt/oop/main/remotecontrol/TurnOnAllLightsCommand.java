package ru.sbt.mipt.oop.main.remotecontrol;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.main.elements.Light;
import ru.sbt.mipt.oop.main.elements.SmartHome;

@Component
public class TurnOnAllLightsCommand implements RemoteControlCommand {

    private final SmartHome smartHome;

    public TurnOnAllLightsCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if (object instanceof Light) {
                Light light = (Light) object;
                light.setOn(true);
            }
        });
    }
}
