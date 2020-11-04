package ru.sbt.mipt.oop.main.remotecontrol;

import org.springframework.stereotype.Component;
import ru.sbt.mipt.oop.main.elements.Light;
import ru.sbt.mipt.oop.main.elements.Room;
import ru.sbt.mipt.oop.main.elements.SmartHome;

@Component
public class TurnOnHallLightCommand implements RemoteControlCommand{

    private final SmartHome smartHome;

    public TurnOnHallLightCommand(SmartHome smartHome) {
        this.smartHome = smartHome;
    }

    @Override
    public void execute() {
        smartHome.execute(object -> {
            if(object instanceof Room){
                Room room = (Room) object;
                if(room.getName().equals("hall")){
                    room.execute(object1 -> {
                        if(object1 instanceof Light){
                            Light light = (Light) object1;
                            light.setOn(true);
                        }
                    });
                }
            }
        });
    }
}
